package com.barbaud.florent.mareu.di;

import com.barbaud.florent.mareu.API.ReunionAPI;
import com.barbaud.florent.mareu.API.ReunionApiService;

/**
 * Created by florent on 25/04/2020.
 */
public class DI {

    private static final ReunionApiService service = new ReunionAPI();

    public static ReunionApiService getReunionApiService(){
        return service;
    }

    public static ReunionApiService getNewInstanceApiService(){
        return new ReunionAPI();
    }
}
