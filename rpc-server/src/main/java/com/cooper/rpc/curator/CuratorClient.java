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
        registerNode(newClient,o,host,port);
    }

    private static void registerNode(CuratorFramework newClient ,Class o,String host,int port) throws Exception {
        Class []inters = o.getInterfaces();
        for(Class c : inters){
            if(c==null){
                continue;
            }
            String classPath =root+ c.getCanonicalName();
            System.out.println("register node:"+classPath);
            newClient.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath(classPath,createContent(host,port,c.getMethods(),c.getName()).getBytes());
        }
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
