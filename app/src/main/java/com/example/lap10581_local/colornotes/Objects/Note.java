package com.example.lap10581_local.colornotes.Objects;

import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.example.lap10581_local.colornotes.Support.Sorter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Random;

public class Note implements Serializable,Cloneable{

    private int mID;
    private Date mDateCreate;
    private Color mColor;
    private String mContent;
    private Date mDateReminder;


    //Constructor-------------------------
    //
    private Note(){}

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
    //prototype

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public Object clone() throws CloneNotSupportedException {
        Note note = new Note();
        note.mID = this.mID;
        LocalDateTime localDateTimeCreate = mDateCreate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

        int year = localDateTimeCreate.getYear();
        int month = localDateTimeCreate.getMonthValue();
        int day = localDateTimeCreate.getDayOfMonth();
        int hour = localDateTimeCreate.getHour();
        int minute = localDateTimeCreate.getMinute();
        int second = localDateTimeCreate.getSecond();

        note.mDateCreate = new Date(year,month,day,hour,minute,second);
        note.mContent = new String(this.mContent);
        note.mColor = Color.valueOf(this.mColor.toArgb());

        if(!mDateReminder.equals("")&&mDateReminder!=null) {
            localDateTimeCreate = mDateCreate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

            year = localDateTimeCreate.getYear();
            month = localDateTimeCreate.getMonthValue();
            day = localDateTimeCreate.getDayOfMonth();
            hour = localDateTimeCreate.getHour();
            minute = localDateTimeCreate.getMinute();
            second = localDateTimeCreate.getSecond();

            note.mDateReminder = new Date(year,month,day,hour,minute,second);
        }
        else
            note.mDateReminder = null;
        return note;
    }
}
