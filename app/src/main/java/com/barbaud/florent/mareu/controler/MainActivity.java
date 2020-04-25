package com.barbaud.florent.mareu.controler;

import android.os.Bundle;

import com.barbaud.florent.mareu.API.ReunionAPI;
import com.barbaud.florent.mareu.API.ReunionApiService;
import com.barbaud.florent.mareu.API.ReunionFictif;
import com.barbaud.florent.mareu.R;
import com.barbaud.florent.mareu.di.DI;
import com.barbaud.florent.mareu.model.Reunion;
import com.barbaud.florent.mareu.view.ReunionRecyclerViewAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Date;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private ReunionApiService mService;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mService = DI.getReunionApiService();

        // Affichage du Recycler View
        mRecyclerView = (RecyclerView) findViewById(R.id.content_main_recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Floating Bouton vers Add Reunion
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddReunion();
            }
        });
    }

    private void initList(){
        List<Reunion> mReu = mService.getReunions();
        DisplayReunion(mReu);
        mRecyclerView.setAdapter(new ReunionRecyclerViewAdapter(mReu));
    }

    @Override
    public void onResume(){
        super.onResume();
        initList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_filter) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // Fonction pour naviguer vers Add Reunion
    public void AddReunion() {
        AddReunion.navigate(this);
    }

    // Suppresion des reunion anterieur a la date du jours

    private void DisplayReunion (List<Reunion> items){
        Date date = new Date();
        int n = 0;
        for (int i = 0; i<items.size(); i++){
            if (date.compareTo(items.get(i).getDate())>=0){
                Log.i("DATA", "Suppresion de" +items.get(i).getTittle());
                items.remove(items.get(i));
                i--;
            }
            else {
                Log.i("DATA", "Pas toucher: " +items.get(i).getTittle());
            }
        }
    }

}
