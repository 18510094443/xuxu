package com.example.lenovo.dengluxiangmu.api;

import com.example.lenovo.dengluxiangmu.bean.Login;
import com.example.lenovo.dengluxiangmu.bean.Yanzheng;
import com.example.lenovo.dengluxiangmu.bean.Zhuce;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface MyServer {

    //验证码
    String URl1 = "http://yun918.cn/study/public/index.php/";
    @GET("verify")
    Observable<Yanzheng> get1();
    //注册
    String URl2 = "http://yun918.cn/study/public/";
    @POST("register")
    @FormUrlEncoded
    Observable<Zhuce>get2(@FieldMap Map<String, Object> map);

    //登陆
    //http://yun918.cn/study/public/index.php/login
    String URL3="http://yun918.cn/study/public/index.php/";
    @POST("login")
    @FormUrlEncoded
    Observable<Login> get3(@FieldMap Map<String,String> map);

}
