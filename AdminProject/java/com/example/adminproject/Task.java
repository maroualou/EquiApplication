package com.example.adminproject;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Task {
    private int id,durationMinut,user_Fk;
    private String title,detail;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDurationMinut() {
        return durationMinut;
    }

    public void setDurationMinut(int durationMinut) {
        this.durationMinut = durationMinut;
    }

    public int getUser_Fk() {
        return user_Fk;
    }

    public void setUser_Fk(int user_Fk) {
        this.user_Fk = user_Fk;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getStartDate() {
        Date myDate=new Date(startDate.getTime());
        DateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String myDateStr=df.format(myDate);
        return myDateStr ;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Task(int id, int durationMinut,
                int user_Fk, String title,
                String detail, Timestamp startDate,
                Timestamp isDone) {
        this.id = id;
        this.durationMinut = durationMinut;
        this.user_Fk = user_Fk;
        this.title = title;
        this.detail = detail;
        this.startDate = startDate;
        this.isDone = isDone;
    }

    public String getIsDone() {
        Date myDate=new Date(isDone.getTime());
        DateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String myDateStr=df.format(myDate);
        return myDateStr ;
    }

    public void setIsDone(Timestamp isDone) {
        this.isDone = isDone;
    }

    private Timestamp startDate,isDone;
}
