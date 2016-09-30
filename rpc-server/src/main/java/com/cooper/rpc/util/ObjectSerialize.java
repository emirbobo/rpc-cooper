package com.cooper.rpc.util;

import java.io.*;

/**
 * Created by xijingbo on 2016-09-30.
 */
public class ObjectSerialize {

    public static byte[] serialize(Object object) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            //将对象写入到字节数组中进行序列化
            objectOutputStream.writeObject(object);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static Object deSerialize(byte[] bytes) {
        //将二进制数组导入字节数据流中
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        try {
            //将字节数组流转化为对象
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            return objectInputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
