package com.barbaud.florent.mareu.API;

import com.barbaud.florent.mareu.model.Participant;
import com.barbaud.florent.mareu.model.Reunion;

import java.util.List;

/**
 * Created by florent on 18/04/2020.
 */
public interface ReunionApiService {

    List<Reunion> getReunions();

    void deleteReunion (Reunion reunion);

    void createReunion (Reunion reunion);
}
