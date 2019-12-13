package com.magician.book.controller;

import com.magician.book.config.rabbit.RabbitMQSend;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @消息队列提供者
 * @Autor zxf
 * @Date 2019/8/15
 */
@RestController
public class RabbitMqContronller {

    private final RabbitMQSend send;

    @Autowired
    public RabbitMqContronller(RabbitMQSend send) {
        this.send = send;
    }

    @ApiOperation(value = "简单模式发送json消息",notes = "简单模式发送json消息" )
    @ApiImplicitParam(name="json",value="消息json",required=true,dataType="String",paramType="header",defaultValue="application/json")
    @GetMapping("/simplSend")
    public String simplSend(@RequestParam(value = "json", required = false) String json){
        try {
            this.send.simplSend(json);
            return "succeed";
        } catch (Exception e) {
            e.printStackTrace();
            return  "error";
        }
    }

    @ApiOperation(value = "订阅模式发送json消息",notes = "订阅模式发送json消息" )
    @ApiImplicitParam(name="json",value="消息json",required=true,dataType="String",paramType="header",defaultValue="application/json")
    @GetMapping("/routeSend")
    public String routeSend(@RequestParam(value = "json", required = false) String json){
        try {
            this.send.routeSend(json);
            return "succeed";
        } catch (Exception e) {
            e.printStackTrace();
            return  "error";
        }
    }

    @ApiOperation(value = "路由模式发送json消息",notes = "路由模式发送json消息" )
    @ApiImplicitParams({
            @ApiImplicitParam(name="json",value="消息json",required=true,dataType="String",paramType="header",defaultValue="application/json"),
            @ApiImplicitParam(name="routingKey",value="key",required=true,dataType="String",paramType="header",defaultValue="application/json")})
    @GetMapping("/routingSend")
    public String routingSend(
            @RequestParam(value = "json", required = false) String json,
            @RequestParam(value ="routingKey", required = false) String routingKey){
        try {
            this.send.routingSend(routingKey,json);
            return "succeed";
        } catch (Exception e) {
            e.printStackTrace();
            return  "error";
        }
    }

    @ApiOperation(value = "主题模式发送json消息",notes = "主题模式发送json消息" )
    @ApiImplicitParams({
            @ApiImplicitParam(name="json",value="消息json",required=true,dataType="String",paramType="header",defaultValue="application/json"),
            @ApiImplicitParam(name="routingKey",value="key",required=true,dataType="String",paramType="header",defaultValue="application/json")})
    @GetMapping("/topicSend")
    public String topicSend(
            @RequestParam(value = "json", required = false) String json,
            @RequestParam(value ="routingKey", required = false) String routingKey){
        try {
            this.send.topicSend(routingKey,json);
            return "succeed";
        } catch (Exception e) {
            e.printStackTrace();
            return  "error";
        }
    }

    @ApiOperation(value = "死信模式发送json消息",notes = "用于处理定时任务,如订单超时未支付自动取消" )
    @ApiImplicitParams({
            @ApiImplicitParam(name="json",value="消息json",required=true,dataType="String",paramType="header",defaultValue="application/json"),
            @ApiImplicitParam(name="routingKey",value="key",required=true,dataType="String",paramType="header",defaultValue="application/json")})
    @GetMapping("/beadSend")
    public String beadSend(
            @RequestParam(value = "json", required = false) String json,
            @RequestParam(value ="routingKey", required = false) String routingKey){
        System.out.println("json:"+json);
        try {
            MessageProperties messageProperties = new MessageProperties();
            //设置消息过期时间,这里设置的时间是10分钟
            messageProperties.setExpiration(6 + "000");
            Message message = new Message(json.getBytes(), messageProperties);
            message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
            //这里的key应该传死信队列绑定死信交换机的路由key,这里我们传key1
            this.send.beadSend("routing_key1",message);
            return "succeed";
        } catch (Exception e) {
            e.printStackTrace();
            return  "error";
        }
    }


}
