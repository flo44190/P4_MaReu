package com.barbaud.florent.mareu.model;

import android.graphics.Color;

import com.barbaud.florent.mareu.R;

/**
 * Created by florent on 17/04/2020.
 */
public enum Salle {

    A ("Salle A", R.color.A),
    B ("Salle B", R.color.B),
    C ("Salle C", R.color.C),
    D ("Salle D", R.color.D),
    E ("Salle E", R.color.E),
    F ("Salle J", R.color.F),
    G ("Salle G", R.color.G),
    H ("Salle H", R.color.H),
    I ("Salle I", R.color.I),
    J ("Salle J", R.color.J);

    private String name;
    private int color;

    Salle(String name, int mColor){
        this.name = name;
        this.color = mColor;
    }

    public String getName() {
        return name;
    }

    public int getColor() {
        return color;
    }
}
