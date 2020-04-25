package com.barbaud.florent.mareu.controler;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.barbaud.florent.mareu.API.ReunionApiService;
import com.barbaud.florent.mareu.R;
import com.barbaud.florent.mareu.di.DI;
import com.barbaud.florent.mareu.model.Participant;
import com.barbaud.florent.mareu.model.Reunion;
import com.barbaud.florent.mareu.model.Salle;
import com.barbaud.florent.mareu.view.ParticipantRecyclerViewAdapter;
import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AddReunion extends AppCompatActivity {

    private EditText mTittle;
    private TextView mDate;
    private TextView mHoraire;
    private ImageButton mBack;
    private Button mSave;
    private ImageButton mAddParticipant;
    private DatePickerDialog mDatePickerDialog;
    private TimePickerDialog mTimePickerDialog;
    private static ReunionApiService mService;
    Calendar Date = Calendar.getInstance();
    int mYear = Date.get(Calendar.YEAR); // current year
    int mMonth = Date.get(Calendar.MONTH); // current month
    int mDay = Date.get(Calendar.DAY_OF_MONTH); // current day
    int mHour = Date.get(Calendar.HOUR);
    int mMinute = Date.get(Calendar.MINUTE);
    Salle mSalle;
    private static final int ADD_PARTICIPANT_REQUEST_CODE = 42;
    List<Participant> mParticipantList = new ArrayList<>();
    RecyclerView mRecyclerView;
    Participant mParticipant;



    @Override
    protected void onCreate(Bundle SaveInstanceState){
        super.onCreate(SaveInstanceState);
        mService = DI.getReunionApiService();


        setContentView(R.layout.activity_add_reunion);
        mSave = findViewById(R.id.activity_add_save_btn);
        mDate = findViewById(R.id.activity_add_date_txt);
        mTittle = findViewById(R.id.activity_add_tittle_edit);
        mAddParticipant = findViewById(R.id.activit_add_participant_btn);
        mHoraire = findViewById(R.id.activity_add_horaire_txt);
        mBack = findViewById(R.id.activity_add_back_btn);
        displayDateTime();

        mRecyclerView = (RecyclerView) findViewById(R.id.activity_add_receclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        Spinner spinner = (Spinner) findViewById(R.id.activity_add_salle_spinner);
        spinner.setAdapter(new ArrayAdapter<Salle>(this, android.R.layout.simple_list_item_1,Salle.values()));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                mSalle = Salle.values()[pos];
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        mDate.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
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
               mTimePickerDialog = new TimePickerDialog(AddReunion.this, new TimePickerDialog.OnTimeSetListener() {
                   @Override
                   public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                       Date.set(Calendar.HOUR, hourOfDay);
                       Date.set(Calendar.MINUTE, minute);
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
                Reunion mReunions = new Reunion(mSalle, mTittle.getText().toString(), Date, mParticipantList);
                mService.createReunion(mReunions);
                Toast.makeText(getApplicationContext(), "Reunion Créer", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        mAddParticipant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent AddParticipant = new Intent(AddReunion.this, AddParticipant.class);
                startActivityForResult(AddParticipant,ADD_PARTICIPANT_REQUEST_CODE);
            }
        });
    }
    // Affiche de la date et de l'heure
    private void displayDateTime (){
        SimpleDateFormat ddMMyyy = new SimpleDateFormat("dd MMM yyyy");
        SimpleDateFormat HHmm = new SimpleDateFormat("HH:mm");
        mDate.setText(ddMMyyy.format(Date.getTime()));
        mHoraire.setText(HHmm.format(Date.getTime()));
    }

    private void initList(){
        mRecyclerView.setAdapter(new ParticipantRecyclerViewAdapter(mParticipantList));
    }

    @Override
    public void onResume(){
        super.onResume();
        initList();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (ADD_PARTICIPANT_REQUEST_CODE == requestCode && RESULT_OK == resultCode){
            mParticipant = data.getParcelableExtra(AddParticipant.BUNDLE_EXTRA_PARTICIPANT);
            mParticipantList.add(mParticipant);
        }
    }

    // Navigation vers cette activité
    public static void navigate(FragmentActivity activity) {
        Intent intent = new Intent(activity, AddReunion.class);
        ActivityCompat.startActivity(activity, intent, null);
    }

}
