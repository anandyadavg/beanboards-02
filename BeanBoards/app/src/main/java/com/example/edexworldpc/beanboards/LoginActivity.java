package com.example.edexworldpc.beanboards;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    Button loginBtn;
    TextView linkRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginBtn = (Button)findViewById(R.id.btn_login);
        linkRegister = (TextView)findViewById(R.id.linkRegister);

        loginBtn.setOnClickListener(this);
        linkRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btn_login:
                startActivity(new Intent(this, MainActivity.class));
                break;

            case R.id.linkRegister:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
        }
    }
}
