package com.vince.utils;

/**
 * 字符串为空验证工具
 */
public class EmptyUtils {

    public static boolean isEmpty(String s){
        if(null==s || "".equals(s)){
            return true;
        }else{
            return false;
        }
    }
}
