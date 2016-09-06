package com.jx.RxMovie.dbMovie;

public class DBMovie {
    private int total;
    private DBMovieSubjects[] subjects;
    private int count;
    private int start;
    private String title;

    public int getTotal() {
        return this.total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public DBMovieSubjects[] getSubjects() {
        return this.subjects;
    }

    public void setSubjects(DBMovieSubjects[] subjects) {
        this.subjects = subjects;
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getStart() {
        return this.start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
