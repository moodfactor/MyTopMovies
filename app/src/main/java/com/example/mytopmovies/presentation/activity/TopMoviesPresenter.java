package com.example.mytopmovies.presentation.activity;

import com.example.mytopmovies.data.BaseModel;
import com.example.mytopmovies.domain.IInteractor;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class TopMoviesPresenter implements TopMoviesContract.Presenter {
    @Inject
    TopMoviesContract.View view;

    @Inject
    IInteractor interactor;

    @Inject
    public TopMoviesPresenter() {
    }

    private CompositeDisposable subscription = new CompositeDisposable();
    List<BaseModel> modelList = new ArrayList<>();


    @Override
    public void startView() {
    }

    @Override
    public void stopView() {
        if (view != null) view = null;

    }

    @Override
    public void loadData() {
        subscription.add(interactor.result()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(results -> {
                    if (view != null) {
                        modelList.add(results);
                        view.updateData(modelList);
                    }
                }, error -> {
                    error.printStackTrace();
                    if (view != null) {
                        view.showSnackbar("Error getting movies");
                    }
                }));

    }

    @Override
    public void rxUnsubscribe() {
       subscription.clear();
    }

    @Override
    public void setView(TopMoviesContract.View view) {
        this.view = view;
    }

    @Override
    public void onLoadNextPage() {
        subscription.add(interactor.loadNextPage()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(results -> {
                    if (view != null) {
                        modelList.add(results);
                        view.updateData(modelList);
                    }
                }, error -> {
                    error.printStackTrace();
                    if (view != null) {
                        view.showSnackbar("Error getting movies");
                    }
                }));
    }
}
