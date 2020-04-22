package com.barbaud.florent.mareu.controler;

import android.os.Bundle;

import com.barbaud.florent.mareu.API.ReunionFictif;
import com.barbaud.florent.mareu.R;
import com.barbaud.florent.mareu.model.Reunion;
import com.barbaud.florent.mareu.view.ReunionRecyclerViewAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    private List<Reunion> mReu = ReunionFictif.generateReunion();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Affichage du Recycler View
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.content_main_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ReunionRecyclerViewAdapter(mReu));

        // Floating Bouton vers Add Reunion
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddReunion();
            }
        });
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

}
