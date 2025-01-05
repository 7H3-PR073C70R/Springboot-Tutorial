package com.protector.Spring1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.protector.config.AppConfig;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	
    	ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
    	
   	 	Alien obj = context.getBean(Alien.class);
   	 	System.out.println(obj.getAge());
   	 	obj.code();
    	
    	Desktop desktop = context.getBean("desktop" ,Desktop.class);
    	desktop.compile();
    	
    	
    	
    	
    	
    	
    	
    	
//    	ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
//        Alien obj = context.getBean("alien", Alien.class);
//        System.out.println(obj.getAge());
//        obj.code();
//        
//        
//        Computer desktop = context.getBean(Desktop.class);
//        desktop.compile();
       
    }
}

