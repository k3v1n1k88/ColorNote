package com.example.lap10581_local.colornotes.Support;

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.example.lap10581_local.colornotes.Objects.Note;

import java.util.ArrayList;
import java.util.Collections;

public class SortByColor implements Sorter {
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void sort(ArrayList<Note> listNote) {
        for(int i =0;i<listNote.size()-1;i++){
            for(int j=i+1;j<listNote.size();j++){
                if(listNote.get(i).getmColor().toArgb()>listNote.get(j).getmColor().toArgb()){
                    Collections.swap(listNote,i,j);
                }
            }
        }
    }
}
