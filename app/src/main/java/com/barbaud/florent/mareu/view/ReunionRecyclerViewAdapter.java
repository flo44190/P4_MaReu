package com.barbaud.florent.mareu.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.barbaud.florent.mareu.R;
import com.barbaud.florent.mareu.model.Reunion;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by florent on 16/04/2020.
 */
public class ReunionRecyclerViewAdapter extends RecyclerView.Adapter<ReunionRecyclerViewAdapter.ViewHolder> {

    private final List<Reunion> mReunions;

    public ReunionRecyclerViewAdapter (List<Reunion> items){
        this.mReunions = items;
    }

    @Override
    public ViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
         View view = layoutInflater.inflate(R.layout.fragment_first, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder (final ViewHolder holder, int position) {
        Reunion reunion = mReunions.get(position);
        holder.mTittle.setText( reunion.getTittle() + " - " + reunion.getHoraire());
        holder.mParticipant.setText(reunion.getParticipant());
    }

    @Override
    public int getItemCount (){
        return mReunions.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
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
