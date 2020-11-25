package com.example.quartz.web;

import com.example.quartz.QuartzService;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobResource {
    @Autowired
    QuartzService quartzService;

    @PostMapping("/schedule")
    public void schedule(@RequestBody Param param) throws SchedulerException {
        quartzService.schedule(param.getJobName(), param.getCron());

    }

    @PostMapping("/reschedule")
    public void reschedule(@RequestBody Param param) throws SchedulerException {
        quartzService.reschedule(param.getJobName(), param.getCron());
    }

    @PostMapping("/unschedule")
    public void unschedule(@RequestBody Param param) throws SchedulerException {
        quartzService.unschedule(param.getJobName());
    }
}
