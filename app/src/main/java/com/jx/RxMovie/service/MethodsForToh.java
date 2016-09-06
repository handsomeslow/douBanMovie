package com.jx.RxMovie.service;

import com.jx.RxMovie.tohContent.TohEntity;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by junxu on 2016/7/18.
 */
public class MethodsForToh {
    private Retrofit retrofit;
    private TohService tohService;
    private static MethodsForToh ourInstance = new MethodsForToh();

    public static MethodsForToh getInstance() {
        return ourInstance;
    }

    private MethodsForToh() {
        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(TohService.Toh_URL)
                .build();
        tohService = retrofit.create(TohService.class);
    }
/**
 * Method for Toh
 * 获取历史详情
 */
    public void getTohDetail(Subscriber<TohEntity> subscriber,
                      String ver,
                      int month,
                      int day){
        tohService.getToh(ver,month,day,"9b0eb9e572ae928f7b72cfc1ac880feb")
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}
