package com.example.aaron.services;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aaron on 8/15/17.
 */

public class RandomListAdaptor extends RecyclerView.Adapter<RandomListAdaptor.ViewHolder> {
   List<String> randStringList = new ArrayList<>();

    public RandomListAdaptor(List<String> randStringList) {
        this.randStringList = randStringList;
    }

    @Override
    public RandomListAdaptor.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.random_item,parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RandomListAdaptor.ViewHolder holder, int position) {
        String currentString = randStringList.get(position);

        holder.tvRandomItem.setText(currentString);
    }

    @Override
    public int getItemCount() {

        Log.d("BLA", "getItemCount: " + randStringList.size());
        return randStringList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvRandomItem;

        public ViewHolder(View itemView) {
            super(itemView);
            tvRandomItem = (TextView)itemView.findViewById(R.id.tvRandomString);

        }
    }
}
