package com.cooper.rpc.zk;

/**
 * Created by emirbobo on 2016/10/5.
 */
public class ZKDiscoveryServer {

    static final String root = "/emirbobo";

    String path;

    String host;

    public ZKDiscoveryServer(String host, String path){
        this.host = host;
        this.path = path;
    }
}
