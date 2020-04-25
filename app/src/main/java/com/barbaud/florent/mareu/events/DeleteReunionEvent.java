package com.barbaud.florent.mareu.events;

import com.barbaud.florent.mareu.model.Reunion;

/**
 * Created by florent on 25/04/2020.
 */
public class DeleteReunionEvent {

    public final Reunion mReunion;

    public DeleteReunionEvent (Reunion reunion){
        this.mReunion = reunion;
    }
}
