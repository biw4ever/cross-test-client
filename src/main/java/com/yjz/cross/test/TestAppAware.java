package com.yjz.cross.test;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.yjz.cross.client.init.CrossClientInitializer;
import com.yjz.cross.config.Configuration;

public class TestAppAware implements ApplicationContextAware
{
    
    private static final Logger logger = LoggerFactory.getLogger(TestAppAware.class);
    
    @Value("#{cross['zk.address']}")
    private String zkAddress;
    
    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
        throws BeansException
    {
        // 遍历容器中所有Bean，获取其中标注了CrossReference的字段，将其替换成代理对象，此代理对象的方法调用将通过Rpc的方式调用服务端来实现
        String[] beanNames = applicationContext.getBeanDefinitionNames();
        List<Object> beanObjectList = new ArrayList<>();
        for (String beanName : beanNames)
        {
            Object obj = applicationContext.getBean(beanName);
            beanObjectList.add(obj);
        }
        
        Configuration conf = new Configuration();
        conf.addRegistry(zkAddress);
        
        CrossClientInitializer.bootStrap(beanObjectList, conf);
        
        logger.info("cross client is booted"); 
    }
    
}
