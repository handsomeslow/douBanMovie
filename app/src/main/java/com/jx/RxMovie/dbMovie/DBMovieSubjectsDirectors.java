package com.jx.RxMovie.dbMovie;

public class DBMovieSubjectsDirectors {
    private String alt;
    private String name;
    private String id;
    private DBMovieSubjectsDirectorsAvatars avatars;

    public String getAlt() {
        return this.alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public DBMovieSubjectsDirectorsAvatars getAvatars() {
        return this.avatars;
    }

    public void setAvatars(DBMovieSubjectsDirectorsAvatars avatars) {
        this.avatars = avatars;
    }
}
