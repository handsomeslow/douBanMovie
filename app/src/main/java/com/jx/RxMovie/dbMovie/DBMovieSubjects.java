package com.jx.RxMovie.dbMovie;

import java.util.Arrays;

public class DBMovieSubjects {
    private DBMovieSubjectsImages images;
    private DBMovieSubjectsCasts[] casts;
    private String original_title;
    private String subtype;
    private String year;
    private String[] genres;
    private DBMovieSubjectsDirectors[] directors;
    private DBMovieSubjectsRating rating;
    private String alt;
    private String id;
    private String title;
    private int collect_count;

    public DBMovieSubjectsImages getImages() {
        return this.images;
    }

    public void setImages(DBMovieSubjectsImages images) {
        this.images = images;
    }

    public DBMovieSubjectsCasts[] getCasts() {
        return this.casts;
    }

    public String getCastsStr(){
        String str = new String();
        for(int i=0;i<casts.length;i++)
            str += casts[i].getName() + " ";
        if (str.length()>16)
            str=str.substring(0,16)+"...";
        return str;
    }

    public void setCasts(DBMovieSubjectsCasts[] casts) {
        this.casts = casts;
    }

    public String getOriginal_title() {
        return this.original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getSubtype() {
        return this.subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public String getYear() {
        return this.year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String[] getGenres() {
        return this.genres;
    }

    public String getGenresString() {
        return  Arrays.toString(genres);
    }

    public void setGenres(String[] genres) {
        this.genres = genres;
    }

    public DBMovieSubjectsDirectors[] getDirectors() {
        return this.directors;
    }

    public String getDirectorsStr(){
        return "导演：" + Arrays.toString(directors);
    }

    public void setDirectors(DBMovieSubjectsDirectors[] directors) {
        this.directors = directors;
    }

    public DBMovieSubjectsRating getRating() {
        return this.rating;
    }

    public void setRating(DBMovieSubjectsRating rating) {
        this.rating = rating;
    }

    public String getAlt() {
        return this.alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCollect_count() {
        return this.collect_count;
    }

    public void setCollect_count(int collect_count) {
        this.collect_count = collect_count;
    }


}
