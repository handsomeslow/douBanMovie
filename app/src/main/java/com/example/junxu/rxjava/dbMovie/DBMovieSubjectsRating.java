package com.example.junxu.rxjava.dbMovie;

public class DBMovieSubjectsRating {
    private double average;
    private int min;
    private int max;
    private String stars;

    public double getAverage() {
        return this.average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public int getMin() {
        return this.min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return this.max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public String getStars() {
        return this.stars;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }
}
