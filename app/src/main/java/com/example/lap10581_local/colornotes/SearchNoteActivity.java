package com.example.lap10581_local.colornotes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.lap10581_local.colornotes.Adapter.ListViewAdapterNotesSearch;
import com.example.lap10581_local.colornotes.Objects.ListNote;
import com.example.lap10581_local.colornotes.Objects.Note;

import java.util.ArrayList;

public class SearchNoteActivity extends AppCompatActivity {
    EditText edtSearch;
    ListView lvSearch;
    ListViewAdapterNotesSearch listViewAdapterNotesSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_note);
        edtSearch = (EditText)findViewById(R.id.search_edt_search);
        lvSearch = (ListView)findViewById(R.id.search_lv);

        listViewAdapterNotesSearch = new ListViewAdapterNotesSearch(this);
        lvSearch.setAdapter(listViewAdapterNotesSearch);

        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                ListNote listNote = ListNote.getInstance();
                String content = edtSearch.getText().toString();
                ArrayList<Note> listNotesSearch =  listNote.findNoteByContent(content);
                if(listNotesSearch!=null) {
                    listViewAdapterNotesSearch.setListNotes(listNotesSearch);
                }
                else{
                    listViewAdapterNotesSearch.setListNotes(new ArrayList<Note>());
                }
                listViewAdapterNotesSearch.notifyDataSetChanged();
            }
        });
    }
}
