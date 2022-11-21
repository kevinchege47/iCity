package com.example.icity.HelperClasses.HomeAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.icity.R;

import java.util.ArrayList;

public class FeaturedAdapter extends RecyclerView.Adapter <FeaturedAdapter.FeaturedViewHolder> {
    ArrayList<FeaturedHelperClass> featuredlocations;

    public FeaturedAdapter(ArrayList<FeaturedHelperClass> featuredlocations) {
        this.featuredlocations = featuredlocations;
    }

    @NonNull
    @Override
    public FeaturedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.featured_card_design,parent,false);
        FeaturedViewHolder featuredViewHolder = new FeaturedViewHolder(view);
        return featuredViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FeaturedViewHolder holder, int position) {
        FeaturedHelperClass featuredHelperClass = featuredlocations.get(position);
        holder.imageView.setImageResource(featuredHelperClass.getImages());
        holder.titles.setText(featuredHelperClass.getTitles());
        holder.descriptions.setText(featuredHelperClass.getDescriptions());

    }

    @Override
    public int getItemCount() {
        return featuredlocations.size();
    }

    public static class FeaturedViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView titles, descriptions;


        public FeaturedViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.featured_image);
           titles = itemView.findViewById(R.id.featured_title);
          descriptions = itemView.findViewById(R.id.featured_desc);
        }
    }
}

