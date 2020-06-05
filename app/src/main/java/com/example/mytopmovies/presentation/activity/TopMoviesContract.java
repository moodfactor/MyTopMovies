package com.example.mytopmovies.presentation.activity;

import com.example.mytopmovies.data.BaseModel;
import com.example.mytopmovies.presentation.base.ActivityView;
import com.example.mytopmovies.presentation.base.BasePresenter;

import io.reactivex.Observable;

public interface TopMoviesContract {
    interface View extends ActivityView {

        void updateData(BaseModel baseModel);

        void showSnackbar(String s);
    }

    interface Presenter extends BasePresenter {
        void loadData();

        void rxUnsubscribe();

        void setView(TopMoviesContract.View view);
    }

//    interface Model {
//
//        Observable<BaseModel> result();
//
//    }
}
