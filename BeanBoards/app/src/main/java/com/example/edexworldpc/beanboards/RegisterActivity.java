package com.example.edexworldpc.beanboards;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Handler;
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

    boolean doubleBackToExitPressedOnce=false;
    EditText  edittext_email, edittext_password, edittext_username, edittext_confirm_password ;
    EditText edittext_phone, edittext_organization;
    Button btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btn_register = (Button)findViewById(R.id.btn_register);
        edittext_email = (EditText) findViewById(R.id.edittext_email);
        edittext_password = (EditText) findViewById(R.id.edittext_password);
        edittext_confirm_password = (EditText)findViewById(R.id.edittext_confirm_password);
        edittext_organization = (EditText)findViewById(R.id.edittext_organization);
        edittext_phone = (EditText)findViewById(R.id.edittext_phone);
        edittext_username = (EditText)findViewById(R.id.edittext_username);
        btn_register.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
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
                else if(edittext_username.getText().toString().equals(""))
                {
                    Toast.makeText(RegisterActivity.this, "Please Fill your Username", Toast.LENGTH_SHORT).show();
                }
                else if(edittext_confirm_password.getText().toString().equals(""))
                {
                    Toast.makeText(RegisterActivity.this, "Please Fill the confirm password field", Toast.LENGTH_SHORT).show();
                }
                else if(!edittext_confirm_password.getText().toString().equals(edittext_password.getText().toString()))
                {
                    Toast.makeText(RegisterActivity.this,"The confirm password and password field doesnot matches", Toast.LENGTH_SHORT).show();
                }
                else if(edittext_organization.getText().toString().equals(""))
                {
                    Toast.makeText(RegisterActivity.this, "Please Fill your Organization Name", Toast.LENGTH_SHORT).show();
                }
                else if(edittext_password.getText().toString().equals(""))
                {
                    Toast.makeText(RegisterActivity.this, "Please Fill your password", Toast.LENGTH_SHORT).show();
                }
                else if(edittext_phone.getText().toString().equals("") || edittext_phone.getText().toString().length()!=10)
                {
                    Toast.makeText(RegisterActivity.this, "Please Fill your correct phone number", Toast.LENGTH_SHORT).show();
                }
                else {
                    HashMap postData = new HashMap();
                    postData.put("username", edittext_username.getText().toString());
                    postData.put("email", edittext_email.getText().toString());
                    postData.put("password", edittext_password.getText().toString());
                    postData.put("confirmPassword", edittext_confirm_password.getText().toString());
                    postData.put("organization", edittext_organization.getText().toString());
                    postData.put("phone", edittext_phone.getText().toString());
                    PostResponseAsyncTask task = new PostResponseAsyncTask(RegisterActivity.this, postData);
                    task.execute("https://bbm-staging-194118.appspot.com/addUserService");
                    //task.execute("http://10.0.2.2:3000/addUserService");
                }
                break;
        }
    }

    @Override
    public void processFinish(String s) {
        try {
            JSONObject jsonObject = new JSONObject(s);
            Object jsonresult = jsonObject.get("id");
            Object jsonmessage = jsonObject.get("value");
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
