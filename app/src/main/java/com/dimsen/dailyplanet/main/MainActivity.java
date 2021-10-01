package com.dimsen.dailyplanet.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.dimsen.dailyplanet.CategoryActivity;
import com.dimsen.dailyplanet.LoginActivity;
import com.dimsen.dailyplanet.ProfileActivity;
import com.dimsen.dailyplanet.R;
import com.dimsen.dailyplanet.data.api.Api;
import com.dimsen.dailyplanet.data.api.ApiClient;
import com.dimsen.dailyplanet.data.model.Articles;
import com.dimsen.dailyplanet.data.model.News;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public static final String API_KEY = "23b92eb137c74f6eab5f15055aa1de69";
    private RecyclerView recyclerView;
    private List<Articles> articles = new ArrayList<>();
    private NewsAdapter newsAdapter;
    private String login = "failed";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.txtTest);

        Intent intent = getIntent();
        login = intent.getStringExtra("done");

        textView.setText(login);


        getSupportActionBar().setTitle("Daily Planet");

        recyclerView = findViewById(R.id.recyclerNews);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);

        Load("");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_home, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        final SearchView searchView = (SearchView) menu.findItem(R.id.menuSearch).getActionView();
        MenuItem menuItem = menu.findItem(R.id.menuSearch);

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setQueryHint("Search News");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (query.length() > 2){
                    Load(query);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Load(newText);
                return false;
            }
        });
        menuItem.getIcon().setVisible(false, false);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.menuCategory) {
            Intent intentCategory = new Intent(MainActivity.this, CategoryActivity.class);
            startActivity(intentCategory);
        } else if (itemId == R.id.menuAccount) {
            Intent intentLogin = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intentLogin);
        }

        return super.onOptionsItemSelected(item);
    }

    public void Load(final String keyword){
        Api api = ApiClient.getApiClient().create(Api.class);
        String country = "id";
        Call<News> newsCall;

        if (keyword.length() > 0){
           newsCall = api.getSearchNews(keyword, country, "publishedAt", API_KEY);
        } else {
            newsCall = api.getNews(country, API_KEY);
        }

        newsCall.enqueue(new Callback<News>() {
            @Override
            public void onResponse(@NonNull Call<News> call, @NonNull Response<News> response) {
                if (response.body() != null) {
                    if (response.isSuccessful() && response.body().getArticles() != null){
                        if (!articles.isEmpty()){
                            articles.clear();
                        }
                        articles = response.body().getArticles();
                        newsAdapter = new NewsAdapter(articles, MainActivity.this);
                        recyclerView.setAdapter(newsAdapter);
                        newsAdapter.notifyDataSetChanged();

                        initListener();

                    } else {
                        Toast.makeText(MainActivity.this, "No Result!", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<News> call, Throwable t) {

            }
        });
    }

    private void initListener(){
        newsAdapter.setOnItemClickListener((view, position) -> {
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            Articles articles1 = articles.get(position);
            intent.putExtra("url", articles1.getUrl());
            intent.putExtra("title", articles1.getTitlel());
            intent.putExtra("img", articles1.getUrlToImage());
            intent.putExtra("source", articles1.getSource().getName());
            intent.putExtra("published", articles1.getPublishedAt());
            intent.putExtra("author", articles1.getAuthor());

            startActivity(intent);
        });
    }


}