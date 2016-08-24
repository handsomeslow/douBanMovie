package com.example.junxu.rxjava.dbMovie;

/**
 * Created by junxu on 2016/7/20.
 */
public class DBMovieBox {
    private String title;
    private String date;
    private DBMovieBoxSubjects[] subjects;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public DBMovieBoxSubjects[] getSubjects() {
        return subjects;
    }

    public void setSubjects(DBMovieBoxSubjects[] subjects) {
        this.subjects = subjects;
    }

    public class DBMovieBoxSubjects{
        int rank;
        int box;
        //boolean new; //变量名为new？？
        DBMovieSubjects[] subject;

        public int getRank() {
            return rank;
        }

        public void setRank(int rank) {
            this.rank = rank;
        }

        public int getBox() {
            return box;
        }

        public void setBox(int box) {
            this.box = box;
        }

        public DBMovieSubjects[] getSubject() {
            return subject;
        }

        public void setSubject(DBMovieSubjects[] subject) {
            this.subject = subject;
        }
    }
}
