package com.Auth.checkHome;

import java.net.URL;

/**
 * Created by ASUS! on 2016/11/21.
 */
public class test1 {
    public static void main(String [] args){

        String a=new String("A");
        String b=new String("B");
        change(a,b);
        System.out.println("a:"+a+"   b:"+b);
        int i=0;
        i=i++;
        System.out.println(i);
        System.out.println(i++);
        System.out.println(i);

        String c="中";
        System.out.println(a.getBytes().length);
        System.out.println(c.getBytes().length);





        URL[] urls = sun.misc.Launcher.getBootstrapClassPath().getURLs();
        for (int j = 0; j < urls.length; j++) {
            System.out.println(urls[i].toExternalForm());
        }

    }


    public static void change(String x,String y){
        x=x+y;
        y=x;
    }






}
