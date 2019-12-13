package com.magician.book.pojo;

import java.util.UUID;

public class AlipayRefund {
    //商户订单号，商户网站订单系统中唯一订单号
    public String out_trade_no ="0e9c990abd424eb7919f7dba7d8409ab";
    //支付宝交易号
    public String trade_no = "2019120922001404321000058063";
    //请二选一设置
    //需要退款的金额，该金额不能大于订单金额，必填
    public String refund_amount ="0.10";
    //退款的原因说明
    public String refund_reason = "1111111";
    //标识一次退款请求，同一笔交易多次退款需要保证唯一，如需部分退款，则此参数必传
    public String out_request_no = UUID.randomUUID().toString().replace("-", "");

    public AlipayRefund() {
    }

}
