package com.example.asus.rikao20181007.data;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class OkHttpUtil {


    private static OkHttpUtil okHttpUtil;
    private  OkHttpClient okHttpClient;


    private OkHttpUtil(){
      if (null==okHttpClient){
          synchronized (OkHttpClient.class){
              if (null==okHttpClient){
                  okHttpClient = new OkHttpClient.Builder().build();
              }
          }
      }
  }

    public static OkHttpUtil getInstance(){
       if (null==okHttpUtil){
           synchronized (OkHttpUtil.class){
               if (null==okHttpUtil){
                   okHttpUtil = new OkHttpUtil();
               }
           }
       }
        return okHttpUtil;
    }


    public void  get(String stringurl, Callback callback){
        Request request = new Request.Builder().url(stringurl).build();
        okHttpClient.newCall(request).enqueue(callback);
    }
}
