package com.example.mytopmovies.presentation.base;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.FragmentManager;


import java.util.Objects;

import dagger.android.support.DaggerAppCompatActivity;
import dagger.android.support.DaggerAppCompatDialogFragment;
import dagger.android.support.DaggerFragment;

public abstract class BaseActivity<Binding extends ViewDataBinding> extends DaggerAppCompatActivity {
    private Binding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        binding = DataBindingUtil.setContentView(this, getLayoutRes());
        createActivity(savedInstanceState);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @LayoutRes
    protected abstract int getLayoutRes();

    protected abstract void createActivity(@Nullable Bundle savedInstanceState);

    protected abstract void stopActivity();

    protected abstract void startActivity();

    protected abstract void pauseActivity();

    protected abstract void resumeActivity();

    protected abstract void destroyActivity();

    protected abstract BasePresenter getPresenter();

    protected Binding getBinding() {
        return binding;
    }




    @Override
    protected void onDestroy() {
        if (getPresenter() != null){
            getPresenter().stopView();
        }
        destroyActivity();
        if (binding != null)binding = null;
        super.onDestroy();
    }

//    protected void onTransactionFragmentNoBackStack(DaggerFragment fragment, int container){
//        this.getSupportFragmentManager()
//                .beginTransaction()
//                .setCustomAnimations(R.anim.fragment_enter, R.anim.fragment_exit,
//                        R.anim.fragment_pop_enter, R.anim.fragment_pop_exit)
//                .replace(container,fragment,fragment.getClass().getSimpleName())
//                .commit();
//    }

    protected void onTransactionFragmentDialog(DaggerAppCompatDialogFragment fragment, int container){
        this.getSupportFragmentManager()
                .beginTransaction()
                .add(container,fragment,fragment.getClass().getSimpleName())
                .commit();
    }

    protected void onCloseFragmentDialog(DaggerAppCompatDialogFragment fragment){
        this.getSupportFragmentManager()
                .beginTransaction()
                .remove(fragment)
                .commit();
    }

//    protected void onRemoveAllFragment(){
//        if(getSupportFragmentManager() != null && getSupportFragmentManager().findFragmentById(R.id.main_content) != null) {
//            getSupportFragmentManager()
//                    .beginTransaction()
//                    .remove(Objects.requireNonNull(getSupportFragmentManager().findFragmentById(R.id.main_content)))
//                    .commit();
//        }
//    }


    protected void onTransactionFragmentWithBackStack(DaggerFragment fragment, int container) {
        this.getSupportFragmentManager()
                .beginTransaction()
                .replace(container, fragment, fragment.getClass().getSimpleName())
                .addToBackStack(null)
                .commit();
    }

    protected void onTransactionActivity(Class<?> activity, boolean cycleFinish){
        if (activity != null) {
            Intent intent = new Intent(this, activity);
            startActivity(intent);
            if(cycleFinish) {
                this.finish();
            }
        }
    }

    protected <T>void onTransactionActivity(Class<?> activity, boolean cycleFinish, T object){
        if (activity != null) {
            Intent intent = new Intent(this, activity);
            if(object != null){
                //data model to intent
            }
            startActivity(intent);
            if(cycleFinish) {
                this.finish();
            }
        }
    }

    protected  void restartAppBase() {
        PackageManager packageManager = this.getPackageManager();
        Intent intent = packageManager.getLaunchIntentForPackage(this.getPackageName());
        if(intent != null) {
            ComponentName componentName = intent.getComponent();
            Intent mainIntent = Intent.makeRestartActivityTask(componentName);
            this.startActivity(mainIntent);
            Runtime.getRuntime().exit(0);
        }
    }

    protected void toastShort(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }

    protected void toastLong(String message){
        Toast.makeText(this,message, Toast.LENGTH_LONG).show();
    }




    protected  void backPresed(){
        FragmentManager manager = this.getSupportFragmentManager();
        if (manager.getBackStackEntryCount() == 0) {

        }else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        startActivity();
    }

    @Override
    protected void onStop() {
//        stopActivity();
//        if (getPresenter() != null){
//            getPresenter().stopView();
//        }
//        destroyActivity();
//        if (binding != null)binding = null;
        super.onStop();
    }

    @Override
    protected void onPause() {
        pauseActivity();
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        resumeActivity();
    }

    public void hideKeyboard() {
        InputMethodManager imm =
                (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        Objects.requireNonNull(imm).hideSoftInputFromWindow(this.getWindow().getDecorView().getWindowToken(), 0);

    }

    public void showKeyboard() {
        InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        Objects.requireNonNull(imm).showSoftInput(this.getWindow().getDecorView(), InputMethodManager.SHOW_IMPLICIT);
    }

}
