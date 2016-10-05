package com.dubbo.example;

/**
 * Created by emirbobo on 2016/10/5.
 */
public class ProcessDataImpl implements IProcessData {
    public String hello(String name) {
        System.out.println(name);
        return "hello : " + name;
    }

    public String hello1(String name) {
        System.out.println(name);
        return "hello : " + name;
    }
}
