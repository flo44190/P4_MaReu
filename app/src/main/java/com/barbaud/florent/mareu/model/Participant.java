package com.barbaud.florent.mareu.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by florent on 18/04/2020.
 */
public class Participant implements Parcelable {
    private long id;
    private String avatarUrl;
    private String name;
    private String fonction;
    private String phoneNumber;
    private String mail;

    public Participant (long id, String AvatarUrl, String Name, String Fonction, String PhoneNumber, String Mail){
        this.id = id;
        this.avatarUrl = AvatarUrl;
        this.name = Name;
        this.fonction = Fonction;
        this.phoneNumber = PhoneNumber;
        this.mail = Mail;
    }

    protected Participant(Parcel in) {
        id = in.readLong();
        avatarUrl = in.readString();
        name = in.readString();
        fonction = in.readString();
        phoneNumber = in.readString();
        mail = in.readString();
    }

    public static final Creator<Participant> CREATOR = new Creator<Participant>() {
        @Override
        public Participant createFromParcel(Parcel in) {
            return new Participant(in);
        }

        @Override
        public Participant[] newArray(int size) {
            return new Participant[size];
        }
    };

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFonction() {
        return fonction;
    }

    public void setFonction(String fonction) {
        this.fonction = fonction;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(avatarUrl);
        dest.writeString(name);
        dest.writeString(fonction);
        dest.writeString(phoneNumber);
        dest.writeString(mail);
    }
}
