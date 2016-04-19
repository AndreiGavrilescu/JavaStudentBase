package com.core;

/**
 * Created by Andrei on 14-Mar-16.
 */
public class Course {
    private int cid;
    private String cname;
    private int year;
    private String tname;

    public Course(String cname, int year, int cid, String tname) {
        this.cname = cname;
        this.year = year;
        this.cid = cid;
        this.tname = tname;
    }

    @Override
    public String toString() {
        return "Course{" +
                "cid=" + cid +
                ", cname='" + cname + '\'' +
                ", year=" + year +
                ", tname='" + tname + '\'' +
                '}';
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }
}
