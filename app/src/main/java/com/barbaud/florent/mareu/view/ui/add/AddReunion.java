package com.barbaud.florent.mareu.view.ui.add;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.barbaud.florent.mareu.R;

import java.util.Calendar;

import butterknife.BindView;


public class AddReunion extends AppCompatActivity {

    @BindView(R.id.activity_add_date_txt)
    TextView mDate;
    @BindView(R.id.activity_add_horaire_txt)
    TextView mHoraire;

    Calendar Date;

    @Override
    protected void onCreate(Bundle SaveInstanceState){
        super.onCreate(SaveInstanceState);
        setContentView(R.layout.activity_add_reunion);
        displayDateTime();
    }

    private void displayDateTime (){
        Date = Calendar.getInstance();
        mDate.setText(Date.get(Calendar.DAY_OF_MONTH)+"/"+Date.get(Calendar.MONTH)+"/"+Date.get(Calendar.YEAR));
        mHoraire.setText(Date.get(Calendar.HOUR_OF_DAY)+":"+Date.get(Calendar.MINUTE));
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }


    public static void navigate(FragmentActivity activity) {
        Intent intent = new Intent(activity, AddReunion.class);
        ActivityCompat.startActivity(activity, intent, null);
    }

    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

}
