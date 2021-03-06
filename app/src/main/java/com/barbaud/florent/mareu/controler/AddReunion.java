package com.barbaud.florent.mareu.controler;

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
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.barbaud.florent.mareu.API.ReunionApiService;
import com.barbaud.florent.mareu.R;
import com.barbaud.florent.mareu.di.DI;
import com.barbaud.florent.mareu.events.DeleteParticipantEvent;
import com.barbaud.florent.mareu.model.Participant;
import com.barbaud.florent.mareu.model.Reunion;
import com.barbaud.florent.mareu.model.Salle;
import com.barbaud.florent.mareu.view.ParticipantRecyclerViewAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    int mYear = Date.get(Calendar.YEAR);
    int mMonth = Date.get(Calendar.MONTH);
    int mDay = Date.get(Calendar.DAY_OF_MONTH);
    int mHour = Date.get(Calendar.HOUR);
    int mMinute = Date.get(Calendar.MINUTE);
    Date CurrentDate = new Date();
    Salle mSalle;
    private static final int ADD_PARTICIPANT_REQUEST_CODE = 42;
    List<Participant> mParticipantList = new ArrayList<>();
    RecyclerView mRecyclerView;
    Participant mParticipant;
    private ImageView mSalleColor;



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
        mSalleColor = findViewById(R.id.activity_add_salle_img);
        displayDateTime();

        // Désactivation du button
        mSave.setEnabled(false);

        // Config RecyclerView Participant
        mRecyclerView = (RecyclerView) findViewById(R.id.activity_add_receclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Spinner choix de la salle
        Spinner spinner = (Spinner) findViewById(R.id.activity_add_salle_spinner);
        spinner.setAdapter(new ArrayAdapter<Salle>(this, android.R.layout.simple_list_item_1,Salle.values()));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                mSalle = Salle.values()[pos];
                mSalleColor.setBackgroundResource(mSalle.getColor());
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        // activation du button des qu'un titre est ecrit
        mTittle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                mSave.setEnabled(s.length() >0);
            }
        });

        // Choix de la date avec DatePicker
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

        // Choix de l'heure avec TimePicker
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

        // Retour a l'activité Precedante
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Création d'une nouvelle reunion
        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Reunion mReunions = new Reunion(mSalle, mTittle.getText().toString(), Date, mParticipantList);
                if (CurrentDate.compareTo(mReunions.getDate())>=0 || mParticipantList.size()==0) {
                    if (CurrentDate.compareTo(mReunions.getDate())>=0) {
                        Toast.makeText(getApplicationContext(), R.string.Error_Date,Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(getApplicationContext(), R.string.Error_Participant,Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                mService.createReunion(mReunions);
                Toast.makeText(getApplicationContext(), R.string.Reunion_Okay, Toast.LENGTH_SHORT).show();
                finish();}
            }
        });

        // Bouton pour ajout d'un Participant
        mAddParticipant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent AddParticipant = new Intent(AddReunion.this, AddParticipant.class);
                startActivityForResult(AddParticipant,ADD_PARTICIPANT_REQUEST_CODE);
            }
        });
    }

    @Override
    public void onResume(){
        super.onResume();
        mRecyclerView.setAdapter(new ParticipantRecyclerViewAdapter(mParticipantList));
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

    // Affichage de la date et de l'heure
    private void displayDateTime (){
        SimpleDateFormat ddMMyyy = new SimpleDateFormat("dd MMM yyyy");
        SimpleDateFormat HHmm = new SimpleDateFormat("HH:mm");
        mDate.setText(ddMMyyy.format(Date.getTime()));
        mHoraire.setText(HHmm.format(Date.getTime()));
    }

    // Recupération de l'utilisateur créer et ajout a la list des Participant
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

    // Suppression d'un participant avec EventBus
    @Subscribe
    public void onEvent(DeleteParticipantEvent event) {
        mParticipantList.remove(event.mParticipant);
        Log.i("DATA", "onDeleteParticipant: "+event.mParticipant+ "Supprimé");
        mRecyclerView.setAdapter(new ParticipantRecyclerViewAdapter(mParticipantList));
    }
}
