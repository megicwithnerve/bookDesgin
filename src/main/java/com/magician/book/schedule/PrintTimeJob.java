package com.magician.book.schedule;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PrintTimeJob extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        String msg = (String) jobExecutionContext.getJobDetail().getJobDataMap().get("msg");
        System.out.println("current time :"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "---" + msg);

    }
}
