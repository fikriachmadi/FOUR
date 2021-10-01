package com.dimsen.dailyplanet.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.dimsen.dailyplanet.HistoryActivity;
import com.dimsen.dailyplanet.R;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ImageView imgDetailNews = findViewById(R.id.imgDetailNews);
        TextView detailTitle = findViewById(R.id.txtDetailTitle);
        TextView detailPublishedAt = findViewById(R.id.txtDetailPublishedAt);
        TextView detailSource = findViewById(R.id.txtDetailSource);
        TextView detailAuthor = findViewById(R.id.txtDetailAuthor);


        Intent intent = getIntent();
        String contentUrl = intent.getStringExtra("url");
        String imgDetail = intent.getStringExtra("img");
        String title = intent.getStringExtra("title");
        String publishedAt = intent.getStringExtra("published");
        String source = intent.getStringExtra("source");
        String author = intent.getStringExtra("author");

        RequestOptions requestOptions = new RequestOptions();
        Glide.with(this)
                .load(imgDetail)
                .apply(requestOptions)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imgDetailNews);

        detailTitle.setText(title);
        detailPublishedAt.setText(publishedAt);
        detailSource.setText(source);
        detailAuthor.setText(author);

        initWebView(contentUrl);
    }

    private void initWebView(String url){
        WebView webView = findViewById(R.id.txtDetailContent);
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().supportZoom();
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);
    }
}