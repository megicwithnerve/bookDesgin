package com.magician.book.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

//@Component
public class OrderPackageScheduleTask {
    //添加定时任务15分钟操作一次数据库，cron的表达式生成参考：http://cron.qqe2.com/ =====写法也可以直接指定时间间隔，例如：5秒：@Scheduled(fixedRate=5000)====
//    1.fixedRate：定义一个按一定频率执行的定时任务，
//            2.fixedDelay：定义一个按一定频率执行的定时任务，与上面不同的是，改属性可以配合initialDelay， 定义该任务延迟执行时间。
//            3.cron：通过表达式来配置任务执行时间
    @Scheduled(cron = "0 0/15 * * * ?")
    private void configureTasks() {
        //定时执行的，业务逻辑就在这里。自己测试的话，就可以只写几个，sout，然后定时时间写短点方便测试即可
        //查询未支付订单
//        List<MmOrderPackage> orderPackageNotPay = orderPackageService.getOrderPackageNotPay();
//        for (MmOrderPackage orderPackage:orderPackageNotPay) {
//            //如果下单时间，距离现在超过30分钟，就将订单状态改为3.已过期
//            if(Long.valueOf(TimeService.currentTimeMilliStr()) - Long.valueOf(orderPackage.getCtime()) > TimeService.THIRTY_MILLISECONDS){
//                System.out.println("当前更新时间是："+TimeService.currentTimeMilliStr());
//                System.out.println("还未完成支付，已过期的订单：id:"+orderPackage.getId()+"编号："+orderPackage.getOid()+"订单名:"+orderPackage.getTitle());
//                orderPackageService.changeOrderPackagePayToOverdue(orderPackage.getId());
//            }
//        }


    }

}
