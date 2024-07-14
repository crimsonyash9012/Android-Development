package com.example.phonebookapp;

import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import android.content.Context;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.phonebookapp.databinding.ItemCardBinding;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.UserViewHolder> {

    private Context context;
    private ArrayList<User> userArrayList;

    public MyAdapter(Context context, ArrayList<User> userArrayList) {
        this.context = context;
        this.userArrayList = userArrayList;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // initializes the view holder and infaltes the item layout
        ItemCardBinding binding = DataBindingUtil
                                    .inflate(
                                            LayoutInflater.from(parent.getContext()),
                                            R.layout.item_card, parent, false
                                    );


        return new UserViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        // bind data to an existing view holder
        // populate the views in viewHolder with data from the dataset

        User currUser = userArrayList.get(position);
        holder.itemCardBinding.setUser(currUser);

    }

    @Override
    public int getItemCount() {
        return userArrayList.size();
    }

    // ViewHolder: cache references to the
    // individual views within an item layout
    // of a RecyclerView list or grid.
    public class UserViewHolder extends RecyclerView.ViewHolder{
        private ItemCardBinding itemCardBinding;


        public UserViewHolder(ItemCardBinding itemCardBinding) {
            super(itemCardBinding.getRoot());
            this.itemCardBinding = itemCardBinding;

            // handling click events on recycler view
            itemCardBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // getting the clicked item position
                    int position = getAdapterPosition();
                }
            });
        }
    }

}
