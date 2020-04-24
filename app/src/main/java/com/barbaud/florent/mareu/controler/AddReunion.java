package com.barbaud.florent.mareu.controler;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.barbaud.florent.mareu.R;
import com.barbaud.florent.mareu.model.Salle;
import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddReunion extends AppCompatActivity {

    private TextView mDate;
    private TextView mHoraire;
    private ImageButton mBack;
    private Button mSave;
    private DatePickerDialog mDatePickerDialog;
    private TimePickerDialog mTimePickerDialog;

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

        mDate.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                int mYear = Date.get(Calendar.YEAR); // current year
                int mMonth = Date.get(Calendar.MONTH); // current month
                int mDay = Date.get(Calendar.DAY_OF_MONTH); // current day
                mDatePickerDialog = new DatePickerDialog(AddReunion.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        // set day of month , month and year value in the edit text
                        Date.set(year,month,dayOfMonth);
                        displayDateTime();
                    }
                }, mYear, mMonth, mDay);
                mDatePickerDialog.show();
            }
        });
        mHoraire.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               int mHour = Date.get(Calendar.HOUR);
               int mMinute = Date.get(Calendar.MINUTE);
               mTimePickerDialog = new TimePickerDialog(AddReunion.this, new TimePickerDialog.OnTimeSetListener() {
                   @Override
                   public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                       Date.set(Calendar.YEAR,Calendar.MONTH,Calendar.DAY_OF_MONTH,hourOfDay,minute);
                       displayDateTime();
                   }
               }, mHour, mMinute,true);
               mTimePickerDialog.show();
           }
       });
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
        SimpleDateFormat ddMMyyy = new SimpleDateFormat("dd MMM yyyy");
        SimpleDateFormat HHmm = new SimpleDateFormat("HH:mm");
        mDate.setText(ddMMyyy.format(Date.getTime()));
        mHoraire.setText(HHmm.format(Date.getTime()));
    }

    // Navigation vers cette activité
    public static void navigate(FragmentActivity activity) {
        Intent intent = new Intent(activity, AddReunion.class);
        ActivityCompat.startActivity(activity, intent, null);
    }

}
