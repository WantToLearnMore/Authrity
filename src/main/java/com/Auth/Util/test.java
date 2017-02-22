package com.Auth.Util;

/**
 * Created by Jiangtenglong on 2016/9/1.
 */
public class test {
    char e;
    public  char  findTheDifference(String s,String t){

      char  []temp=s.toCharArray();
      char  []temp2=t.toCharArray();

    if((temp.length<=temp2.length)&&(temp.length!=0)){
        for(int i=0;i<temp.length;i++){
            if(temp[i]!=temp2[i]){
                e=temp2[i];
                i=temp.length;
            }else if((i==temp.length-1)&&(temp[i]==temp2[i])){
                    e=temp2[i+1];
            }
        }
    }else if (temp.length==0){
        e=temp2[0];

    }else if(temp2.length==0){
        e=temp[0];
    }else {

        for(int i=0;i<temp2.length;i++){
            if(temp[i]!=temp2[i]){
                e=temp[i];
                i=temp2.length;
            }else if((i==temp2.length-1)&&(temp[i]==temp2[i])){
                e=temp[i+1];
            }
        }
    }

        return  e;
    }


    public   static   void   main(String [] args){

        test  t=new test();
        System.out.println(t.findTheDifference("adadafada","adadsfwsdaw"));

    }
}
