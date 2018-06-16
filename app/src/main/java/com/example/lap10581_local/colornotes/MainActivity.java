package com.example.lap10581_local.colornotes;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lap10581_local.colornotes.Adapter.GridViewAdapterNotes;
import com.example.lap10581_local.colornotes.Adapter.ListViewAdapterNotes;
import com.example.lap10581_local.colornotes.CustomView.DialogChooseSortType;
import com.example.lap10581_local.colornotes.Objects.Constant;
import com.example.lap10581_local.colornotes.Objects.ListNote;
import com.example.lap10581_local.colornotes.Objects.Note;
import com.example.lap10581_local.colornotes.Support.SortByColor;
import com.example.lap10581_local.colornotes.Support.SortByDateCreate;
import com.example.lap10581_local.colornotes.Support.SortByName;
import com.example.lap10581_local.colornotes.database.DatabaseHandler;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity implements DialogChooseSortType.DialogListener {
    private static final Logger logger = Logger.getLogger("MainActivity");
    private ListView lvNotes;
    private GridView gvNotes;
    private TextView tvNotify;
    private static int style = Constant.LIST_VIEW_STYLE;

    private ListViewAdapterNotes listViewAdapterNote;
    private GridViewAdapterNotes gridViewAdapterNotes;

    Button btnSortBy;
    FloatingActionButton fabAddNote;
    DatabaseHandler databaseHandler = null;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        connectElement();
        setFuction4Element();

        databaseHandler = new DatabaseHandler(this);
        if(!databaseHandler.getAllNote()){
            logger.log(Level.SEVERE,"Error when load database");
        }

        listViewAdapterNote = new ListViewAdapterNotes(this);
        lvNotes.setAdapter(listViewAdapterNote);

        gridViewAdapterNotes = new GridViewAdapterNotes(this);
        gvNotes.setAdapter(gridViewAdapterNotes);

        if(listViewAdapterNote.getCount()>=0){
            tvNotify.setVisibility(View.INVISIBLE);

        }
        setViewStyle();
    }

    private void setViewStyle() {
        switch (style){
            case Constant.GRDID_VIEW_STYLE:
                lvNotes.setVisibility(View.GONE);
                gvNotes.setVisibility(View.VISIBLE);
                break;
            case Constant.LIST_VIEW_STYLE:
                lvNotes.setVisibility(View.VISIBLE);
                gvNotes.setVisibility(View.GONE);
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id  = item.getItemId();
        switch(id){
            case R.id.main_menu_change_view_style:
                style++;
                style = style%2+100;
                setViewStyle();
                break;
            case R.id.main_menu_search:
                Intent intent = new Intent(MainActivity.this,SearchNoteActivity.class);
                startActivity(intent);
                default:
                    break;
        }
        return true;
    }

    private void setFuction4Element() {
        btnSortBy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogChooseSortType dialogChooseSortType = new DialogChooseSortType();
                dialogChooseSortType.show(getSupportFragmentManager(),"Alo");
            }
        });
        fabAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AddNoteActivity.class);
                startActivityForResult(intent, Constant.REQUEST_CODE_ADDNOTE);
            }
        });

    }
    private void connectElement() {
        btnSortBy = (Button) findViewById(R.id.btnSortBy);
        lvNotes = (ListView)findViewById(R.id.main_lv_notes);
        gvNotes = (GridView)findViewById(R.id.main_gv_notes);
        fabAddNote = (FloatingActionButton) findViewById(R.id.main_fab_add_note);
        tvNotify = (TextView) findViewById(R.id.main_tv_notify);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        System.out.println("Ve chua may?");
            switch (requestCode){
            case Constant.REQUEST_CODE_ADDNOTE:
                if(resultCode == RESULT_OK && data!=null){
                    //Toast.makeText(this, "Got it", Toast.LENGTH_SHORT).show();
                    if(data.getExtras()!=null){
                        String content = data.getStringExtra("content");
                        int color = data.getIntExtra("color",0);
                        logger.log(Level.OFF,"content: "+content+"\ncolor:"+color);
                        Date dateCreate = new Date();
                        Note note = new Note(dateCreate, Color.valueOf(color),content);
                        databaseHandler.addNote(note);
                        listViewAdapterNote.notifyDataSetChanged();
                        gridViewAdapterNotes.notifyDataSetChanged();
                    }
                }
                break;
            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void choosen(int selection) {
        ListNote listView = ListNote.getInstance();
        switch (selection){
            case Constant.SORT_BY_NAME:
                listView.setSorter(new SortByName());
                Toast.makeText(this, "Sort by name ", Toast.LENGTH_SHORT).show();
                break;
            case Constant.SORT_BY_DATE:
                listView.setSorter(new SortByDateCreate());
                Toast.makeText(this, "Sort by date create ", Toast.LENGTH_SHORT).show();
                break;
            case Constant.SORT_BY_COLOR:
                Toast.makeText(this, "Sort by color ", Toast.LENGTH_SHORT).show();
                listView.setSorter(new SortByColor());
                break;
            case Constant.SORT_BY_REMIND:
                Toast.makeText(this, "Sort by reminder day ", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        listView.sort();
        listViewAdapterNote.notifyDataSetChanged();
        gridViewAdapterNotes.notifyDataSetChanged();
    }
}
