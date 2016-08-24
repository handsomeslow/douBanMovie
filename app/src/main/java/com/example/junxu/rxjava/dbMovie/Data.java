package com.example.junxu.rxjava.dbMovie;

import android.support.annotation.IntDef;
import android.support.annotation.NonNull;

import com.example.junxu.rxjava.service.MethodsForMovie;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subjects.BehaviorSubject;

/**
 * Created by junxu on 2016/7/19.
 */
public class Data {
    private static Data instance;
    private static final int DATA_SOURCE_MEMORY = 1;
    private static final int DATA_SOURCE_DISK = 2;
    private static final int DATA_SOURCE_NETWORK = 3;
    @IntDef({DATA_SOURCE_MEMORY, DATA_SOURCE_DISK, DATA_SOURCE_NETWORK}) @interface DataSource {}

    BehaviorSubject<DBMovie> cache;

    private int dataSource;

    private Data() {
    }

    public static Data getInstance() {
        if (instance == null) {
            instance = new Data();
        }
        return instance;
    }

    private void setDataSource(@DataSource int dataSource) {
        this.dataSource = dataSource;
    }

    public String getDataSourceText() {
        String str;
        switch (dataSource) {
            case DATA_SOURCE_MEMORY:
                str = "内存";
                break;
            case DATA_SOURCE_DISK:
                str = "磁盘";
                break;
            case DATA_SOURCE_NETWORK:
                str = "网络";
                break;
            default:
                str = "网络";
        }
        return str;
    }

    public void loadFromNetwork() {
        MethodsForMovie.getInstance().getTheaterMovie("上海", 0, 20,
                new Action1<DBMovie>() {
                    @Override
                    public void call(DBMovie dbMovie) {
                        cache.onNext(dbMovie);
                    }
                });
    }


    public Subscription subscribeData(@NonNull Observer<DBMovie> observer) {
        if (cache == null) {
            cache = BehaviorSubject.create();
            Observable.create(new Observable.OnSubscribe<DBMovie>() {
                @Override
                public void call(Subscriber<? super DBMovie> subscriber) {
                    DBMovie items = DataBase.getInstance().readdata();
                    if (items == null) {
                        setDataSource(DATA_SOURCE_NETWORK);
                        loadFromNetwork();
                    } else {
                        setDataSource(DATA_SOURCE_DISK);
                        subscriber.onNext(items);
                    }
                }
            })
                    .subscribeOn(Schedulers.io())
                    .subscribe(cache);
        } else {
            setDataSource(DATA_SOURCE_MEMORY);
        }
        return cache.observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }

    public void clearMemoryCache() {
        cache = null;
    }

    public void clearMemoryAndDiskCache() {
        clearMemoryCache();
        DataBase.getInstance().delete();
    }

}
