package cn.quartz.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.apache.bcel.util.ClassPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Quartz配置类
 *
 * @author: lvhao
 * @since: 2016-6-2 20:09
 */
@Configuration
@Slf4j
public class QuartzConfig {

    @Autowired
    private ExternalPathConfig externalPathConfig;

   @Resource(name = "dataSource")
   private DataSource dataSource;

    @Autowired
    private PlatformTransactionManager platformTransactionManager;

    @Autowired
    private AutowiringQuartzJobFactory autowiringQuartzJobFactory;

    @PostConstruct
    public void initDone() {
        log.info("Quartz init done...");
    }

    @Bean
    public SchedulerFactoryBean init(){
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        schedulerFactoryBean.setDataSource(dataSource);
        schedulerFactoryBean.setTransactionManager(platformTransactionManager);
        //配置定时器相关参数
        schedulerFactoryBean.setQuartzProperties(externalPathConfig.quartzCfg());
        schedulerFactoryBean.setAutoStartup(true);
        // 覆盖已存在定时任务
        schedulerFactoryBean.setOverwriteExistingJobs(true);
        //是否等待当前任务执行完成之后再停止
        schedulerFactoryBean.setWaitForJobsToCompleteOnShutdown(false);

        schedulerFactoryBean.setJobFactory(autowiringQuartzJobFactory);
        return schedulerFactoryBean;
    }

}
