package com.example.mytopmovies.presentation.activity.common;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mytopmovies.R;
import com.example.mytopmovies.data.BaseModel;


import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListItemViewHolder> {

    private List<BaseModel> list;

    public ListAdapter(List<BaseModel> list) {
        this.list = list;
    }

    @Override
    public ListItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_row, parent, false);
        return new ListItemViewHolder(itemView);
    }

    public void updateData(List<BaseModel> newList){
        MyDiffUtill myDiffUtill = new MyDiffUtill(list,newList);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(myDiffUtill);
        list.clear();
        list.addAll(newList);
        diffResult.dispatchUpdatesTo(this);
    }

    @Override
    public void onBindViewHolder(ListItemViewHolder holder, int position) {

        holder.itemName.setText(list.get(position).getName());
        holder.countryName.setText(list.get(position).getCountry());

    }

    @Override
    public int getItemCount() {

        return list.size();
    }

    public static class ListItemViewHolder extends RecyclerView.ViewHolder {

        public TextView itemName;
        public TextView countryName;

        public ListItemViewHolder(View itemView) {
            super(itemView);
            itemName = (TextView) itemView.findViewById(R.id.textView_fragmentlist_task_name);
            countryName = (TextView) itemView.findViewById(R.id.textView_fragmentlist_country_name);
        }
    }
}

