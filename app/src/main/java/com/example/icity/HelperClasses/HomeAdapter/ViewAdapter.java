package com.example.icity.HelperClasses.HomeAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.icity.R;

import java.util.ArrayList;

public class ViewAdapter extends RecyclerView.Adapter<ViewAdapter.ViewedViewHolder> {
    ArrayList<ViewedHelperClass> mostViewed;

    public ViewAdapter(ArrayList<ViewedHelperClass> mostViewed) {
        this.mostViewed = mostViewed;
    }

    @NonNull
    @Override
    public ViewedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.most_viewed_card_design, parent, false);
        ViewedViewHolder viewedViewHolder = new ViewedViewHolder(view);

        return viewedViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewedViewHolder holder, int position) {
        ViewedHelperClass viewedHelperClass = mostViewed.get(position);
        holder.image.setImageResource(viewedHelperClass.getViewed_image());
        holder.title.setText(viewedHelperClass.getViewed_titles());

    }

    @Override
    public int getItemCount() {
        return mostViewed.size();
    }

    public static class ViewedViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title;


        public ViewedViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.viewed_image);
            title = itemView.findViewById(R.id.viewed_title);
            image = itemView.findViewById(R.id.viewed_image);


        }
    }
}
