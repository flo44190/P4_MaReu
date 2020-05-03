package com.barbaud.florent.mareu.API;

import android.util.Log;

import com.barbaud.florent.mareu.model.Participant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by florent on 18/04/2020.
 */
public class ParticipantFictif {
    public static final List<Participant> PARTICIPANT_LIST = Arrays.asList(
            new Participant(1, "https://i.pravatar.cc/400?img=69", "Francis", "CEO",
                    "06 12 30 24 10", "francis@lamzone.com"),
            new Participant(2,"https://i.pravatar.cc/400?img=68", "André", "Product Manager",
                    "06 12 30 24 10", "andre@lamzone.com"),
            new Participant(3,"https://i.pravatar.cc/400?img=49", "Marie", "Developer",
                    "06 12 30 24 10", "marie@lamzone.com"),
            new Participant(4,"https://i.pravatar.cc/400?img=18", "Simon", "Developer",
                    "06 12 30 24 10", "simon@lamzone.com"),
            new Participant(5,"https://i.pravatar.cc/400?img=14", "Florent", "Developer",
                    "06 12 30 24 10", "florent@lamzone.com"),
            new Participant(6,"https://i.pravatar.cc/400?img=28", "Alexandra", "Manager Project",
                    "06 12 30 24 10", "alexandra@lamzone.com"),
            new Participant(7,"https://i.pravatar.cc/400?img=11", "Jacquie", "Developer",
                    "06 12 30 24 10", "jacquie@lamzone.com"),
            new Participant(8,"https://i.pravatar.cc/400?img=9", "Marion", "Social Media Manager",
                    "06 12 30 24 10", "juliette@lamzone.com")
    );

    public static List<Participant> generateParticipant() {
        return new ArrayList<>(PARTICIPANT_LIST);
    }
}
