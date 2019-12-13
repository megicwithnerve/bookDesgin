package com.magician.book.config.rabbit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;


/**
 * @消息队列发送工具类
 * @Autor zxf
 * @Date 2019/8/15
 */
@Component
public class RabbitMQSend implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {

    private  static final Logger log = LoggerFactory.getLogger(RabbitMQSend.class);

    private RabbitTemplate rabbitTemplate;

//    private MqProperties config;

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    public RabbitMQSend(RabbitTemplate rabbitTemplate) {
        super();
        this.rabbitTemplate = rabbitTemplate;
        this.rabbitTemplate.setMandatory(true);
        this.rabbitTemplate.setReturnCallback(this);
        this.rabbitTemplate.setConfirmCallback(this);
    }

    /**
     * 发布/订阅模式发送
     * @param json
     */
    public void routeSend(String json) {
        Message message = this.setMessage(json);
        //在fanoutExchange中在绑定Q到X上时，会自动把Q的名字当作bindingKey。
        this.rabbitTemplate.convertAndSend(RabbitMQConstant.FANOUT_EXCHANGE, "", message);
    }

    /**
     * 简单模式发送
     * @param json
     */
    public void simplSend(String json) {
        Message message = this.setMessage(json);
        this.rabbitTemplate.convertAndSend(RabbitMQConstant.QUEUE_1, message, new CorrelationData("123456"));
    }

    /**
     * 路由模式发送
     *  @param routingKey
     * @param json
     */
    public void routingSend(String routingKey, String json) {
        Message message = this.setMessage(json);
        this.rabbitTemplate.convertAndSend(RabbitMQConstant.DIRECT_EXCHANGE, routingKey, message);
    }

    /**
     * 主题模式发送
     *
     * @param routingKey
     * @param json
     */
    public void topicSend(String routingKey, String json) {
        Message message = this.setMessage(json);
        this.rabbitTemplate.convertAndSend(RabbitMQConstant.TOPIC_EXCHANGE, routingKey, message);
    }

    /**
     * 死信模式发送,用于定时任务处理
     *  @param routingKey
     * @param message
     */
    public void beadSend(String routingKey, Message message) {
        this.rabbitTemplate.convertAndSend(RabbitMQConstant.DEAD_EXCHANGE, routingKey, message);
    }

    /**
     * 设置消息参数
     * @param json
     * @return
     */
    private Message setMessage(String json){
        MessageProperties messageProperties = new MessageProperties();
        Message message = new Message(json.getBytes(), messageProperties);
        //消息持久化
        message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
        return message;
    }

    /**
     * 消息确认
     * @param correlationData
     * @param ack
     * @param cause
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if(ack){
            log.info("发送成功");
        }else{
            log.info("发送失败");
        }
    }

    /**
     * 消息发送失败回传
     * @param message
     * @param replyCode
     * @param replyText
     * @param exchange
     * @param routingKey
     */
    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        log.info("return--message:" + new String(message.getBody()) + ",replyCode:" + replyCode + ",replyText:"
                + replyText + ",exchange:" + exchange + ",routingKey:" + routingKey);
    }

    /**
     * 扩展消息的CorrelationData，方便在回调中应用
     */
//    public void setCorrelationData(String bizId, String coordinator, EventMessage msg, Integer retry) {
//        rabbitTemplate.setCorrelationDataPostProcessor(((message, correlationData) ->
//                new CorrelationDataExt(bizId, coordinator,
//                        retry == null ? config.getDistributed().getCommitMaxRetries() : retry, msg)));
//    }
}


