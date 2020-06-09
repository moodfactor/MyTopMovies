package com.example.mytopmovies.presentation.activity;

import com.example.mytopmovies.data.BaseModel;
import com.example.mytopmovies.domain.IInteractor;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
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

    private Disposable subscription = null;
    private int currentPage = 1;


    @Override
    public void startView() {
    }

    @Override
    public void stopView() {
        if (view != null) view = null;

    }

    @Override
    public void loadData(int currentPage) {
        subscription = interactor.result(currentPage)
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
                            view.setRefreshing(false);
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

    @Override
    public void onLoadNextPage() {
        view.setRefreshing(true);
        loadData(currentPage);
        currentPage++;
    }
}
