package com.magician.book.config.rabbit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @消息队列配置
 * @Autor zxf
 * @Date 2019/8/15
 */
@Configuration
public class QueueConfig {

    private  static final Logger logger = LoggerFactory.getLogger(QueueConfig.class);


    /**
     *  创建订阅模式交换机
     *  @return
     */
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(RabbitMQConstant.FANOUT_EXCHANGE,true, false);
    }

    /**
     * 创建路由模式交换机
     * @return
     */
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(RabbitMQConstant.DIRECT_EXCHANGE,true, false);
    }

    /**
     * 创建主题模式交换机
     * @return
     */
    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(RabbitMQConstant.TOPIC_EXCHANGE,true, false);
    }

    /**
     * 创建死信交换机,跟普通交换机一样,只是死信交换机只用来接收过期的消息
     * @return
     */
    @Bean
    public DirectExchange deadExchange() {
        return new DirectExchange(RabbitMQConstant.DEAD_EXCHANGE, true, false);
    }

    /**
     * 创建死信队列,该队列没有消费者,消息会设置过期时间,消息过期后会发送到死信交换机,在由死信交换机转发至处理该消息的队列中
     * @return
     */
    @Bean
    public Queue BeadQueue() {
        Map<String, Object> arguments = new HashMap<>(2);
        // 死信路由到死信交换器DLX
        arguments.put("x-dead-letter-exchange", RabbitMQConstant.DEAD_EXCHANGE);
        arguments.put("x-dead-letter-routing-key", RabbitMQConstant.ROUTING_KEY2);
        return new Queue(RabbitMQConstant.DEAD_QUEUE, true, false, false, arguments);

    }

    /**
     * 处理死信队列的消费队列
     *
     */
    @Bean
    public Queue consumerBeadQueue() {
        return new Queue(RabbitMQConstant.CONSUMER_BEAD_QUEUE, true); // 队列持久

    }

    /**
     * 创建队列1
     * @return
     */
    @Bean
    public Queue Queue1() {
        //队列持久化
        return new Queue(RabbitMQConstant.QUEUE_1, true);
    }

    /**
     * 创建队列2
     * @return
     */
    @Bean
    public Queue Queue2() {
        return new Queue(RabbitMQConstant.QUEUE_2, true);
    }

    /**
     * 创建队列3
     * @return
     */
    @Bean
    public Queue Queue3() {
        return new Queue(RabbitMQConstant.QUEUE_3, true);
    }
    /**
     * 订阅模式队列1绑定交换机
     * @return
     */
    @Bean
    public Binding fanoutBinding1() {
        return BindingBuilder.bind(Queue1()).to(fanoutExchange());
    }

    /**
     * 订阅模式队列2绑定交换机
     * @return
     */
    @Bean
    public Binding fanoutBinding2() {
        return BindingBuilder.bind(Queue2()).to(fanoutExchange());
    }

    /**
     * 路由模式队列1绑定交换机,通过key1发送
     * @return
     */
    @Bean
    public Binding directBinding1() {
        return BindingBuilder.bind(Queue1()).to(directExchange()).with(RabbitMQConstant.ROUTING_KEY1);
    }

    /**
     * 路由模式队列2绑定交换机,通过key2发送
     * @return
     */
    @Bean
    public Binding directBinding2() {
        return BindingBuilder.bind(Queue2()).to(directExchange()).with(RabbitMQConstant.ROUTING_KEY2);
    }

    /**
     * 路由模式队列3绑定交换机,通过key3发送
     * @return
     */
    @Bean
    public Binding directBinding3() {
        return BindingBuilder.bind(Queue3()).to(directExchange()).with(RabbitMQConstant.ROUTING_KEY3);
    }
    /**
     * 主题模式队列1绑定交换机
     * 符号“#”匹配一个或多个词，符号“*”匹配一个词。比如“hello.#”能够匹配到“hello.123.456”，但是“hello.*”只能匹配到“hello.123”
     * @return
     */
    @Bean
    public Binding topicBinding1() {
        return BindingBuilder.bind(Queue1()).to(topicExchange()).with(RabbitMQConstant.TOPIC_ROUTINGKEY1);
    }

    /**
     * 主题模式队列1绑定交换机
     * 符号“#”匹配一个或多个词，符号“*”匹配一个词。比如“hello.#”能够匹配到“hello.123.456”，但是“hello.*”只能匹配到“hello.123”
     * @return
     */
    @Bean
    public Binding topicBinding2() {
        return BindingBuilder.bind(Queue2()).to(topicExchange()).with(RabbitMQConstant.TOPIC_ROUTINGKEY2);
    }


    /**
     * 将死信队列与死信交换机绑定,key1
     *
     * @return
     */
    @Bean
    public Binding beadQueuebinding() {
        return BindingBuilder.bind(BeadQueue()).to(deadExchange()).with(RabbitMQConstant.ROUTING_KEY1);
    }

    /**
     * 将处理死信队列的消费队列与死信交换机绑定 key2
     *
     * @return
     */
    @Bean
    public Binding consumerBeadQueuebinding() {
        return BindingBuilder.bind(consumerBeadQueue()).to(deadExchange()).with(RabbitMQConstant.ROUTING_KEY2);
    }
}
