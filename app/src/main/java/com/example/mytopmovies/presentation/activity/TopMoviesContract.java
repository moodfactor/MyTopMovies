package com.example.mytopmovies.presentation.activity;

import com.example.mytopmovies.data.BaseModel;
import com.example.mytopmovies.presentation.base.ActivityView;
import com.example.mytopmovies.presentation.base.BasePresenter;



public interface TopMoviesContract {

    interface View extends ActivityView {

        void updateData(BaseModel baseModel);

        void showSnackbar(String s);

        void setRefreshing(boolean active);
    }

    interface Presenter extends BasePresenter {

        void loadData(int currentPage);

        void rxUnsubscribe();

        void setView(TopMoviesContract.View view);

        void onLoadNextPage();
    }

}
