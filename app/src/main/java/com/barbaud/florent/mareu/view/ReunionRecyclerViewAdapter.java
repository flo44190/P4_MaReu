package com.barbaud.florent.mareu.view;

import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.barbaud.florent.mareu.R;
import com.barbaud.florent.mareu.model.Participant;
import com.barbaud.florent.mareu.model.Reunion;
import com.bumptech.glide.Glide;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by florent on 16/04/2020.
 */
public class ReunionRecyclerViewAdapter extends RecyclerView.Adapter<ReunionRecyclerViewAdapter.ViewHolder> {

    private final List<Reunion> mReunions;
    private SimpleDateFormat JourMois = new SimpleDateFormat("dd-MMM",Locale.getDefault());
    private SimpleDateFormat HeureMinutes = new SimpleDateFormat("hh:mm", Locale.getDefault());


    public ReunionRecyclerViewAdapter (List<Reunion> items){
        this.mReunions = items;
    }

    @Override
    public ViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        Collections.sort(mReunions, new ComparatorReunion());
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
         View view = layoutInflater.inflate(R.layout.fragment_first, parent, false);
        return new ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder (final ViewHolder holder, int position) {
        Reunion reunion = mReunions.get(position);
        holder.mDate.setText(JourMois.format(reunion.getDate()));
        holder.mSalle.setBackgroundResource(reunion.getSallecolor());
        holder.mTittle.setText( reunion.getTittle() + " - " +HeureMinutes.format(reunion.getDate()));
        holder.mParticipant.setText(reunion.getParticipantMail(reunion.getParticipantPresent()));
    }

    @Override
    public int getItemCount (){
        return mReunions.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.Fragment_first_Salle_img)
        ImageView mSalle;
        @BindView(R.id.Fragment_first_Date_txt)
        TextView mDate;
        @BindView(R.id.Fragment_first_Tittle_txt)
        TextView mTittle;
        @BindView(R.id.Fragment_first_Participant_txt)
        TextView mParticipant;
        @BindView(R.id.Fragment_first_delette_btn)
        ImageButton mDelette;

        ViewHolder(View view){
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
