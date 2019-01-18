package com.example.lenovo.dengluxiangmu;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lenovo.dengluxiangmu.activity.LoginActivity;
import com.example.lenovo.dengluxiangmu.activity.XiangActivity;
import com.example.lenovo.dengluxiangmu.utils.GlideCircleTransform;

public class MainActivity extends AppCompatActivity {

    private ImageView img;
    private TextView tv;
    private Toolbar tool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        img = (ImageView) findViewById(R.id.img);
        Glide.with(this).load(R.drawable.a).dontAnimate().centerCrop().transform(new GlideCircleTransform(this)).into(img);
        tv = (TextView) findViewById(R.id.tv);
        tool = (Toolbar) findViewById(R.id.tool);
        tool.setTitle("");
        setSupportActionBar(tool);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        img.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent intent = new Intent(MainActivity.this, XiangActivity.class);
                intent.putExtra("img",R.drawable.a);
                intent.putExtra("user",tv.getText().toString());
                startActivityForResult(intent,1);
                return true;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode==1&&resultCode==2){
            String img = data.getStringExtra("img");
            String user = data.getStringExtra("user");
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
