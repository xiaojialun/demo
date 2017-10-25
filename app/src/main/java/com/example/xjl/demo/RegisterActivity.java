package com.example.xjl.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.xjl.demo.Cilent.RegisterClient;


/**
 * Created by xjl on 2017/10/19.
 */

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText  nameTv,usernameTv,passwordTv,telTv;
    private Button registerBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
        initClient();
    }

    private void initView() {
        nameTv=(EditText)findViewById(R.id.name);
        usernameTv=(EditText)findViewById(R.id.username);
        passwordTv=(EditText)findViewById(R.id.password);
        telTv=(EditText)findViewById(R.id.tel);
        registerBtn=(Button)findViewById(R.id.registerBtn);
    }

    private void initClient() {
        registerBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(nameTv.getText()==null||usernameTv.getText()==null||passwordTv.getText()==null||telTv.getText()==null){

        }else {
            new RegisterClient( nameTv.getText().toString(),usernameTv.getText().toString(),passwordTv.getText().toString(),telTv.getText().toString(),RegisterActivity.this);
        }
    }
}

