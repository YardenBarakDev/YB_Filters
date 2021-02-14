package com.bawp.ybfilters;

import android.content.Context;
import android.graphics.ColorMatrixColorFilter;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.ArrayList;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private ArrayList<ColorMatrixColorFilter> filters = new ArrayList<>();
    private final LayoutInflater mInflater;
    private final Context context;
    private final Uri uri;


    // data is passed into the constructor
    public MyRecyclerViewAdapter(Context context, ArrayList<ColorMatrixColorFilter> data, Uri uri) {
        this.mInflater = LayoutInflater.from(context);
        this.filters = data;
        this.context = context;
        this.uri = uri;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.list_item_image, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.List_Image.setColorFilter(filters.get(position));
        Glide.with(context)
                .load(uri)
                .into(holder.List_Image);

    }

    // total number of rows
    @Override
    public int getItemCount() {
        return filters.size();
    }


    // convenience method for getting data at click position
    ColorMatrixColorFilter getItem(int position) {
        return filters.get(position);
    }


    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView List_Image;

        ViewHolder(View view) {
            super(view);
            //find view
            List_Image = view.findViewById(R.id. List_Image);
        }
    }

}
