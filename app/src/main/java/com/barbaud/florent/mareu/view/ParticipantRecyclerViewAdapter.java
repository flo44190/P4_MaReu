package com.barbaud.florent.mareu.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.barbaud.florent.mareu.R;
import com.barbaud.florent.mareu.model.Participant;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by florent on 25/04/2020.
 */
public class ParticipantRecyclerViewAdapter extends RecyclerView.Adapter<ParticipantRecyclerViewAdapter.ViewHolder> {

    private List<Participant> mParticipants;

    public ParticipantRecyclerViewAdapter (List<Participant> items){
        this.mParticipants = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.fragment_participant, parent, false);
        return new ParticipantRecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Participant participant = mParticipants.get(position);
        Glide.with(holder.mAvatar.getContext())
                .load(participant.getAvatarUrl())
                .apply(RequestOptions.circleCropTransform())
                .into(holder.mAvatar);
        holder.mName.setText(participant.getName());
        holder.mMail.setText(participant.getMail());
    }

    @Override
    public int getItemCount() {
        return mParticipants.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.fragment_participant_avatar_img)
        ImageView mAvatar;
        @BindView(R.id.fragment_participant_name_txt)
        TextView mName;
        @BindView(R.id.fragment_participant_mail_txt)
        TextView mMail;
        @BindView(R.id.fragment_participant_delete_btn)
        ImageButton mDelette;

        ViewHolder(View view){
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
