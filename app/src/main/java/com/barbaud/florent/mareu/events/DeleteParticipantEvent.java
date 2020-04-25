package com.barbaud.florent.mareu.events;

import com.barbaud.florent.mareu.model.Participant;

/**
 * Created by florent on 25/04/2020.
 */
public class DeleteParticipantEvent {

    public final Participant mParticipant;

    public DeleteParticipantEvent(Participant participant){
        this.mParticipant = participant;
    }
}
