package com.barbaud.florent.mareu.model;

import com.barbaud.florent.mareu.R;

/**
 * Created by florent on 17/04/2020.
 */
public enum Salle {

    A ("Salle A"),
    B ("Salle B"),
    C ("Salle C"),
    D ("Salle D"),
    E ("Salle E"),
    F ("Salle J"),
    G ("Salle G"),
    H ("Salle H"),
    I ("Salle I"),
    J ("Salle J");

    private String name;

    Salle(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
