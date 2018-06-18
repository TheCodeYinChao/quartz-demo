package cn.quartz.schedule.listener;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;

/**
 * Created by Admin on 2018/6/18.
 */
public class TestJobListener implements JobListener {
    @Override
    public String getName() {
        return null;
    }

    @Override
    public void jobToBeExecuted(JobExecutionContext jobExecutionContext) {
        System.out.println(jobExecutionContext.getJobDetail().toString());
    }

    @Override
    public void jobExecutionVetoed(JobExecutionContext jobExecutionContext) {

    }

    @Override
    public void jobWasExecuted(JobExecutionContext jobExecutionContext, JobExecutionException e) {

    }
}
