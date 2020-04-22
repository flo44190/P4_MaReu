package com.barbaud.florent.mareu.controler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.barbaud.florent.mareu.R;
import com.barbaud.florent.mareu.model.Salle;
import com.barbaud.florent.mareu.view.ui.add.DatePickerFragment;
import com.barbaud.florent.mareu.view.ui.add.TimePickerFragment;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Calendar;

public class AddReunion extends AppCompatActivity {

    private TextView mDate;
    private TextView mHoraire;
    private ImageButton mBack;
    private Button mSave;

    Calendar Date = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle SaveInstanceState){
        super.onCreate(SaveInstanceState);
        setContentView(R.layout.activity_add_reunion);
        mSave = findViewById(R.id.activity_add_save_btn);
        mDate = findViewById(R.id.activity_add_date_txt);
        mHoraire = findViewById(R.id.activity_add_horaire_txt);
        mBack = findViewById(R.id.activity_add_back_btn);

        Spinner spinner = (Spinner) findViewById(R.id.activity_add_salle_spinner);
        spinner.setAdapter(new ArrayAdapter<Salle>(this, android.R.layout.simple_list_item_1,Salle.values()));

        displayDateTime();

        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
    // Affiche de la date et de l'heure actuel
    private void displayDateTime (){
        mDate.setText(Date.get(Calendar.DAY_OF_MONTH)+"/"+Date.get(Calendar.MONTH)+"/"+Date.get(Calendar.YEAR));
        mHoraire.setText(Date.get(Calendar.HOUR_OF_DAY)+":"+Date.get(Calendar.MINUTE));
    }

    // Selection de l'heure
    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    // Selection de la date
    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    // Navigation vers cette activité
    public static void navigate(FragmentActivity activity) {
        Intent intent = new Intent(activity, AddReunion.class);
        ActivityCompat.startActivity(activity, intent, null);
    }

}
