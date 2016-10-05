package com.dubbo.example;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by emirbobo on 2016/10/5.
 */
public class Main {

    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext(
                new String[] {"applicationProvider.xml"});
        context.start();
        System.out.println("按任意键退出");
        System.in.read();
    }
}
