package com.jx.RxMovie;

import android.util.Log;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by junxu on 2016/7/15.
 */
public class MapTest {
    Observable observable;
    Observable observable2;

    public void subscribeTest(){
         Observable.just(1,2,3,4)
                 // 指定subscribe()发生的线程，这里创建的1,2,3,4会在IO线程发出。
                 .subscribeOn(Schedulers.io())
                 // 指定Subscriber的回调运行的线程，此处打印将在主线程中完成
                 .observeOn(AndroidSchedulers.mainThread())
                 .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer number) {
                        Log.d("RxJava Test","Number" + number);
                    }
                });
    }


    public void MapTest(){
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {

            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {

            }
        });


        Observable.just(1,2,3)
            .map(new Func1<Integer, String>() {
                @Override
                public String call(Integer integer) {
                    return null;
                }
            })
        .subscribe(new Action1<String>() {
            @Override
            public void call(String s) {

            }
        });


    }


}
