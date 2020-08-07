package com.arthurpaiva96.reminderapp.model;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class EmailReminder {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String email;
    private String password;

    public EmailReminder(){}

    public EmailReminder(String email, String password){
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @NonNull
    @Override
    public String toString(){
        return this.email;
    }

    public boolean isGmail(){

        if(email.indexOf('@') < 0) return false;

        return email.substring(email.indexOf('@')).equals("@gmail.com");
    }

    public boolean allFieldsAreNotNull(){
        return (this.email != null && !this.email.isEmpty() &&
                this.password != null && !this.password.isEmpty());
    }
}
