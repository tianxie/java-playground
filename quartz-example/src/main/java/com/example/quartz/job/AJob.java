package com.example.quartz.job;

import com.example.quartz.service.AService;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;


public class AJob implements Job {

    @Autowired
    private AService aService;

    @Override
    public void execute(JobExecutionContext context) {
        Integer pipelineId = context.getJobDetail().getJobDataMap().getIntValue("pipelineId");
        String cron = ((CronTrigger) context.getTrigger()).getCronExpression();
        aService.printTime();
        System.out.println(pipelineId);
        System.out.println(cron);
    }
}
