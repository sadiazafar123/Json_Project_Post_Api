package com.example.jsonprojectpostapi.java.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jsonprojectpostapi.R;
import com.example.jsonprojectpostapi.java.model.ApiResponse;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    List<ApiResponse> dataList;

    public RecyclerAdapter(List<ApiResponse> postList) {
        this.dataList = postList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.recycler_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(dataList.get(position).getTitle());
        holder.body.setText(dataList.get(position).getBody());

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView title,body;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.title= itemView.findViewById(R.id.tvTitle);
            this.body= itemView.findViewById(R.id.tvBody);
        }
    }
}
