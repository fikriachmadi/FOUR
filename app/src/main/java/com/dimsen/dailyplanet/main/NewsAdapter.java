package com.dimsen.dailyplanet.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.dimsen.dailyplanet.R;
import com.dimsen.dailyplanet.data.model.Articles;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder>{

    private final List<Articles> articles;
    private final Context context;
    private OnItemClickListener onItemClickListener;

    public NewsAdapter(List<Articles> articles, Context context) {
        this.articles = articles;
        this.context = context;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.news_item, parent, false);
        return new NewsViewHolder(view, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        Articles articles1 = articles.get(position);

        Glide.with(context)
                .load(articles1.getUrlToImage())
                .transition(DrawableTransitionOptions.withCrossFade())
                .centerCrop()
                .into(holder.urlToImage);

        holder.title.setText(articles1.getTitlel());
        holder.description.setText(articles1.getDescription());
        holder.publishedAt.setText(articles1.getPublishedAt());
        holder.source.setText(articles1.getSource().getName());
        holder.coins.setText("5 Coins");
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public static class NewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView title, description, publishedAt, source, coins;
        ImageView urlToImage;
        OnItemClickListener onItemClickListener;

        public NewsViewHolder(@NonNull View itemView, OnItemClickListener clickListener) {
            super(itemView);

            itemView.setOnClickListener(this);
            title = itemView.findViewById(R.id.txtTitleFav);
            description = itemView.findViewById(R.id.txtDescription);
            publishedAt = itemView.findViewById(R.id.txtPublishedAt);
            source = itemView.findViewById(R.id.txtSource);
            urlToImage = itemView.findViewById(R.id.imgNewsFav);
            coins = itemView.findViewById(R.id.txtCoinSum);

            this.onItemClickListener = clickListener;
        }

        @Override
        public void onClick(View v) {
            onItemClickListener.onItemClick(v, getAdapterPosition());
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
}
