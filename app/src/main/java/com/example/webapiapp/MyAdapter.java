package com.example.webapiapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

//This, MyViewHolder, row.xml, and RecyclerView mentions
//https://www.youtube.com/watch?v=TAEbP_ccjsk&ab_channel=EasyTuto
//https://developer.android.com/develop/ui/views/layout/recyclerview
//https://developer.android.com/reference/androidx/recyclerview/widget/RecyclerView

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    Context context;
    List<Pokemon> list;
    RecyclerViewInterface recyclerViewInterface;

    public MyAdapter(Context context, List<Pokemon> list, RecyclerViewInterface recyclerViewInterface) {
        this.context = context;
        this.list = list;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.row, parent, false), recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.num_tv.setText(String.valueOf(list.get(position).getNat_num()));
        holder.name_tv.setText(list.get(position).getName());
        Picasso.get().load(list.get(position).getImage_url()).into(holder.sprite_iv);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
