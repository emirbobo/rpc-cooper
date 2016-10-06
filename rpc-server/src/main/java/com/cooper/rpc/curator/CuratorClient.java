package com.cooper.rpc.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

import java.lang.reflect.Method;

/**
 * Created by emirbobo on 2016/10/5.
 */
public class CuratorClient {

    static final String root = "/emirbobo/";

    public static void main(String [] args) throws Exception {

    }

    public static void createNode(String host,int port,Class o) throws Exception {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework newClient= CuratorFrameworkFactory.newClient(host+":"+port, retryPolicy);
        newClient.start();
        String classPath =root+ o.getCanonicalName();
        newClient.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath(classPath,createContent(host,port,o.getMethods(),o.getName()).getBytes());
    }

    private static String createContent(String host, int port, Method[]methods, String className){
        StringBuffer sb = new StringBuffer();
        sb.append(host).append("&").append(port).append("&").append(className).append("&");
        for(int i=0;i<methods.length;i++){
            Method method = methods[i];
            if(i>0){
                sb.append(",");
            }
            sb.append(method.getName());
        }
        sb.append("&").append(System.currentTimeMillis());
        return sb.toString();
    }
}
