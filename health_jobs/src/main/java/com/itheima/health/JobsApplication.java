package com.itheima.health;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class JobsApplication {
    public static void main(String[] args) throws Exception {
        new ClassPathXmlApplicationContext("classpath:spring-jobs.xml");
        System.in.read();
    }
}