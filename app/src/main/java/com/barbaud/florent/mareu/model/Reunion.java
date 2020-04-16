package com.barbaud.florent.mareu.model;

/**
 * Created by florent on 16/04/2020.
 */
public class Reunion {


    private Salle mSalle;
    private String Tittle;
    private String Horaire;
    private String Date;
    private String Participant;

    public Reunion(Salle salle, String tittle, String horaire, String date, String participant) {
        mSalle = salle;
        Tittle = tittle;
        Horaire = horaire;
        Date = date;
        Participant = participant;
    }

    public String getSalle() {
        return mSalle.getName();
    }

    public void setSalle(Salle salle) {
        mSalle = salle;
    }

    public String getTittle() {
        return Tittle;
    }

    public void setTittle(String tittle) {
        Tittle = tittle;
    }

    public String getHoraire() {
        return Horaire;
    }

    public void setHoraire(String horaire) {
        Horaire = horaire;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getParticipant() {
        return Participant;
    }

    public void setParticipant(String participant) {
        Participant = participant;
    }
}

