package com.cslg.utils;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import org.springframework.stereotype.Component;

/**
 * Md5摘要加密
 */
public class MD5Utils {

    /**
     * MD5摘要加密，直接用Hutool的工具方法
     *
     * @param pwd
     * @return
     */
    public static String Encrypt(String pwd) {
        Digester md5 = new Digester(DigestAlgorithm.MD5);
        String s = md5.digestHex(pwd);
        return s;
    }
}
