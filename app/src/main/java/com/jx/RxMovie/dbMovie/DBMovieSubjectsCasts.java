package com.jx.RxMovie.dbMovie;

public class DBMovieSubjectsCasts {
    private String alt;
    private String name;
    private String id;
    private DBMovieSubjectsCastsAvatars avatars;

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

    public DBMovieSubjectsCastsAvatars getAvatars() {
        return this.avatars;
    }

    public void setAvatars(DBMovieSubjectsCastsAvatars avatars) {
        this.avatars = avatars;
    }
}
