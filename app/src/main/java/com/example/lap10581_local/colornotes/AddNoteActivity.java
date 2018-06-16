package com.example.lap10581_local.colornotes;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.lap10581_local.colornotes.CustomView.LinedEditTextView;
import com.example.lap10581_local.colornotes.Objects.Note;

import org.joda.time.DateTime;

import java.util.Date;

@RequiresApi(api = Build.VERSION_CODES.O)
public class AddNoteActivity extends AppCompatActivity {

    LinedEditTextView linedEditTextView;
    Color color = Color.valueOf(Color.parseColor("#ffffff"));
    String content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        linedEditTextView = (LinedEditTextView) findViewById(R.id.add_note_content);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_note,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case android.R.id.home:
                super.onBackPressed();
                finish();
                break;
            case R.id.add_note_choose_blue:
                linedEditTextView.setBackgroundColor(Color.parseColor("#5656ff"));
                color = Color.valueOf(Color.parseColor("#5656ff"));
                Toast.makeText(this,"blue",Toast.LENGTH_SHORT).show();
                break;
            case R.id.add_note_choose_white:
                linedEditTextView.setBackgroundColor(Color.parseColor("#ffffff"));
                color = Color.valueOf(Color.parseColor("#ffffff"));
                Toast.makeText(this,"white",Toast.LENGTH_SHORT).show();
                break;
            case R.id.add_note_choose_green:
                linedEditTextView.setBackgroundColor(Color.parseColor("#67fd67"));
                color = Color.valueOf(Color.parseColor("#67fd67"));
                Toast.makeText(this,"green",Toast.LENGTH_SHORT).show();
                break;
            case R.id.add_note_choose_yellow:
                linedEditTextView.setBackgroundColor(Color.parseColor("#ffff8d"));
                color = Color.valueOf(Color.parseColor("#ffff8d"));
                Toast.makeText(this,"yellow",Toast.LENGTH_SHORT).show();
                break;
            case R.id.add_note_choose_red:
                linedEditTextView.setBackgroundColor(Color.parseColor("#ff6a9d"));
                color = Color.valueOf(Color.parseColor("#ff6a9d"));
                Toast.makeText(this,"red",Toast.LENGTH_SHORT).show();
                break;
            case R.id.add_note_finish:
                Intent intent = new Intent();
                String content = linedEditTextView.getText().toString();
                intent.putExtra("color",color.toArgb());
                intent.putExtra("content",content);
                setResult(RESULT_OK,intent);
                finish();
                break;
        }
        return true;
    }
}
