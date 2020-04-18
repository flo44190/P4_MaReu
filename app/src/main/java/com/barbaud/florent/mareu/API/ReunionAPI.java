package com.barbaud.florent.mareu.API;

import com.barbaud.florent.mareu.model.Participant;
import com.barbaud.florent.mareu.model.Reunion;

import java.util.List;

/**
 * Created by florent on 18/04/2020.
 */
public class ReunionAPI implements ReunionApiService {

    private final List<Reunion> mReunions = ReunionFictif.generateReunion();

    @Override
    public List<Reunion> getReunions(){
        return mReunions;
    }

    @Override
    public void deleteReunion (Reunion reunion){
        mReunions.remove(reunion);
    }

    @Override
    public void createReunion (Reunion reunion){
        mReunions.add(reunion);
    }

}
