package com.dimsen.dailyplanet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class ExchangeActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchange);

        getSupportActionBar().setTitle("Exchange");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button button = findViewById(R.id.button_withdrawal);
        button.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(ExchangeActivity.this, Withdrawal.class);
        startActivity(intent);

    }
}