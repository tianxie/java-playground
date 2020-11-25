package com.example.quartz;

import com.example.quartz.job.AJob;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import static org.quartz.JobKey.jobKey;
import static org.quartz.TriggerKey.triggerKey;

@Service
public class QuartzService {
    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    public void schedule(String jobName, String cron) throws SchedulerException {
        JobDetail jobDetail = JobBuilder.newJob(AJob.class).withIdentity(jobName)
                .usingJobData("pipelineId", 1)
                .storeDurably().build();

        Trigger trigger = TriggerBuilder.newTrigger().forJob(jobDetail)
                .withIdentity(jobName)
                .startNow()
                .withSchedule(CronScheduleBuilder.cronSchedule(cron).withMisfireHandlingInstructionDoNothing())
                .build();

        schedulerFactoryBean.getScheduler().scheduleJob(jobDetail, trigger);
    }

    public void reschedule(String jobName, String cron) throws SchedulerException {

        Trigger oldTrigger = schedulerFactoryBean.getScheduler().getTrigger(triggerKey(jobName));

        Trigger myNewTrigger = TriggerBuilder
                .newTrigger()
                .forJob(jobName)
                .withIdentity(jobName)
                .withSchedule(CronScheduleBuilder.cronSchedule(cron).withMisfireHandlingInstructionDoNothing())
                .build();

        schedulerFactoryBean.getScheduler().rescheduleJob(oldTrigger.getKey(), myNewTrigger);
    }

    public void unschedule(String jobName) throws SchedulerException {
//        schedulerFactoryBean.getScheduler().unscheduleJob(triggerKey(jobName));
        schedulerFactoryBean.getScheduler().deleteJob(jobKey(jobName));
    }
}
