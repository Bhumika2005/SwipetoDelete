package com.hemil.k.swipetodelete;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hemil on 23-06-2017.
 */

public class SwipeRecylerAdapter extends RecyclerSwipeAdapter<SwipeRecylerAdapter.ViewHolder> {

    private List<String> movies = new ArrayList<>();
    private Activity mActivity;

    public SwipeRecylerAdapter(List<String> movies, Activity mActivity) {
        this.movies = movies;
        this.mActivity = mActivity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.item_your_news_feeds, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.txtMoviesName.setText(movies.get(position));

        holder.lnrDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movies.remove(position);
                notifyDataSetChanged();
                holder.swipeLayout.close();

                // Here you can do whatever on th swipe button
            }
        });

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return position;
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {
        private final SwipeLayout swipeLayout;
        private LinearLayout lnrDelete;
        private TextView txtMoviesName;

        public ViewHolder(View view) {
            super(view);
            swipeLayout = (SwipeLayout) view.findViewById(R.id.swipeLayout);
            lnrDelete = (LinearLayout) view.findViewById(R.id.lnrDelete);
            txtMoviesName = (TextView) view.findViewById(R.id.txtMoviesName);
        }
    }
}
