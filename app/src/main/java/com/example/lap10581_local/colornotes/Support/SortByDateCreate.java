package com.example.lap10581_local.colornotes.Support;

import com.example.lap10581_local.colornotes.Objects.Note;

import java.util.ArrayList;
import java.util.Collections;

public class SortByDateCreate implements Sorter{
    @Override
    public void sort(ArrayList<Note> listNote) {
        for(int i =0;i<listNote.size()-1;i++){
            for(int j=i+1;j<listNote.size();j++){
                if(listNote.get(i).getmDateCreate().compareTo(listNote.get(j).getmDateCreate())>0){
                    Collections.swap(listNote,i,j);
                }
            }
        }
    }
}
