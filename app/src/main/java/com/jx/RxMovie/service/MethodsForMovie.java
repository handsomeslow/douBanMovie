package com.jx.RxMovie.service;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import com.jx.RxMovie.dbMovie.DBMovie;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by junxu on 2016/7/18.
 */
public class MethodsForMovie {
    Retrofit retrofit;
    MovieService movieService;
    public CompositeSubscription subscriptions;
    private static MethodsForMovie ourInstance = new MethodsForMovie();

    public static MethodsForMovie getInstance() {
        return ourInstance;
    }

    private MethodsForMovie() {
        retrofit  = new Retrofit.Builder()
                .baseUrl(MovieService.DBmovieURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        movieService = retrofit.create(MovieService.class);
        subscriptions = new CompositeSubscription();
    }

    public void getTopMovie(@NonNull int start, int count, Subscriber<DBMovie> subscriber){
        Subscription subscription = movieService.getTopMovie(start, count)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
        subscriptions.add(subscription);
    }

    public void getTheaterMovie(@NonNull String city, int start, int count, Action1<DBMovie> action1){
        Subscription subscription = movieService.getMovieInTheaters(city,start,count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(action1);
        subscriptions.add(subscription);
    }

    public void getComingMovie(@NonNull int start, int count,Action1<DBMovie> action1){
        Subscription subscription = movieService.getMovieComingSoon(start, count)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(action1);
        subscriptions.add(subscription);
    }

    /**
     * method zip:获取不同的数据源后统一处理
     * @param action1
     */

    public void getMovieWithLoaclPic(Action1<List<String>> action1){
        Subscription subscription = Observable.zip(movieService.getMovieComingSoon(0, 10),
                movieService.getMovieInTheaters("beijing", 0, 10),
                new Func2<DBMovie, DBMovie, List<String>>() {
                    @Override
                    public List<String> call(DBMovie dbMovie, DBMovie dbMovie2) {
                        List<String> list = new ArrayList<String>();
                        for (int i=0;i<dbMovie.getSubjects().length;i++)
                            list.add(dbMovie.getSubjects()[i].getTitle());

                        for (int i=0;i<dbMovie2.getSubjects().length;i++)
                            list.add(dbMovie.getSubjects()[i].getTitle());
                        return list;
                    }
                }
        )
                .subscribeOn(Schedulers.io())
                .subscribe(action1);
        subscriptions.add(subscription);
    }

    private Observable getLocalPic(){
        return Observable.create(new Observable.OnSubscribe<Bitmap>() {
            @Override
            public void call(Subscriber<? super Bitmap> subscriber) {

            }
        });
    }

    /**
     * map的使用:Object 转 String
     * @param title
     * @param start
     * @param count
     * @param action1
     */
    public void getMoiveTitle(@NonNull String title,int start,int count, Action1< List<String>> action1){
        Subscription subscription = movieService.searchMovie(title,start,count)
                .map(new Func1<DBMovie, List<String>>() {
                    @Override
                    public List<String> call(DBMovie dbMovie) {
                        List<String> listResult = new ArrayList<String>();
                        for (int i = 0;i<dbMovie.getCount();i++)
                            listResult.add(i+1+"、"+dbMovie.getSubjects()[i].getTitle());
                        return listResult;
                    }
                })
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(action1);
        subscriptions.add(subscription);
    }

    public void unSubscribe(){
        if (subscriptions != null && !subscriptions.isUnsubscribed())
            subscriptions.unsubscribe();
    }

    public void reSubscribe(){
        if (subscriptions != null && subscriptions.hasSubscriptions())
            subscriptions.unsubscribe();
        if (subscriptions.isUnsubscribed())
            subscriptions = new CompositeSubscription();
    }

}
