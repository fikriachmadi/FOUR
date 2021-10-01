package com.dimsen.dailyplanet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dimsen.dailyplanet.main.MainActivity;

import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button button = findViewById(R.id.btnLogin);
        button.setOnClickListener(this);

        TextView SignUp = findViewById(R.id.sign_up);
        TextView ForgotPass = findViewById(R.id.forgot_password);

        SignUp.setOnClickListener(this);
        ForgotPass.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnLogin:
                Intent intentLogin = new Intent(LoginActivity.this, ProfileActivity.class);
                startActivity(intentLogin);
                break;
            case R.id.sign_up:
                Intent intentSignUp = new Intent(LoginActivity.this, sign_up.class);
                startActivity(intentSignUp);
                break;
            case R.id.forgot_password:
                Intent intentForgotPassword = new Intent(LoginActivity.this, forgot_password_activity.class);
                startActivity(intentForgotPassword);
        }
    }
}