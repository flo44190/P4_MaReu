package com.barbaud.florent.mareu;

import com.barbaud.florent.mareu.API.ReunionApiService;
import com.barbaud.florent.mareu.API.ReunionFictif;
import com.barbaud.florent.mareu.di.DI;
import com.barbaud.florent.mareu.model.Reunion;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Test unitaire MaReu
 */
@RunWith(JUnit4.class)
public class ExampleUnitTest {

    private ReunionApiService mService;

    @Before
    public void setup(){
        mService = DI.getNewInstanceApiService();
    }

    @Test
    public void getReunionWithSuccess() {
        List<Reunion> reunion = mService.getReunions();
        List<Reunion> reunionAttendu = ReunionFictif.REUNION_LIST;
        assertThat(reunion, IsIterableContainingInAnyOrder.containsInAnyOrder(reunionAttendu.toArray()));
    }

    @Test
    public void deleteReunionWithSuccess (){
        Reunion reunionDelette = mService.getReunions().get(0);
        mService.deleteReunion(reunionDelette);
        assertFalse(mService.getReunions().contains(reunionDelette));
    }

}