package com.barbaud.florent.mareu.controler;

import android.os.Bundle;

import com.barbaud.florent.mareu.API.ReunionApiService;
import com.barbaud.florent.mareu.R;
import com.barbaud.florent.mareu.di.DI;
import com.barbaud.florent.mareu.events.DeleteReunionEvent;
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
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

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

        // Config du Recycler View
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

    // Génération de la list de la reunion et affichage recyclerView
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
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       switch (item.getItemId()){
           case R.id.action_filter :
               Toast.makeText(getApplicationContext(), "Action de filtre", Toast.LENGTH_SHORT).show();
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
                items.remove(items.get(i));
                i--;
            }
        }
    }

    // Suppression d'une reunion avec EventBus
    @Subscribe
    public void onEvent (DeleteReunionEvent event){
        mService.deleteReunion(event.mReunion);
        Log.i("DATA", "onDeleteParticipant: "+event.mReunion+ "Supprimé");
        initList();
    }

}
