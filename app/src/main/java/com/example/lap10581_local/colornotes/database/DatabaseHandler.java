package com.example.lap10581_local.colornotes.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.example.lap10581_local.colornotes.Objects.ListNote;
import com.example.lap10581_local.colornotes.Objects.Note;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

public class DatabaseHandler extends SQLiteOpenHelper{
    private static final String DATABASE_NAME = "ColorNote";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "Note";

    private static final String KEY_ID = "id";
    private static final String KEY_DAY_CREATE = "date_create";
    private static final String KEY_COLOR = "color";
    private static final String KEY_CONTENT = "content";
    private static final String KEY_DAY_REMINDER = "date_reminder";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_students_table = String.format("CREATE TABLE %s(%s INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, %s TEXT NOT NULL, %s INTEGER NOT NULL, %s TEXT, %s TEXT)", TABLE_NAME, KEY_ID, KEY_DAY_CREATE, KEY_COLOR, KEY_CONTENT,KEY_DAY_REMINDER);
        db.execSQL(create_students_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String drop_students_table = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);
        db.execSQL(drop_students_table);

        onCreate(db);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public boolean addNote(Note note) {
        SQLiteDatabase db = this.getWritableDatabase();
        Date dateCreate = note.getmDateCreate();
        Date dateReminder = note.getmDateReminder();

        LocalDateTime localDateTimeCreate = dateCreate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime localDateTimeReminder;
        String dateCreateString = String.format("%d-%d-%d %d:%d:%d",
                localDateTimeCreate.getYear(),
                localDateTimeCreate.getMonthValue(),
                localDateTimeCreate.getDayOfMonth(),
                localDateTimeCreate.getHour(),
                localDateTimeCreate.getMinute(),
                localDateTimeCreate.getSecond()
        );
        String dateReminderString;
        if(dateReminder != null){
            localDateTimeReminder = dateReminder.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            dateReminderString = String.format("%d-%d-%d %d:%d:%d",
                    localDateTimeReminder.getYear(),
                    localDateTimeReminder.getMonthValue(),
                    localDateTimeReminder.getDayOfMonth(),
                    localDateTimeReminder.getHour(),
                    localDateTimeReminder.getMinute(),
                    localDateTimeReminder.getSecond());
        }
        else{
            dateReminderString = "";
        }
        ContentValues values = new ContentValues();
        values.put(KEY_DAY_CREATE, dateCreateString);
        values.put(KEY_COLOR, note.getmColor().toArgb());
        values.put(KEY_CONTENT, note.getmContent());
        values.put(KEY_DAY_REMINDER,dateReminderString);

        long res = db.insert(TABLE_NAME, null, values);
        db.close();
        if(res>0){
            return ListNote.getInstance().addNote(note);
        }
        return false;
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public Note getNode(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, null, KEY_ID + " = ?", new String[] { String.valueOf(id) },null, null, null);
        if(cursor != null)
            cursor.moveToFirst();
        Note note = createNoteFromCursor(cursor);
        return note;
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public boolean getAllNote() {
        ArrayList<Note>  listNote = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if(cursor==null&&cursor.getCount()<=0)
            return false;
        cursor.moveToFirst();

        while(cursor.isAfterLast() == false) {
            Note note = createNoteFromCursor(cursor);
            listNote.add(note);
            cursor.moveToNext();
        }
        if(listNote.size()<=0)
            return false;

        ListNote.getInstance().setListNote(listNote);
        return true;
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public boolean updateColor(int positionNoteInListNote, Color color) {
        SQLiteDatabase db = this.getWritableDatabase();
        ListNote listNote = ListNote.getInstance();
        int id = listNote.getItem(positionNoteInListNote).getmID();

        ContentValues values = new ContentValues();

        values.put(KEY_COLOR, color.toArgb());

        int res = db.update(TABLE_NAME, values, KEY_ID + " = ?", new String[] { String.valueOf(id) });
        db.close();

        if(res>0) {
            listNote.getItem(positionNoteInListNote).setmColor(color);
            return true;
        }
        return false;
    }
    public boolean updateContent(int positionNoteInListNote,String content) {
        SQLiteDatabase db = this.getWritableDatabase();
        ListNote listNote = ListNote.getInstance();
        int id = listNote.getItem(positionNoteInListNote).getmID();

        ContentValues values = new ContentValues();

        values.put(KEY_CONTENT,content);

        int res = db.update(TABLE_NAME, values, KEY_ID + " = ?", new String[] { String.valueOf(id) });
        db.close();

        if(res>0) {
            listNote.getItem(positionNoteInListNote).setmContent(content);
            return true;
        }
        return false;
    }

    public boolean deleteNote(int positionNoteInListNote) {
        SQLiteDatabase db = this.getWritableDatabase();
        ListNote listNote = ListNote.getInstance();
        int id = listNote.getItem(positionNoteInListNote).getmID();

        int res = db.delete(TABLE_NAME, KEY_ID + " = ?", new String[] { String.valueOf(id) });
        db.close();
        if(res>0) {
            listNote.removeNote(positionNoteInListNote);
            return true;
        }
        return false;
    }

    public boolean updateDateReminder(int positionNoteInListNote,Date dateReminder) {
        SQLiteDatabase db = this.getWritableDatabase();
        ListNote listNote = ListNote.getInstance();
        int id = listNote.getItem(positionNoteInListNote).getmID();

        String dateReminderString = String.format("%d-%d-%d %d:%d:%d",dateReminder.getYear(),dateReminder.getMonth(),dateReminder.getDate(),dateReminder.getHours(),dateReminder.getMinutes(),dateReminder.getSeconds());

        ContentValues values = new ContentValues();

        values.put(KEY_DAY_REMINDER,dateReminderString);

        int res = db.update(TABLE_NAME, values, KEY_ID + " = ?", new String[] { String.valueOf(id) });
        db.close();

        if(res>0){
            listNote.getItem(positionNoteInListNote).setmDateReminder(dateReminder);
            return true;
        }
        return false;
    }

    //Support method
    @RequiresApi(api = Build.VERSION_CODES.O)
    private Note createNoteFromCursor(Cursor cursor) {
        int id = cursor.getInt(0);
        String dateCreateString = cursor.getString(1);
        int colorCode = cursor.getInt(2);
        String content = cursor.getString(3);
        String dateReminderString = cursor.getString(4);

        Color color = Color.valueOf(colorCode);
        Date dateCreate = null;
        Date dateReminder = null;

        try {
            dateCreate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateCreateString);
            if(!dateReminderString.equals(""))
                dateReminder = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateReminderString);
        } catch (ParseException e) {
            System.out.println("Invalid format day or day is not valid");
            return null;
        }
        Note note = new Note(id,dateCreate,color,content,dateReminder);
        return note;
    }
}
