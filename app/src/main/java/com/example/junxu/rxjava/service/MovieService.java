package com.example.junxu.rxjava.service;

import com.example.junxu.rxjava.dbMovie.DBMovie;
import com.example.junxu.rxjava.dbMovie.DBMovieBox;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by junxu on 2016/7/18.
 */
public interface MovieService {
    String DBmovieURL = "https://api.douban.com/v2/movie/";

    /**
     * 获取TOP电影
     * @param start
     * @param count
     * @return
     */
    @GET("top250")
    Observable<DBMovie> getTopMovie(
            @Query("start")int start,
            @Query("count")int count);

    /**
     * 获取正在热映的电影
     * @param city
     * @param start
     * @param count
     * @return
     */
    @GET("in_theaters")
    Observable<DBMovie> getMovieInTheaters(
            @Query("city")String city,
            @Query("start")int start,
            @Query("count")int count
    );

    @GET("coming_soon")
    Observable<DBMovie> getMovieComingSoon(
            @Query("start")int start,
            @Query("count")int count
    );

    @GET("search")
    Observable<DBMovie> searchMovie(
            @Query("q")String title,
            @Query("start")int start,
            @Query("count")int count
    );

    @GET("us_box")
    Observable<DBMovieBox> getMovieUSBox();

}
