package com.leelear.utils;

/**
 * 在属性名字不同，但用途相同的情况下
 */


import java.lang.reflect.InvocationTargetException;



public class BeanUtil {


    public static void copyProperties(Object source, Object target,String prefixStr)  {
        copyProperties(source,target,prefixStr,"",true);

    }
    public static void copyProperties(Object source, Object target,String prefixStr,String suffixStr)  {
        copyProperties(source,target,prefixStr,suffixStr,true);

    }

    public static void copyProperties(Object source, Object target,String prefixStr,String suffixStr,boolean isSourceMoreThanTarget)  {
        prefixStr=checkStr(prefixStr);
        suffixStr=checkStr(suffixStr);
        try {
            CopyHandler copyHandler = new CopyHandler(source, target, prefixStr,suffixStr);
            copyHandler.handler(isSourceMoreThanTarget);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("发生了错误");
        }

    }

    private static String checkStr(String str) {
        if(str==null){
            return "";
        }else{
            return str.trim();
        }
    }


}
