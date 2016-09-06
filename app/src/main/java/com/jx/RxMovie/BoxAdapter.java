package com.jx.RxMovie;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by junxu on 2016/7/20.
 */
public class BoxAdapter extends RecyclerView.Adapter<BoxAdapter.ViewHolder> {
    Context context;
    public BoxAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).
                inflate(R.layout.cell_movie_detail, parent, false);
        ViewHolder myVH = new ViewHolder(itemView);
        return myVH;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

}
