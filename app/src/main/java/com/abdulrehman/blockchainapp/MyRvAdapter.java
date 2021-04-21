package com.abdulrehman.blockchainapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyRvAdapter extends RecyclerView.Adapter<MyRvAdapter.MyViewHolder>{
    blockChain object;
    Context c;
    private OnItemListner onItemListner;

    public MyRvAdapter(blockChain object, Context c, OnItemListner onItemListner) {
        this.object = object;
        this.c = c;
        this.onItemListner = onItemListner;
    }

    @NonNull
    @Override
    public MyRvAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(c).inflate(R.layout.row,parent,false);
        return new MyViewHolder(itemView, onItemListner);
    }

    @Override
    public void onBindViewHolder(@NonNull MyRvAdapter.MyViewHolder holder, int position) {
        holder.block_index.setText(String.valueOf(object.blockChain.get(position).getIndex()));
        holder.block_timestamp.setText(object.blockChain.get(position).getTimeStamp());
        holder.block_data.setText(object.blockChain.get(position).getData());
        holder.prev_block_hash.setText(object.blockChain.get(position).getPreviousBlockHash());
        holder.curr_block_hash.setText(object.blockChain.get(position).getBlockHash());
    }

    @Override
    public int getItemCount() {
        return object.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView block_index, block_timestamp, block_data, prev_block_hash, curr_block_hash;
        LinearLayout linearLayout;
        OnItemListner onItemListner;
        public MyViewHolder(@NonNull View itemView, OnItemListner onItemListner) {
            super(itemView);
            block_index = itemView.findViewById(R.id.block_index);
            block_timestamp = itemView.findViewById(R.id.block_timestamp);
            block_data = itemView.findViewById(R.id.block_data);
            prev_block_hash = itemView.findViewById(R.id.prev_block_hash);
            curr_block_hash = itemView.findViewById(R.id.curr_block_hash);
            linearLayout = itemView.findViewById(R.id.ll);
            this.onItemListner = onItemListner;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onItemListner.ONItemClick(getAdapterPosition());
        }
    }
    public interface OnItemListner{
        void ONItemClick(int position);
    }
}
