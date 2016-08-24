package com.example.junxu.rxjava.service;

import com.example.junxu.rxjava.tohContent.TohEntity;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by junxu on 2016/7/18.
 */
public interface TohService {
    String Toh_URL = "http://api.juheapi.com/japi/";


    @GET("toh/")
    Observable<TohEntity> getToh(@Query("v") String ver,
                                 @Query("month")int month,
                                 @Query("day")int day,
                                 @Query("key")String aipKey);
}
