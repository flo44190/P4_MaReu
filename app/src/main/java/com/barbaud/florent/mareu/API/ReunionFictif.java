package com.barbaud.florent.mareu.API;

import com.barbaud.florent.mareu.model.Reunion;
import com.barbaud.florent.mareu.model.Salle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by florent on 16/04/2020.
 */
public class ReunionFictif {

    public static final List<Reunion> REUNION_LIST = Arrays.asList(
           new Reunion(Salle.A,"Tittle", "18h30","20/04/20", "Participant 1 - Participant 2"),
            new Reunion(Salle.B,"Tittle", "18h30","20/04/20", "Participant 1 - Participant 2"),
            new Reunion(Salle.C,"Tittle", "18h30","20/04/20", "Participant 1 - Participant 2"),
            new Reunion(Salle.D,"Tittle", "18h30","20/04/20", "Participant 1 - Participant 2")
    );

   public static List<Reunion> generateReunion() {
        return new ArrayList<>(REUNION_LIST);
    }
}
