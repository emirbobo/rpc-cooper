package com.testcode;

import com.cooper.rpc.body.TestInterface;

import java.util.Date;

/**
 * Created by emirbobo on 2016/10/3.
 */
public class PrintTimeImpl implements TestInterface {
    @Override
    public void printTime(String ss) {
        System.out.println("now time is :" + new Date().toGMTString() + "&params"+ss);
    }
}
