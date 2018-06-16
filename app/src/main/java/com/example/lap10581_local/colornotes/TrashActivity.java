package com.example.lap10581_local.colornotes;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lap10581_local.colornotes.Adapter.ListViewTrashAdapterNotes;
import com.example.lap10581_local.colornotes.Objects.ListNoteInTrash;
import com.example.lap10581_local.colornotes.database.DatabaseHandler;

import java.util.logging.Level;
import java.util.logging.Logger;

public class TrashActivity extends AppCompatActivity {
    private static final Logger logger = Logger.getLogger(TrashActivity.class.toString());
    ListView listTrashNotes;
    ListViewTrashAdapterNotes listViewTrashAdapterNotes = null;
    DatabaseHandler databaseHandler = null;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trash);
        listTrashNotes = (ListView) findViewById(R.id.trash_lv_note);
        TextView tvNotify = (TextView) findViewById(R.id.trash_tv_notify) ;

        ListNoteInTrash listNoteInTrash = ListNoteInTrash.getInstance();

        databaseHandler = new DatabaseHandler(this);
        if(!databaseHandler.getAllNotesInTrash()){
            logger.log(Level.SEVERE,"Error when load database");
        }

        listViewTrashAdapterNotes = new ListViewTrashAdapterNotes(this);
        listTrashNotes.setAdapter(listViewTrashAdapterNotes);
        if(listViewTrashAdapterNotes.getCount()>=0){
            listTrashNotes.setVisibility(View.VISIBLE);
            tvNotify.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.getMenuInflater().inflate(R.menu.menu_trash,menu);
        return super.onCreateOptionsMenu(menu);
    }

}
