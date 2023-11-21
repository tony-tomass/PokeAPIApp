package com.example.webapiapp;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {

    ImageView sprite_iv;
    TextView num_tv;
    TextView name_tv;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        sprite_iv = itemView.findViewById(R.id.sprite_IV);
        num_tv = itemView.findViewById(R.id.num_row_TV);
        name_tv = itemView.findViewById(R.id.name_row_TV);

    }
}
