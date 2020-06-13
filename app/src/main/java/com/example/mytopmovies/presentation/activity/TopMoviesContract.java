package com.example.mytopmovies.presentation.activity;

import com.example.mytopmovies.data.BaseModel;
import com.example.mytopmovies.presentation.base.ActivityView;
import com.example.mytopmovies.presentation.base.BasePresenter;

import java.util.List;


public interface TopMoviesContract {

    interface View extends ActivityView {

        void updateData(List<BaseModel> resultList);

        void showSnackbar(String s);

        void setRefreshing(boolean active);

    }

    interface Presenter extends BasePresenter {

        void loadData();

        void onLoadNextPage();
    }

}
