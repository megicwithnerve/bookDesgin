package com.magician.book.config.rabbit;

/**
 * @消息队列常量
 * @Autor zxf
 * @Date 2019/8/19
 */
public class RabbitMQConstant {
    //队列1
    public static final String QUEUE_1 = "queue_1";

    //队列2
    public static final String QUEUE_2 = "queue_2";

    //队列3
    public static final String QUEUE_3 = "queue_3";

    //死信队列
    public static final String DEAD_QUEUE = "dead_queue";

    //处理死信队列
    public static final String CONSUMER_BEAD_QUEUE = "consumer_bead_queue";

    //死信交换机
    public static final String DEAD_EXCHANGE = "dead_exchange";

    //订阅模式交换机
    public static final String FANOUT_EXCHANGE = "fanout_exchange";

    //路由模式交换机
    public static final String DIRECT_EXCHANGE = "direct_exchange";

    //主题模式交换机
    public static final String TOPIC_EXCHANGE = "topic_exchange";

    //路由键routing_key1
    public static final String ROUTING_KEY1 = "routing_key1";

    //路由键routing_key2
    public static final String ROUTING_KEY2 = "routing_key2";

    //路由键routing_key3
    public static final String ROUTING_KEY3 = "routing_key3";

    //符号*匹配一个词
    public static final String TOPIC_ROUTINGKEY1 = "hello.*";

    //符号#匹配一个或多个词
    public static final String TOPIC_ROUTINGKEY2 = "hello.#";
}
