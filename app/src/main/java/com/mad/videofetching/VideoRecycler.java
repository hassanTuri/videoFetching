package com.mad.videofetching;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class VideoRecycler extends RecyclerView.Adapter<VideoRecycler.ViewHolder> {


    private List<VideoModel> list;

    public VideoRecycler(List<VideoModel> list){
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_lesson, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(list.get(position).name);
        holder.itemView.setOnClickListener(view -> {
            Toast.makeText(holder.itemView.getContext(), list.get(position).url, Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text);
        }
    }

    public static class VideoModel{
        public String name;
        public String url;

        public VideoModel(String name, String url){
            this.name = name;
            this.url = url;
        }
    }

}
