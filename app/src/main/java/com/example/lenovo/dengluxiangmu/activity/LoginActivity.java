package com.example.lenovo.dengluxiangmu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.lenovo.dengluxiangmu.MainActivity;
import com.example.lenovo.dengluxiangmu.R;
import com.example.lenovo.dengluxiangmu.api.MyServer;
import com.example.lenovo.dengluxiangmu.bean.Login;
import com.example.lenovo.dengluxiangmu.utils.GlideCircleTransform;

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

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView img;
    private EditText et_zhanghao;
    private EditText et_password;
    private Button bt_denglu;
    private Button bt_zhuce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        img = (ImageView) findViewById(R.id.img);
        Glide.with(this).load(R.drawable.a).dontAnimate().centerCrop().transform(new GlideCircleTransform(this)).into(img);
        et_zhanghao = (EditText) findViewById(R.id.et_zhanghao);
        et_password = (EditText) findViewById(R.id.et_password);
        bt_denglu = (Button) findViewById(R.id.bt_denglu);
        bt_zhuce = (Button) findViewById(R.id.bt_zhuce);

        bt_denglu.setOnClickListener(this);
        bt_zhuce.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_denglu:
                getLogin();
                break;
            case R.id.bt_zhuce:
                Intent intent = new Intent(LoginActivity.this,ZhuceActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void getLogin() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MyServer.URL3)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        MyServer myServer = retrofit.create(MyServer.class);
        Map<String,String> map = new HashMap<>();
        map.put("username",et_zhanghao.getText().toString());
        map.put("password",et_password.getText().toString());
        Observable<Login> myServer3 = myServer.get3(map);
        myServer3.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Login>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Login value) {
                        int code = value.getCode();
                        String ret = value.getRet();
                        if (code==200){
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_LONG).show();
                        }else {
                            Toast.makeText(LoginActivity.this,ret,Toast.LENGTH_LONG).show();
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
