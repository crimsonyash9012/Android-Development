package com.example.volumecalculator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyCustomAdapter extends ArrayAdapter<Shape> {
    private ArrayList<Shape> shapesArrayList;
    Context context;

    public MyCustomAdapter(Context context, ArrayList<Shape> shapesArrayList) {
        super(context, R.layout.grid_item_layout, shapesArrayList);
        this.shapesArrayList = shapesArrayList;
        this.context = context;
    }

    // viewer holder class - used to cache reference to the views within an item layout so that they don't need to be repeatedly looked up
    private static class MyViewHolder{
        TextView shapeName;
        ImageView shapeImg;
        
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // checks if recycled view is available or not
        // we will -> customize type of view for data object, override this method & inflate a view resource
        // position -> stores the position of item within the dataset that should be displayed
        // convert view -> if null => no view available for recycle
        // parent -> will hold the returned view

        // 1 - get the shape object for the current position
        Shape shapes = getItem(position);

        // 2 - Inflating layout
        MyViewHolder myViewHolder;

        if(convertView == null){
            // no view went off the screen
            myViewHolder = new MyViewHolder();

            // process of creating views objects from XML layout files is called inflating
            LayoutInflater inflater = LayoutInflater.from(getContext());

            convertView = inflater.inflate(
                    R.layout.grid_item_layout, parent, false
            );

            // finding the views
            myViewHolder.shapeName = (TextView) convertView.findViewById(R.id.textView);
            myViewHolder.shapeImg =  (ImageView) convertView.findViewById(R.id.imageView);

            // get tag and set tag -> used to attach and retrieve custom data objects to individual views
            // these methods are helpful when working with list based UI components like grid view list

            convertView.setTag(myViewHolder);
        }
        else{
            myViewHolder = (MyViewHolder) convertView.getTag();
        }

        // getting data from the model class

        myViewHolder.shapeName.setText(shapes.getShapeName());
        myViewHolder.shapeImg.setImageResource(shapes.getShapeImg());
        return convertView;

    }
}
