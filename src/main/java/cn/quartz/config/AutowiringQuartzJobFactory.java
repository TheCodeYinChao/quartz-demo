package cn.quartz.config;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Admin on 2018/6/16.
 */
@Component
public class AutowiringQuartzJobFactory extends SpringBeanJobFactory implements ApplicationContextAware {
    private transient AutowireCapableBeanFactory beanFactory;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {

        this.beanFactory = context.getAutowireCapableBeanFactory();
    }

    /**
     * 将quartz纳入spring管理
     * @param bundle
     * @return
     * @throws Exception
     */
    @Override
    protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
        Object job = super.createJobInstance(bundle);
        beanFactory.autowireBean(job);
        return job;
    }

    
    @Override
    public void setIgnoredUnknownProperties(String... ignoredUnknownProperties) {
        List<String> ignoreList = Arrays.asList(ignoredUnknownProperties);
        ignoreList.add("applicationContext");
        super.setIgnoredUnknownProperties(ignoreList.stream().toArray(String[]::new));
    }
}