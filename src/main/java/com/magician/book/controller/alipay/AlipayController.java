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
import com.magician.book.pojo.AlipayRefund;
import com.magician.book.pojo.AlipayVo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @Title: AlipayController.java
 * @Package cn.trmap.tdcloud.pay
 * @Description: 支付宝后台接口
 * @author nelson
 * @date 2018年3月21日 下午5:31:03
 */
@RequestMapping("alipay")
@RestController
public class AlipayController {

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
            //处理你的业务逻辑，更细订单状态等
            return ("success");
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
        System.out.println(map);

        boolean signVerified = false;
        try {
            signVerified = AlipaySignature.rsaCheckV1(map, alipay_public_key, charset, sign_type);
            signVerified = true;
        } catch (AlipayApiException e) {
            e.printStackTrace();
            return ("fail");// 验签发生异常,则直接返回失败
        }
        if (signVerified) {
            return ("alipay/success");
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


}