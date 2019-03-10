package com.leelear.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class CopyHandler {
    private Map<String, Field> sourceMap =new HashMap<>();
    private Map<String,Field> targetMap =new HashMap<>();
    private Map<String,String> inject =new HashMap<>();
    private Object source;
    private Object target;
    private String prefixStr;//前缀字符
    private String suffixStr;//后缀字符

    public CopyHandler() {
    }

    public CopyHandler(Object source, Object target, String prefixStr, String suffixStr) {
        this.source = source;
        this.target = target;
        this.prefixStr = prefixStr;
        this.suffixStr = suffixStr;
        Init();
    }

    private void Init() {
        putMap(source,sourceMap);
        putMap(target,targetMap);
    }

    public void handler(boolean isSourceMoreThanTarget){

        initInjection(isSourceMoreThanTarget);

        for (String sFieldName : inject.keySet()) {
            try {
                Method sMethod = source.getClass().getMethod("get"+upperFirstCase(sFieldName));
                Method tMethod = target.getClass().getMethod("set"+upperFirstCase(inject.get(sFieldName)),targetMap.get(inject.get(sFieldName)).getType());
                tMethod.invoke(target,sMethod.invoke(source));
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("发生了错误");
            }

        }
    }

    private void initInjection(boolean isSourceMoreThanTarget){
        if(!isSourceMoreThanTarget){
            for (String fieldName : sourceMap.keySet()) {
                String name= prefixStr + upperFirstCase(fieldName) + upperFirstCase(suffixStr);
                if (targetMap.containsKey(name)) {
                    inject.put(fieldName, name);
                }
            }
        }else {
            for (String fieldName : targetMap.keySet()) {
                String name= prefixStr + upperFirstCase(fieldName) + upperFirstCase(suffixStr);
                if (sourceMap.containsKey(name)) {
                    inject.put(name, fieldName);
                }
            }
        }
    }

    private  void putMap(Object obj, Map<String, Field> Map) {
        Field[] sFields = obj.getClass().getDeclaredFields();
        for (Field sField : sFields) {
            Map.put(sField.getName(), sField);
        }
    }

    public static String upperFirstCase(String str){
        if(str==null||"".equals(str)){return null;}
        char[] chars = str.toCharArray();
        chars[0]-=32;
        return String.valueOf(chars);
    }
}
