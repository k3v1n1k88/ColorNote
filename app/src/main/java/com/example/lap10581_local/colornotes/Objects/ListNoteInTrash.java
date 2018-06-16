package com.example.lap10581_local.colornotes.Objects;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.Date;

public class ListNoteInTrash {

    private static ArrayList<Note> listNotes = null;
    private static ListNoteInTrash mInstance = null;

    private ListNoteInTrash(){
        if(listNotes==null)
            listNotes = new ArrayList();
    }

    public static ListNoteInTrash getInstance(){
        if(mInstance == null)
            mInstance = new ListNoteInTrash();
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
}
