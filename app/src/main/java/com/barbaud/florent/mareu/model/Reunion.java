package com.barbaud.florent.mareu.model;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by florent on 16/04/2020.
 */
public class Reunion {


    private Salle mSalle;
    private String Tittle;
    private Calendar Date;
    private List<Participant> ParticipantPresent;

    public Reunion(Salle salle, String tittle, Calendar date, List<Participant> participant) {
        mSalle = salle;
        Tittle = tittle;
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

    public List<Participant> getParticipantPresent() {
        return ParticipantPresent;
    }

    public void setParticipantPresent(List<Participant> participantPresent) {
        ParticipantPresent = participantPresent;
    }

    public Calendar getDate(){
        return Date;
    }

    public String getJourMois(){
        String JourMois = Date.get(Calendar.DAY_OF_MONTH)+" / "+Date.get(Calendar.MONTH);
        return JourMois;
    }

    public String getHeures (){
        String Heure = Date.get(Calendar.HOUR_OF_DAY)+":"+Date.get(Calendar.MINUTE);
        return Heure;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public String getParticipantMail(List<Participant> Participant){
        List<String> Mail = new ArrayList<>();
        for (int n=0; n<Participant.size(); n++){
           Mail.add(Participant.get(n).getMail());
        }
        String MailParticipant = String.join(" , ", Mail);
        return MailParticipant;
    }
}

