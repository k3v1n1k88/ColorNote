package com.example.lap10581_local.colornotes.Adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lap10581_local.colornotes.Objects.ListNote;
import com.example.lap10581_local.colornotes.R;


import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class GridViewAdapterNotes extends BaseAdapter {
    Context mContext;
    ListNote listNote = ListNote.getInstance();

    public GridViewAdapterNotes(Context context){
        mContext = context;
    }
    @Override
    public int getCount() {
        return listNote.getSize();
    }

    @Override
    public Object getItem(int position) {
        return listNote.getItem(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        GridViewAdapterNotes.ViewHolderGridView viewHolder = null;
        View row = convertView;

        if(row==null){
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            row = inflater.inflate(R.layout.grid_view_row,null);
            viewHolder = new GridViewAdapterNotes.ViewHolderGridView();
            viewHolder.tvContent = (TextView) row.findViewById(R.id.main_gridview_row_content);
            viewHolder.tvDateReminder = (TextView) row.findViewById(R.id.main_gridview_row_date_reminder);
            viewHolder.tvDateCreate = (TextView) row.findViewById(R.id.main_gridview_row_date_create);
            viewHolder.tvAlarm = (TextView) row.findViewById(R.id.main_gv_row_ic_alarm);
            row.setTag(viewHolder);
        }
        else{
            viewHolder = (GridViewAdapterNotes.ViewHolderGridView) row.getTag();
        }
        viewHolder.tvContent.setText(listNote.getItem(position).getmContent());
        Date date = listNote.getItem(position).getmDateReminder();
        if(date == null){
            viewHolder.tvAlarm.setVisibility(View.INVISIBLE);
        }
        else {
            viewHolder.tvDateReminder.setText(listNote.getItem(position).getmDateReminder().toString());
        }
        LocalDateTime localDateTime = listNote.getItem(position).getmDateCreate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        int day = localDateTime.getDayOfMonth();
        int month = localDateTime.getMonthValue();
        int year = localDateTime.getYear();
        viewHolder.tvDateCreate.setText(day+"/"+month+"/"+year);
        row.setBackgroundColor(listNote.getItem(position).getmColor().toArgb());
        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "get Click item", Toast.LENGTH_SHORT).show();
            }
        });
        return row;
    }

    private static class ViewHolderGridView{
        TextView tvContent;
        TextView tvDateReminder;
        TextView tvDateCreate;
        TextView tvAlarm;
    }

}
