package com.Auth.Util;

import java.lang.reflect.Field;

/**
 * Created by Jiangtenglong on 2016/8/22.
 */
public class ExcelUtils {
      public static String[] getAttribute(String  className){

          className="com.Auth.po."+className;
          String []attribute=null;
          try {
              Class  clazz  =Class.forName(className);
              Field[] declaredFields = clazz.getDeclaredFields();
              attribute=new  String[declaredFields.length];
              for (int i =0;i<declaredFields.length;i++){

                  String []  fild=declaredFields[i].toString().split(className+".");
                  attribute[i]=fild[1];
              }
          } catch (ClassNotFoundException e) {
              e.printStackTrace();
          }
          return  attribute;
      }


    public static  void  main(String [] args){

        String [] s=ExcelUtils.getAttribute("Function");
        for (int i=0;i<s.length;i++){
            System.out.println(s[i]);
        }
    }
}
