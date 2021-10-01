package com.dimsen.dailyplanet;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        getSupportActionBar().setTitle("My Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView editProfile = findViewById(R.id.txtEditProfile);
        TextView changePassword = findViewById(R.id.txtChangePassword);
        TextView exchange = findViewById(R.id.txtExchange);
        TextView favoriteNews = findViewById(R.id.txtFavoriteNews);
        TextView history = findViewById(R.id.txtHistory);
        TextView logout = findViewById(R.id.txtLogout);

        editProfile.setOnClickListener(this);
        changePassword.setOnClickListener(this);
        exchange.setOnClickListener(this);
        favoriteNews.setOnClickListener(this);
        history.setOnClickListener(this);
        logout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txtEditProfile:
                Intent intentEditProfile = new Intent(ProfileActivity.this, EditProfileActivity.class);
                startActivity(intentEditProfile);
                break;
            case R.id.txtChangePassword:
                Intent intentChangePassword = new Intent(ProfileActivity.this, ChangePasswordActivity.class);
                startActivity(intentChangePassword);
                break;
            case R.id.txtExchange:
                Intent intentExchange = new Intent(ProfileActivity.this, ExchangeActivity.class);
                startActivity(intentExchange);
                break;
            case R.id.txtFavoriteNews:
                Intent intentFavorite = new Intent(ProfileActivity.this, FavoriteActivity.class);
                startActivity(intentFavorite);
                break;
            case R.id.txtHistory:
                Intent intentHistory = new Intent(ProfileActivity.this, HistoryActivity.class);
                startActivity(intentHistory);
                break;
            case R.id.txtLogout:
                Intent intentLogout = new Intent(ProfileActivity.this, LoginActivity.class);
                startActivity(intentLogout);
                Toast.makeText(ProfileActivity.this, "Logged Out", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}