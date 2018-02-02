package com.example.edexworldpc.beanboards;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kosalgeek.asynctask.AsyncResponse;
import com.kosalgeek.asynctask.PostResponseAsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener, AsyncResponse {


    EditText  edittext_email, edittext_password;
    Button btn_register;
    DatePickerDialog datePickerDialog;;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btn_register = (Button)findViewById(R.id.btn_register);
        edittext_email = (EditText) findViewById(R.id.edittext_email);
        edittext_password = (EditText) findViewById(R.id.edittext_password);

        btn_register.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btn_register:
                if(edittext_email.getText().toString().equals("") || !(edittext_email.getText().toString().contains("@")))
                {
                    Toast.makeText(RegisterActivity.this, "Please Fill your correct email address", Toast.LENGTH_SHORT).show();
                }
                else if(edittext_password.getText().toString().equals(""))
                {
                    Toast.makeText(RegisterActivity.this, "Please Fill your password", Toast.LENGTH_SHORT).show();
                }
                else {
                    HashMap postData = new HashMap();
                    postData.put("email", edittext_email.getText().toString());
                    postData.put("password", edittext_password.getText().toString());
                    PostResponseAsyncTask task = new PostResponseAsyncTask(RegisterActivity.this, postData);
                    task.execute("http://10.0.2.2:3000/addUserService");
                }
                break;
        }
    }

    @Override
    public void processFinish(String s) {
        try {
            JSONObject jsonObject = new JSONObject(s);
            Object jsonresult = jsonObject.get("id");
            Object jsonmessage = jsonObject.get("message");
            String resultid = String.valueOf(jsonresult);
            String resultmessage = String.valueOf(jsonmessage);

            if(resultid.equalsIgnoreCase("0")) {
                Toast.makeText(RegisterActivity.this, resultmessage, Toast.LENGTH_SHORT).show();
            }
            else if(resultid.equalsIgnoreCase("1"))
            {
                Toast.makeText(RegisterActivity.this, "Please login to continue .. ", Toast.LENGTH_LONG).show();
                startActivity(new Intent(this, LoginActivity.class));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
