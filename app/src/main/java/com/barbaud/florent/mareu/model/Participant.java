package com.barbaud.florent.mareu.model;

/**
 * Created by florent on 18/04/2020.
 */
public class Participant {
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
}
