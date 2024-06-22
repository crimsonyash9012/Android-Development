package com.example.grocerryapp_recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<item> itemList;
    public ItemClickListener clickListener;
    public MyAdapter(List<item> itemList) {
        this.itemList = itemList;
    }

    public void setClickListener(ItemClickListener myListener){
        this.clickListener = myListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // responsible for creating new view holder for your items
        // viewType can be used when the recycle view has multiple types of items, useful in different layout for different items


        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,parent,false);
        // false here signify not to attach the
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        // binds the data from the dataset to the views within view holder
        // position - position of binding

        item item = itemList.get(position);

        holder.title.setText(item.getItemName());
        holder.description.setText(item.getItemDesc());
        holder.imageView.setImageResource(item.getItemImg());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class MyViewHolder extends
            RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView imageView;
        TextView title;
        TextView description;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageview);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            if(clickListener!=null) clickListener.onClick(v,getAdapterPosition());
        }
    }
}
