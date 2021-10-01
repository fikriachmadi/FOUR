package com.dimsen.dailyplanet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;

import com.dimsen.dailyplanet.main.MainActivity;

public class Withdrawal extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdrawal);

        getSupportActionBar().setTitle("Withdrawal");

        Button button = findViewById(R.id.button_back_home);
        button.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(Withdrawal.this, MainActivity.class);
        startActivity(intent);

    }
}