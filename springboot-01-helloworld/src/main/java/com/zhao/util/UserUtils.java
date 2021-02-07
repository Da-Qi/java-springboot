package com.zhao.util;


import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UserUtils {
    // 以用户id生成文件名，使服务器图片不会存储用户使用过的所有图片
    public String getImageFileName(String filename) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        md5.update(String.valueOf(filename).getBytes());
        return new BigInteger(1, md5.digest()).toString(16);
    }
}
