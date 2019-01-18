package com.example.lenovo.dengluxiangmu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.dengluxiangmu.R;
import com.example.lenovo.dengluxiangmu.api.MyServer;
import com.example.lenovo.dengluxiangmu.bean.Yanzheng;
import com.example.lenovo.dengluxiangmu.bean.Zhuce;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ZhuceActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_zhanghao;
    private EditText et_password;
    private EditText et_phone;
    private EditText et_yanzhenma;
    private TextView tv_yanzhenma;
    private Button bt_zhuce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuce);
        initView();
        isPost();
    }
    private void initView() {
        et_zhanghao = (EditText) findViewById(R.id.et_zhanghao);
        et_password = (EditText) findViewById(R.id.et_password);
        et_phone = (EditText) findViewById(R.id.et_phone);
        et_yanzhenma = (EditText) findViewById(R.id.et_yanzhenma);
        tv_yanzhenma = (TextView) findViewById(R.id.tv_yanzhenma);
        bt_zhuce = (Button) findViewById(R.id.bt_zhuce);

        bt_zhuce.setOnClickListener(this);
    }

    private void isPost() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MyServer.URl1)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        MyServer myServer = retrofit.create(MyServer.class);
        Observable<Yanzheng> myServer1 = myServer.get1();
        myServer1.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Yanzheng>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Yanzheng value) {
                        Log.e("tag","===>"+value.toString());
                        tv_yanzhenma.setText(value.getData());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_zhuce:
                isZhuce();
                break;
        }
    }

    private void isZhuce() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MyServer.URl2)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        MyServer myServer = retrofit.create(MyServer.class);
        Map<String,Object> map = new HashMap<>();
        map.put("username", et_zhanghao.getText().toString());
        map.put("password", et_password.getText().toString());
        map.put("phone", et_phone.getText().toString());
        map.put("verify",et_yanzhenma.getText().toString());
        Observable<Zhuce> myServer2 = myServer.get2(map);
        myServer2.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Zhuce>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Zhuce value) {
                        int code = value.getCode();
                        String ret = value.getRet();
                        if (code==200){
                           /* String s = et_yanzhenma.getText().toString();
                            String s1 = et_password.getText().toString();
                            String s2 = et_phone.getText().toString();*/
                            Intent intent = new Intent(ZhuceActivity.this,LoginActivity.class);
                            startActivity(intent);
                            Toast.makeText(ZhuceActivity.this,"注册成功",Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(ZhuceActivity.this,ret,Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

}
