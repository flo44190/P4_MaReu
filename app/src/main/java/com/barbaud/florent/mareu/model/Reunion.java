package com.barbaud.florent.mareu.model;

import java.util.List;

/**
 * Created by florent on 16/04/2020.
 */
public class Reunion {


    private Salle mSalle;
    private String Tittle;
    private String Horaire;
    private String Date;
    private List<Participant> ParticipantPresent;

    public Reunion(Salle salle, String tittle, String horaire, String date, List<Participant> participant) {
        mSalle = salle;
        Tittle = tittle;
        Horaire = horaire;
        Date = date;
        ParticipantPresent = participant;
    }

    public int getSallecolor() {
        return mSalle.getColor();
    }

    public void setSalle(Salle salle) {
        mSalle = salle;
    }

    public String getSalle() {return mSalle.getName();}

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

    public List<Participant> getParticipantPresent() {
        return ParticipantPresent;
    }

    public void setParticipantPresent(List<Participant> participantPresent) {
        ParticipantPresent = participantPresent;
    }
}

