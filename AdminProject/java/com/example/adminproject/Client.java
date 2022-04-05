package com.example.adminproject;


import android.graphics.Bitmap;

import org.json.JSONObject;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Client {
    private int id;
    private int priceRate;
    private String fName,lName,iNumber,email,pasword,clientPhone,identityDoc,notes;
    private Timestamp birthDate, inscriptionDate, ensurenceValidity, licenceValidity;
    private Bitmap photo;

    public String getPasword() {
        return pasword;
    }

    public void setPasword(String pasword) {
        this.pasword = pasword;
    }

    private ArrayList<Seance> seances;
    private ArrayList<Seance> historiques;

    public Client(int id, int priceRate, String fName, String lName, String iNumber,
                  String email, String pasword, String clientPhone, String identityDoc,
                  String notes, Timestamp birthDate, Timestamp inscriptionDate,
                  Timestamp ensurenceValidity, Timestamp licenceValidity) {
        this.id = id;
        this.priceRate = priceRate;
        this.fName = fName;
        this.lName = lName;
        this.iNumber = iNumber;
        this.email = email;
        this.pasword = pasword;
        this.clientPhone = clientPhone;
        this.identityDoc = identityDoc;
        this.notes = notes;
        this.birthDate = birthDate;
        this.inscriptionDate = inscriptionDate;
        this.ensurenceValidity = ensurenceValidity;
        this.licenceValidity = licenceValidity;
    }

    public String getIdentityDoc() {
        return identityDoc;
    }

    public void setIdentityDoc(String identityDoc) {
        this.identityDoc = identityDoc;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
    public Client() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPriceRate() {
        return priceRate;
    }

    public void setPriceRate(int priceRate) {
        this.priceRate = priceRate;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getiNumber() {
        return iNumber;
    }

    public void setiNumber(String iNumber) {
        this.iNumber = iNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getClientPhone() {
        return clientPhone;
    }

    public void setClientPhone(String clientPhone) {
        this.clientPhone = clientPhone;
    }

    public String getBirthDate() {
        Date myDate=new Date(birthDate.getTime());
        DateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String myDateStr=df.format(myDate);
        return myDateStr ;
    }

    public void setBirthDate(Timestamp birthDate) {
        this.birthDate = birthDate;
    }

    public String getInscriptionDate() {
        Date myDate=new Date(inscriptionDate.getTime());
        DateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String myDateStr=df.format(myDate);
        return myDateStr ;
    }

    public void setInscriptionDate(Timestamp inscriptionDate) {
        this.inscriptionDate = inscriptionDate;
    }

    public String getEnsurenceValidity() {
        Date myDate=new Date(ensurenceValidity.getTime());
        DateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String myDateStr=df.format(myDate);
        return myDateStr ;
    }

    public void setEnsurenceValidity(Timestamp ensurenceValidity) {
        this.ensurenceValidity = ensurenceValidity;
    }

    public String getLicenceValidity() {
        Date myDate=new Date(licenceValidity.getTime());
        DateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String myDateStr=df.format(myDate);
        return myDateStr ;
    }

    public void setLicenceValidity(Timestamp licenceValidity) {
        this.licenceValidity = licenceValidity;
    }

    public Bitmap getPhoto() {
        return photo;
    }

    public void setPhoto(Bitmap photo) {
        this.photo = photo;
    }

    public boolean findSeance(Seance seance){
        for (Seance s:this.seances) {
            if(s.getId() == seance.getId()) { return true;}
        }
        return false;
    }
    public boolean findHistorique(Seance seance){
        for (Seance s:this.historiques) {
            if(s.getId() == seance.getId()) { return true;}
        }
        return false;
    }

    public ArrayList<Seance> getSeances() {
        return seances;
    }

    public void setSeances(ArrayList<Seance> seances) {
        this.seances = seances;
    }
    public void addSeances(Seance seance) {
        if (seances==null)
            seances  = new ArrayList<Seance>();
        // if(findSeance(seance)==false)
        this.seances.add(seance);
    }
    public ArrayList<Seance> getHistoriques() {
        return historiques;
    }

    public void setHistoriques(ArrayList<Seance> historiques) {
        this.historiques = historiques;
    }
    public void addHistorique(Seance seance)
    {
        if(historiques == null)
            historiques  = new ArrayList<Seance>();
        //if(findHistorique(seance)==false)
        this.historiques.add(seance);
    }



    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", password=" + pasword +
                ", priceRate=" + priceRate +
                ", fName='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                ", iNumber='" + iNumber + '\'' +
                ", email='" + email + '\'' +
                ", clientPhone='" + clientPhone + '\'' +
                ", birthDate=" + birthDate +
                ", inscriptionDatse=" + inscriptionDate +
                ", ensurenceValidity=" + ensurenceValidity +
                ", licenceValidity=" + licenceValidity +
                ", photo =" + photo +
                '}';
    }
}
