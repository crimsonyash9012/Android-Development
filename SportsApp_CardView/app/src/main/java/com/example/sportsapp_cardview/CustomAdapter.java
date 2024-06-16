package com.example.sportsapp_cardview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

// always when you create custom adapter follow the steps
// 1 - create view holder class
// 2 - extend recycle view adapter and pass custom view holder
// 3 - implement all the 3 functions
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.SportsViewHolder> {
    private List<Sport> sportList;
    public ItemClickListener clickListener;

    public CustomAdapter(List<Sport> sportList) {
        this.sportList = sportList;
    }
    public void setClickListener(ItemClickListener myListener){this.clickListener = myListener;}

    @NonNull
    @Override
    public SportsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_item_layout, parent, false);


        return new SportsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SportsViewHolder holder, int position) {
        Sport sport = sportList.get(position);
        holder.textView.setText(sport.sportName);
        holder.imageView.setImageResource(sport.sportImg);
    }

    @Override
    public int getItemCount() {
        return sportList.size();
    }


    public class SportsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView textView;
        ImageView imageView;

        public SportsViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
            imageView = itemView.findViewById(R.id.imageviewCard);
            itemView.setOnClickListener((View.OnClickListener) this);
        }

        @Override
        public void onClick(View v){
            if(clickListener!=null) clickListener.onClick(v,getAdapterPosition());
        }
    }
}
