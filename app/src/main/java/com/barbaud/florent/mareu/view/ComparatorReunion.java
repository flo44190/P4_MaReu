package com.barbaud.florent.mareu.view;

import com.barbaud.florent.mareu.model.Reunion;

import java.util.Comparator;

/**
 * Created by florent on 24/04/2020.
 */
public class ComparatorReunion implements Comparator<Reunion> {

    // Tri reunion par date
    @Override
    public int compare(Reunion o1, Reunion o2) {
        int result = o1.getDate().compareTo(o2.getDate());
        return result;
    }
}