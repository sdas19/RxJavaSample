package com.example.rxjavasample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.reactivex.*
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.*


class MainActivity : AppCompatActivity() {

    val TAG = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val reactiveDataSource = ReactiveDataSource()

        reactiveDataSource.observableList.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<String>{

                override fun onComplete() {
                    Log.e(TAG,"observable list - onComplete")
                }

                override fun onSubscribe(d: Disposable) {
                    Log.e(TAG,"observable list - onSubscribe")

                }

                override fun onNext(t: String) {
                    Log.e(TAG,"observable list - onNext $t")

                }

                override fun onError(e: Throwable) {
                    Log.e(TAG,"observable list - onError ${e.message}")

                }
            })

        reactiveDataSource.singleList.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object :SingleObserver<List<String>>{

                override fun onSuccess(t: List<String>) {
                    for(oneString in t){
                        Log.e(TAG,"single list - $oneString")
                    }
                }

                override fun onSubscribe(d: Disposable) {
                    Log.e(TAG,"single list - onSubscribe")

                }

                override fun onError(e: Throwable) {
                    Log.e(TAG,"single list - onError ${e.message}")

                }
            })

        reactiveDataSource.maybeString.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : MaybeObserver<String>{
                override fun onSuccess(t: String) {
                    Log.e(TAG,"maybe list - onSucces $t")

                }

                override fun onComplete() {
                    Log.e(TAG,"maybe list - onComplete")

                }

                override fun onSubscribe(d: Disposable) {
                    Log.e(TAG,"maybe list - onSubscribe")

                }

                override fun onError(e: Throwable) {
                    Log.e(TAG,"maybe list - onError ${e.message}")

                }
            })

        reactiveDataSource.completable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CompletableObserver{

                override fun onComplete() {
                    Log.e(TAG,"completable - onComplete")

                }

                override fun onSubscribe(d: Disposable) {
                    Log.e(TAG,"completable - onSubscribe")

                }

                override fun onError(e: Throwable) {
                    Log.e(TAG,"completable  - onError ${e.message}")

                }
            })

    }
}
