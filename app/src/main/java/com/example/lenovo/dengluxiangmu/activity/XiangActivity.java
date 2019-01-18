package com.example.lenovo.dengluxiangmu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.lenovo.dengluxiangmu.R;
import com.example.lenovo.dengluxiangmu.utils.GlideCircleTransform;

import java.io.Serializable;

public class XiangActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView img;
    private EditText et_name;
    private Button bt_update;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiang);
        initView();
    }

    private void initView() {
        img = (ImageView) findViewById(R.id.img);
        et_name = (EditText) findViewById(R.id.et_name);
        bt_update = (Button) findViewById(R.id.bt_update);
        intent = getIntent();
        Serializable img = intent.getSerializableExtra("img");
        String user = intent.getStringExtra("user");
        Glide.with(this).load(R.drawable.a).dontAnimate().centerCrop().transform(new GlideCircleTransform(this)).into(this.img);
        et_name.setText(user);
        bt_update.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_update:
                String s = et_name.getText().toString();
                et_name.setText(s);
                setResult(2,intent);
                finish();
                break;
        }
    }
}
