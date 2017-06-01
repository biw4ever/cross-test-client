package com.yjz.cross.test.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ClientBoot
{
    private static final Logger logger = LoggerFactory.getLogger(ClientBoot.class);
    
    @SuppressWarnings("resource")
    public static void main(String[] args)
        throws InterruptedException
    {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:applicationContext.xml");
        while (true)
        {
            try
            {  
                String userName = context.getBean(ClientService.class).getUserName();
                logger.error(userName);
                Thread.sleep(2000);
            }
            catch (Exception e)
            {
                logger.error(e.getMessage(), e);
                Thread.sleep(2000);
                
            }
        }
        
    }
}
