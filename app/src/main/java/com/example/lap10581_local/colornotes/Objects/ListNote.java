package com.example.lap10581_local.colornotes.Objects;

import android.graphics.Color;

import com.example.lap10581_local.colornotes.Support.SortByName;
import com.example.lap10581_local.colornotes.Support.Sorter;
import com.example.lap10581_local.colornotes.database.DatabaseHandler;

import java.util.ArrayList;
import java.util.Date;

public class ListNote {
    private static ArrayList<Note> listNotes = null;
    private static ListNote mInstance = null;

    private static Sorter mSorter = null;

    private ListNote(){
        if(listNotes==null)
            listNotes = new ArrayList();
        mSorter = new SortByName();
    }

    public static ListNote getInstance(){
        if(mInstance == null)
            mInstance = new ListNote();
        return mInstance;
    }

    //Method
    public static ArrayList<Note> getListNotes(){
        return listNotes;
    }

    public static boolean addNote(Note note){
        return listNotes.add(note);
    }

    public static void removeNote(int position){
        //Truy van co so du lieu
        listNotes.remove(position);
    }
    public static void setListNote(ArrayList<Note> list){
        listNotes = list;
    }

    public static Note getItem(int position){
        return listNotes.get(position);
    }
    //Method search list notes
    public static ArrayList<Note> findNoteByContent(String content){
        ArrayList<Note> listNoteResult = new ArrayList<>();
        for(Note note :listNotes){
            if(note.getmContent().contains(content)){
                listNoteResult.add(note);
            }
        }
        if(listNoteResult.size()>0)
            return listNoteResult;
        return null;
    }

    public static int getSize(){
        return listNotes.size();
    }
    public static ArrayList<Note> findNoteByColor(Color color){
        ArrayList<Note> listNoteResult = new ArrayList<>();
        for(Note note :listNotes){
            if(note.getmColor().equals(color)){
                listNoteResult.add(note);
            }
        }
        if(listNoteResult.size()>0)
            return listNoteResult;
        return null;
    }
    public static ArrayList<Note> findNoteByDateCreate(Date dateCreate){
        ArrayList<Note> listNoteResult = new ArrayList<>();
        for(Note note :listNotes){
            if(note.getmColor().equals(dateCreate)){
                listNoteResult.add(note);
            }
        }
        if(listNoteResult.size()>0)
            return listNoteResult;
        return null;
    }
    public static ArrayList<Note> findNoteByDateReminder(Date dateReminder){
        ArrayList<Note> listNoteResult = new ArrayList<>();
        for(Note note :listNotes){
            if(note.getmColor().equals(dateReminder)){
                listNoteResult.add(note);
            }
        }
        if(listNoteResult.size()>0)
            return listNoteResult;
        return null;
    }
    public void setSorter(Sorter sorter){
        mSorter = sorter;
    }
    public static void sort(){
        mSorter.sort(listNotes);
    }
}
