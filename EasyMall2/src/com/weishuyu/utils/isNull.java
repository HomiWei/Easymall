package com.weishuyu.utils;

public class isNull {
public  static boolean Null(String name){
        if ("".equals(name)){
            return false;
        }else if (name==null){
            return false;
        }else {
            return true;
        }
}
}
