package com.example.adminproject;



import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Seance {
    private int id,seanceGrpID,durationMinut,clientID,monitorID,isDone;
    private String monitor;

    public int getSeanceGrpID() {
        return seanceGrpID;
    }

    public void setSeanceGrpID(int seanceGrpID) {
        this.seanceGrpID = seanceGrpID;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public int getMonitorID() {
        return monitorID;
    }

    public void setMonitorID(int monitorID) {
        this.monitorID = monitorID;
    }

    public int getIsDone() {
        return isDone;
    }

    public void setIsDone(int isDone) {
        this.isDone = isDone;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    private String comments ;
    private Timestamp startDate;

    public Seance(int id, String Monitor, int durationMinut, Timestamp startDate) {
        this.id = id;
        this.monitor = Monitor;
        this.durationMinut = durationMinut;
        this.startDate = startDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMonitor() {
        return monitor;
    }

    public void setMonitor(String Monitor) {
        this.monitor = Monitor;
    }

    public String getDurationMinut() {
        double quotient = new Double(durationMinut)/new Double(60);
        int resultatEuclide = (int) quotient;
        int reste = (int)(quotient - resultatEuclide)*60;
        return resultatEuclide+"h"+reste+"min";
    }

    public void setDurationMinut(int durationMinut) {
        this.durationMinut = durationMinut;
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

    @Override
    public String toString() {
        return "Seance{" +
                "id=" + id +
                ", durationMinut=" + durationMinut +
                ", monitor='" + monitor + '\'' +
                ", startDate=" + startDate +
                '}';
    }
}

