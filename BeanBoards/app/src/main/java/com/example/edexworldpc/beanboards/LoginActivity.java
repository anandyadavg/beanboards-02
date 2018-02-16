package com.example.edexworldpc.beanboards;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.kosalgeek.asynctask.AsyncResponse;
import com.kosalgeek.asynctask.PostResponseAsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, AsyncResponse {

    EditText edittext_email, edittext_password;
    Button btn_login;
    TextView linkRegister;
    SharedPreferences sharedpreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn_login = (Button)findViewById(R.id.btn_login);
        edittext_email = (EditText)findViewById(R.id.edittext_email);
        edittext_password = (EditText)findViewById(R.id.edittext_password);
        linkRegister = (TextView)findViewById(R.id.linkRegister);

        sharedpreferences = getSharedPreferences("LoginPreferences", Context.MODE_PRIVATE);

        btn_login.setOnClickListener(this);
        linkRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btn_login:
                if(edittext_email.getText().toString() == "")
                    Toast.makeText(this, "Email address field empty", Toast.LENGTH_SHORT).show();
                else if(!edittext_email.getText().toString().contains("@"))
                    Toast.makeText(this,"Please put a valid email address", Toast.LENGTH_SHORT).show();
                else if(edittext_password.getText().toString() == "")
                    Toast.makeText(this,"Password field is empty", Toast.LENGTH_SHORT).show();
                else
                {
                    HashMap postData = new HashMap();
                    postData.put("email", edittext_email.getText().toString());
                    postData.put("password", edittext_password.getText().toString());
                    PostResponseAsyncTask task = new PostResponseAsyncTask(LoginActivity.this, postData);
                    task.execute("https://bbm-staging-194118.appspot.com/loginService");
                }

                break;

            case R.id.linkRegister:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
        }
    }

    @Override
    public void processFinish(String s) {
        try {
            JSONObject jsonObject = new JSONObject(s);
            Object jsonresult = jsonObject.get("id");
            Object jsonmessage = jsonObject.get("value");

            String result = String.valueOf(jsonresult);
            String message = String.valueOf(jsonmessage);

            if(result.equalsIgnoreCase("0")) {
                Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
            }
            else if(result.equalsIgnoreCase("1"))
            {
                Object  email = jsonObject.get("email");
                String jsonemail = String.valueOf(email);

                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString("email",jsonemail);
                editor.commit();

                Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this,MainActivity.class));
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

    }
}
