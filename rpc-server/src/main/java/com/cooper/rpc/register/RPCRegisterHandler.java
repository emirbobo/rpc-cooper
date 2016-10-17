package com.cooper.rpc.register;

import com.cooper.rpc.body.RequestBody;
import org.apache.commons.lang.reflect.MethodUtils;
import org.apache.zookeeper.CreateMode;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by emirbobo on 2016/10/3.
 */
public enum RPCRegisterHandler {

    registor;

    ConcurrentHashMap<String,Object> classRegisterMap = new ConcurrentHashMap<>();

    public void register( Class clazz){
        if(clazz == null){
            System.out.println("class cannot be null..");
            return;
        }
        Class []inters = clazz.getInterfaces();
        for(Class c : inters){
            if(c==null){
                continue;
            }
            registor.classRegisterMap.put(c.getCanonicalName(),clazz);
        }
    }

    public Object findResult(RequestBody className) throws Throwable {
        return reflect(className);
//        Class clazz = registor.classRegisterMap.get(className);
//        ProxyHandler proxyHandler = new ProxyHandler(clazz);
//        Object o = Proxy.newProxyInstance(clazz.getClassLoader(),new Class[]{clazz},proxyHandler);
//        return o;
    }

    private Object reflect(RequestBody request) throws Throwable {
        String className = request.getClassName();
        Object serviceBean = registor.classRegisterMap.get(className);
        if(serviceBean == null){
            System.out.println("cannot find serviceBean for" + className+",invoke failed!");
            return null;
        }
        String methodName = request.getMethodName();
        Object[] parameters = request.getParams();
        System.out.println(String.format("invoke class : %s ,method : %s ",className,methodName));
        return MethodUtils.invokeMethod(serviceBean, methodName, parameters);
    }

//    private static class ProxyHandler implements InvocationHandler {
//
//        Object proxied;
//        public ProxyHandler(Object proxied){
//            this.proxied = proxied;
//        }
//        @Override
//        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//            return method.invoke(proxied,args);
//        }
//    }
}
