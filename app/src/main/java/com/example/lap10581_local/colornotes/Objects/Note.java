package com.example.lap10581_local.colornotes.Objects;

import android.graphics.Color;

import java.io.Serializable;
import java.util.Date;
import java.util.Random;

public class Note implements Serializable {

    private int mID;
    private Date mDateCreate;
    private Color mColor;
    private String mContent;
    private Date mDateReminder;


    //Constructor-------------------------
    //
    public Note(Date mDateCreate) {
        this.mDateCreate = mDateCreate;
        this.mColor = new Color();
        this.mContent = "";
    }

    public Note(Date mDateCreate, Color mColor) {
        this.mDateCreate = mDateCreate;
        this.mColor = mColor;
        this.mContent = "";
    }

    public Note(Date mDateCreate, Color mColor, String mContent) {
        this.mDateCreate = mDateCreate;
        this.mColor = mColor;
        this.mContent = mContent;
    }

    public Note(Date mDateCreate, Color mColor, String mContent, Date mDateReminder) {
        this.mDateCreate = mDateCreate;
        this.mColor = mColor;
        this.mContent = mContent;
        this.mDateReminder = mDateReminder;
    }

    public Note(int mID, Date mDateCreate, Color mColor, String mContent, Date mDateReminder) {
        this.mID = mID;
        this.mDateCreate = mDateCreate;
        this.mColor = mColor;
        this.mContent = mContent;
        this.mDateReminder = mDateReminder;
    }

    //Getter and setter
    public Date getmDateCreate() {
        return mDateCreate;
    }

    public void setmDateCreate(Date mDateCreate) {
        this.mDateCreate = mDateCreate;
    }

    public Color getmColor() {
        return mColor;
    }

    public void setmColor(Color mColor) {
        this.mColor = mColor;
    }

    public String getmContent() {
        return mContent;
    }

    public void setmContent(String mContent) {
        this.mContent = mContent;
    }

    public Date getmDateReminder() {
        return mDateReminder;
    }

    public void setmDateReminder(Date mDateReminder) {
        this.mDateReminder = mDateReminder;
    }

    public int getmID(){
        return mID;
    }
    //Method
    public void changeColor(Color color){
        mColor = color;
        //truy van co so du lieu
    }
    public void update(Color color, String content, Date dateReminder){
        mColor = color;
        mContent = content;
        mDateReminder = dateReminder;
        //Truy van co so du lieu
    }
    public void updateColor(Color color){
        mColor = color;
        //Truy van co so du lieu
    }
    public void updateContent(String content){
        mContent = content;
        //truy van co so du lieu
    }
    public void updateContent(Date dateReminder){
        mDateReminder = dateReminder;
        //truy van co so du lieu
    }

}
