package com.magician.book.controller.alipay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeRefundModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeFastpayRefundQueryRequest;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.google.gson.Gson;
import com.magician.book.config.rabbit.RabbitMQSend;
import com.magician.book.pojo.AlipayRefund;
import com.magician.book.pojo.AlipayVo;
import com.magician.book.pojo.Rechargerecord;
import com.magician.book.pojo.Setmeal;
import com.magician.book.services.RechargeRecordService;
import com.magician.book.services.SetmealService;
import com.magician.book.utils.APIResult;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @Title: AlipayController.java
 * @Package cn.trmap.tdcloud.pay
 * @Description: 支付宝后台接口
 * @author nelson
 * @date 2018年3月21日 下午5:31:03
 */
@RequestMapping("alipay")
//@RestController
@Controller
public class AlipayController {

    private final RabbitMQSend send;

    @Autowired
    public AlipayController(RabbitMQSend send) {
        this.send = send;
    }

    @Value("${ALIPAY.APPID}")
    private String app_id;
    @Value("${ALIPAY.PRIVATEKEY}")
    private String merchant_private_key;
    @Value("${ALIPAY.PUBLICKEY}")
    private String alipay_public_key;
    @Value("${ALIPAY.NOTIFY_URL}")
    private String notify_url;
    @Value("${ALIPAY.RETURNA_URL}")
    private String return_url;
    @Value("${ALIPAY.SIGN}")
    private String sign_type = "RSA2";
    private String charset = "utf-8";

    @Value("${ALIPAY.SERVER}")
    private String gatewayUrL;

    @Autowired
    RechargeRecordService rechargeRecordService;
    @Autowired
    SetmealService setmealService;

    @GetMapping("pay")
    private String alipayPay() throws AlipayApiException {
        //这个应该是从前端端传过来的，这里为了测试就从后台写死了
        AlipayVo vo = new AlipayVo();
        vo.setOut_trade_no(UUID.randomUUID().toString().replace("-", ""));
        vo.setTotal_amount("0.10");
        vo.setSubject("nelson-test-title");
        vo.setProduct_code("FAST_INSTANT_TRADE_PAY"); //这个是固定的
        Long time = new Date().getTime()+5000;
//        vo.setTimeout_express(time.toString());
        String json = new Gson().toJson(vo);
        System.out.println(json);

        AlipayClient alipayClient = new DefaultAlipayClient(gatewayUrL, app_id, merchant_private_key, "json",charset,alipay_public_key,sign_type);
        // 设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(return_url);
        alipayRequest.setNotifyUrl(notify_url);
        alipayRequest.setBizContent(json);
        String result = alipayClient.pageExecute(alipayRequest).getBody();
//        System.out.println(result);
        return result; //这里生成一个表单，会自动提交
    }

    /**
            * @Title: alipayNotify
     * @Description: 支付宝回调接口
     * @author nelson
     * @param request
     * @param out_trade_no 商户订单号
     * @param trade_no 支付宝交易凭证号
     * @param trade_status 交易状态
     * @throws AlipayApiException
     * @return String
     * @throws
             */
    @PostMapping("notify")
//    @ResponseBody
    private String alipayNotify(HttpServletRequest request, String out_trade_no, String trade_no, String trade_status)
            throws AlipayApiException {
        Map<String, String> map = new HashMap<String, String>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = iter.next();
            String[] values = requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
                System.out.println(valueStr);
            }
            map.put(name, valueStr);
        }
        boolean signVerified = false;
        try {
            signVerified = AlipaySignature.rsaCheckV1(map,alipay_public_key,charset,sign_type);
//            System.out.println(map);
            signVerified = true;

        } catch (AlipayApiException e) {
            e.printStackTrace();
            return ("fail");// 验签发生异常,则直接返回失败
        }
        if (signVerified) {
            AlipayVo alipayVo = new AlipayVo();
            alipayVo.setOut_trade_no(out_trade_no);
            System.out.println(alipayVo.getOut_trade_no());
            rechargeRecordService.updateRechargeByVo(alipayVo,alipayVo.getOut_trade_no(),1);
            return "redirect:/reader/ok";
        } else {
            System.out.println("验证失败,不去更新状态");
            return ("fail");
        }
    }


    /**
     * @Title: alipayReturn
     * @Description: 支付宝回调接口
     * @author nelson
     * @param request
     * @param out_trade_no 商户订单号
     * @param trade_no 支付宝交易凭证号
     * @param trade_status 交易状态
     * @throws AlipayApiException
     * @return String
     * @throws
     */
    @GetMapping("return")
//    @ResponseBody
    private String alipayReturn(Map<String, String> params, HttpServletRequest request, String out_trade_no,String trade_no,String total_amount)
            throws AlipayApiException {
        Map<String, String> map = new HashMap<String, String>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = iter.next();
            String[] values = requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
//                System.out.println(valueStr);
            }
            map.put(name, valueStr);
        }
//        System.out.println(map);

        boolean signVerified = false;
        try {
            signVerified = AlipaySignature.rsaCheckV1(map, alipay_public_key, charset, sign_type);
            signVerified = true;
        } catch (AlipayApiException e) {
            e.printStackTrace();
            return ("fail");// 验签发生异常,则直接返回失败
        }
        if (signVerified) {
            AlipayVo alipayVo = new AlipayVo();
            alipayVo.setOut_trade_no(out_trade_no);
//            System.out.println(alipayVo.getOut_trade_no());
            rechargeRecordService.updateRechargeByVo(alipayVo,alipayVo.getOut_trade_no(),1);
            System.out.println("test");
            return "forward:/reader/ok";
        } else {
            System.out.println("验证失败,不去更新状态");
            return ("fail");
        }
    }

    @RequestMapping("refund")
    public String refund(){




        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(gatewayUrL, app_id, merchant_private_key, "json",charset,alipay_public_key,sign_type);

        //设置请求参数
        AlipayTradeRefundRequest alipayRequest = new AlipayTradeRefundRequest();

        AlipayRefund alipayRefund = new AlipayRefund();

        String json = new Gson().toJson(alipayRefund);
        AlipayTradeRefundModel model=new AlipayTradeRefundModel();
        model.setOutTradeNo(alipayRefund.out_trade_no);
        model.setTradeNo(alipayRefund.trade_no);
        model.setRefundAmount(alipayRefund.refund_amount);
        model.setRefundReason(alipayRefund.refund_reason);
        model.setOutRequestNo(alipayRefund.out_request_no);
        alipayRequest.setBizModel(model);
//        alipayRequest.setReturnUrl(return_url);
//        alipayRequest.setNotifyUrl(notify_url);
//,"
//                + "\"out_request_no\":\""+ out_request_no +"\
        //请求
        String result = new String();
        try {
            result = alipayClient.execute(alipayRequest).getBody();
        } catch (AlipayApiException e) {

            e.printStackTrace();

        }finally {
            System.out.println(result);
            return result;

        }

//        System.out.println(11111111);
        //输出
//        return result;
    }


    @RequestMapping("/success")
    public String success(AlipayVo alipayVo){
        return alipayVo.toString();
    }



    @RequestMapping("/refund/query")
    @ResponseBody
    public String refundQuery(){
        AlipayClient alipayClient = new DefaultAlipayClient(gatewayUrL, app_id, merchant_private_key, "json",charset,alipay_public_key,sign_type);

        //设置请求参数
        AlipayTradeFastpayRefundQueryRequest alipayRequest = new AlipayTradeFastpayRefundQueryRequest();

        AlipayRefund alipayRefund = new AlipayRefund();

        String json = new Gson().toJson(alipayRefund);


        alipayRequest.setBizContent(json);

        String result = null;
        try {
            result = alipayClient.execute(alipayRequest).getBody();
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }

        //输出
        return result;
    }

    @RequestMapping("/reader/pay")
    @ResponseBody
    public String readerPay(Setmeal setmeal, HttpSession session) throws AlipayApiException{



        APIResult s = setmealService.getPackageById(setmeal);
        setmeal = (Setmeal) s.getData();
        if(setmeal == null){
            System.out.println("baozha");
        }

        //这个应该是从前端端传过来的，这里为了测试就从后台写死了
        AlipayVo vo = new AlipayVo();
        vo.setOut_trade_no(UUID.randomUUID().toString().replace("-", ""));
        vo.setTotal_amount(setmeal.getPrice().toString());
        vo.setSubject("电子书阅览系统");
        vo.setProduct_code("FAST_INSTANT_TRADE_PAY"); //这个是固定的
        Long time = new Date().getTime()+5000*3;


        String json = new Gson().toJson(vo);
        System.out.println(json);

        AlipayClient alipayClient = new DefaultAlipayClient(gatewayUrL, app_id, merchant_private_key, "json",charset,alipay_public_key,sign_type);
        // 设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(return_url);
        alipayRequest.setNotifyUrl(notify_url);
        alipayRequest.setBizContent(json);
        String result = alipayClient.pageExecute(alipayRequest).getBody();
        Integer readerId = (Integer) session.getAttribute("readerId");

        //生成记录
        Rechargerecord rechargerecord = new Rechargerecord();
        rechargerecord.setReaderId(readerId);
        rechargerecord.setPrice(setmeal.getPrice());
        rechargerecord.setOutTradeNo(vo.getOut_trade_no());
        rechargerecord.setDiscount(setmeal.getDiscount());
        rechargerecord.setStatus(0);
        rechargerecord.setTradeNo(null);
        rechargerecord.setTicketNumber(setmeal.getTicketNumber());
        rechargerecord.setCreatedtime(new Date());

        rechargeRecordService.insert(rechargerecord);
        //消息队列
        try {
            MessageProperties messageProperties = new MessageProperties();
            //设置消息过期时间,这里设置的时间是15分钟
            int t = 15 * 60 *1000;
            messageProperties.setExpiration(String.valueOf(t));
            String grade = vo.getOut_trade_no();
            Message message = new Message(grade.getBytes(), messageProperties);
            message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
            //这里的key应该传死信队列绑定死信交换机的路由key,这里我们传key1
            this.send.beadSend("routing_key1",message);
//            return "succeed";
        } catch (Exception e) {
            e.printStackTrace();
//            return  "error";
        }


        return result; //这里生成一个表单，会自动提交
    }

    //支付成功返回 支付成功页面
    @RequestMapping("/reader/success")
    public String ok(AlipayVo alipayVo){

        System.out.println(alipayVo.getOut_trade_no());
        rechargeRecordService.updateRechargeByVo(alipayVo,alipayVo.getOut_trade_no(),1);


        return "redirect:/reader/ok";
    }


}