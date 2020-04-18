package com.barbaud.florent.mareu.API;

import android.util.Log;

import com.barbaud.florent.mareu.model.Participant;
import com.barbaud.florent.mareu.model.Reunion;
import com.barbaud.florent.mareu.model.Salle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by florent on 16/04/2020.
 */
public class ReunionFictif {

    private static ReunionApiService mService;

    private static List<Participant> mParticipants = ParticipantFictif.generateParticipant();

    private static List<Participant> Reunion1 = Arrays.asList(
            mParticipants.get(1),
            mParticipants.get(3)
    );

    private static List<Participant> Reunion2 = Arrays.asList(
            mParticipants.get(0),
            mParticipants.get(1)
    );

    private static List<Participant> Reunion3 = Arrays.asList(
            mParticipants.get(0),
            mParticipants.get(6),
            mParticipants.get(4)
    );

    private static List<Participant> Reunion4 = Arrays.asList(
            mParticipants.get(0),
            mParticipants.get(5),
            mParticipants.get(2)
    );

    public static final List<Reunion> REUNION_LIST = Arrays.asList(
           new Reunion(Salle.A,"Tittle 1", "18h30","20/04/20", Reunion1),
            new Reunion(Salle.B,"Tittle 2", "18h30","20/04/20", Reunion2),
            new Reunion(Salle.C,"Tittle 3", "18h30","20/04/20", Reunion3),
            new Reunion(Salle.D,"Tittle 4", "18h30","20/04/20", Reunion4)
    );



   public static List<Reunion> generateReunion() {
       Log.i("DATA", "generateReunion: Liste fictif genere");
       return new ArrayList<>(REUNION_LIST);
    }
}
