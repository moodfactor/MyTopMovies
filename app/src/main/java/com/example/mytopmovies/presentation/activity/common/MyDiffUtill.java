package com.example.mytopmovies.presentation.activity.common;

import androidx.recyclerview.widget.DiffUtil;

import com.example.mytopmovies.data.BaseModel;

import java.util.List;

public class MyDiffUtill extends DiffUtil.Callback {

    private List<BaseModel> oldList;
    private List<BaseModel> newList;

    public MyDiffUtill(List<BaseModel> oldList, List<BaseModel> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).getId()==newList.get(newItemPosition).getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).equals(newList.get(newItemPosition));
    }
}
