package com.example.rxjavasample;

import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import io.reactivex.*;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class Main2Activity extends AppCompatActivity {

    public String TAG = Main2Activity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ReactiveDataSource reactiveDataSource = new ReactiveDataSource();
        reactiveDataSource.getObservableList().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e(TAG, "subscribed in observable list");

                    }

                    @Override
                    public void onNext(String item) {
                        Log.e(TAG, "in observable list ->" + item);

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "error in observable list " + e.getMessage());

                    }

                    @Override
                    public void onComplete() {
                        Log.e(TAG, "in observable list complete");

                    }
                });

        reactiveDataSource.getSingleList().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e(TAG, "in single list subscribe");

                    }

                    @Override
                    public void onSuccess(List<String> strings) {
                        for (String current : strings) {
                            Log.e(TAG, "in single list-> " + current);
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "in single list error " + e.getMessage());

                    }
                });

        reactiveDataSource.getMaybeString().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e(TAG, "in maybe string subscribe ");

                    }

                    @Override
                    public void onSuccess(String s) {
                        Log.e(TAG, "in maybe string success " + s);

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "in maybe string error " + e.getMessage());

                    }

                    @Override
                    public void onComplete() {
                        Log.e(TAG, "in maybe string complete ");

                    }
                });

        reactiveDataSource.getMaybeEmpty().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e(TAG, "in maybe empty subscribe ");

                    }

                    @Override
                    public void onSuccess(String s) {
                        Log.e(TAG, "in maybe empty success " + s);

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "in maybe empty error " + e.getMessage());

                    }

                    @Override
                    public void onComplete() {
                        Log.e(TAG, "in maybe empty complete ");

                    }
                });

        reactiveDataSource.getCompletable().subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e(TAG, "in completable subscribe ");

                    }

                    @Override
                    public void onComplete() {
                        Log.e(TAG, "in completable complete ");

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "in completable error " + e.getMessage());

                    }
                });


    }

}

