package com.example.mytopmovies.presentation.activity;

import com.example.mytopmovies.data.BaseModel;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class TopMoviesPresenter implements TopMoviesContract.Presenter {
    @Inject
    TopMoviesContract.View view;

    TopMoviesContract.Model model;


    @Inject
    public TopMoviesPresenter(TopMoviesContract.Model model) {
        this.model = model;
    }

    private Disposable subscription = null;


    @Override
    public void startView() {

    }

    @Override
    public void stopView() {
        if (view != null) view = null;
    }

    @Override
    public void loadData() {
        subscription = model.result()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<BaseModel>() {
                    @Override
                    public void onComplete() {
                    }


                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        if (view != null) {
                            view.showSnackbar("Error getting movies");
                        }
                    }

                    @Override
                    public void onNext(BaseModel viewModel) {
                        if (view != null) {
                            view.updateData(viewModel);
                        }
                    }
                });
    }

    @Override
    public void rxUnsubscribe() {
        if (subscription != null) {
            if (!subscription.isDisposed()) {
                subscription.dispose();
            }
        }
    }

    @Override
    public void setView(TopMoviesContract.View view) {
        this.view = view;
    }
}
