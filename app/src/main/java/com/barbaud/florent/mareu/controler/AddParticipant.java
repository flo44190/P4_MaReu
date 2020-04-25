
package com.barbaud.florent.mareu.controler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.barbaud.florent.mareu.R;
import com.barbaud.florent.mareu.model.Participant;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class AddParticipant extends AppCompatActivity {

    private EditText mName;
    private EditText mFonction;
    private EditText mMail;
    private EditText mPhone;
    private ImageView mAvatar;
    private Button mSave;
    private ImageButton mBack;

    public static final String BUNDLE_EXTRA_PARTICIPANT = "BUNDLE_EXTRA_PARTICIPANT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_participant);

        mName = findViewById(R.id.activity_participant_name_edit);
        mFonction = findViewById(R.id.activity_participant_fonction_edit);
        mPhone = findViewById(R.id.activity_participant_phone_edit);
        mMail = findViewById(R.id.activity_participant_mail_edit);
        mAvatar = findViewById(R.id.activity_participant_avatar_img);
        mSave = findViewById(R.id.activity_participant_save_btn);
        mBack = findViewById(R.id.activity_participant_back_btn);

        // Generation d'un avatar
        String Avatar = "https://i.pravatar.cc/150?u="+ System.currentTimeMillis();
        Glide.with(this).load(Avatar).placeholder(R.drawable.ic_people_alt_24px)
                .apply(RequestOptions.circleCropTransform()).into(mAvatar);

        // retour activity Precedente
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        // Création d'un nouveau Participant
        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Participant participant = new Participant(
                        System.currentTimeMillis(),Avatar,
                        mName.getText().toString(),
                        mFonction.getText().toString(),
                        mPhone.getText().toString(),
                        mMail.getText().toString()
                );
                Intent intent = new Intent();
                intent.putExtra(BUNDLE_EXTRA_PARTICIPANT, participant);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
