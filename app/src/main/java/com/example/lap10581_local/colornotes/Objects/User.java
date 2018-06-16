package com.example.lap10581_local.colornotes.Objects;

import java.util.ArrayList;

public class User {
    String mUserName;
    String mPassword;
    String mName;
    String mEmail;

    //Constructor
    public User(String mUserName, String mPassword, String mName, String mEmail) {
        this.mUserName = mUserName;
        this.mPassword = mPassword;
        this.mName = mName;
        this.mEmail = mEmail;
    }

    public static boolean checkPassword(String username,String password){
        return true;
    }

    public boolean sync(){
        return true;
    }

    public boolean changePassword(String password){
        this.setmPassword(password);
        return true;
    }
    public boolean changeEmail(String email){
        this.mEmail = email;
        return true;
    }
    public boolean changeName(String name){
        this.mName = name;
        return true;
    }
    //Getter and setter

    public String getmUserName() {
        return mUserName;
    }

    public void setmUserName(String mUserName) {
        this.mUserName = mUserName;
    }

    public String getmPassword() {
        return mPassword;
    }

    public void setmPassword(String mPassword) {
        this.mPassword = mPassword;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

}
