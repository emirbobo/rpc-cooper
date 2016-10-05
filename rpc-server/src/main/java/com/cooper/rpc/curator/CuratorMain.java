package com.cooper.rpc.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

/**
 * Created by emirbobo on 2016/10/5.
 */
public class CuratorMain {
        static final String path = "/emirbobo/example";

    public static void main(String [] args) throws Exception {

    }

    private static void registerNode(Object o) throws Exception {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework newClient= CuratorFrameworkFactory.newClient("localhost:2181", retryPolicy);
        newClient.start();
//        newClient.create().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath(path,o);
    }


}
