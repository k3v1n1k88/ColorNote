package com.example.lap10581_local.colornotes;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.PopupMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements DialogChooseSortType.DialogListener {
    private ListView mListViewNotes;
    Button btnSortBy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        mListViewN    otes = (ListView)findViewById(R.id.main_listview_notes);
        connectElement();
        setFuction4Element();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

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
    }
    private void connectElement() {
        btnSortBy = (Button) findViewById(R.id.btnSortBy);
    }

    @Override
    public void choosen(int selection) {
        Toast.makeText(this, "Selection "+selection, Toast.LENGTH_SHORT).show();
    }
}
