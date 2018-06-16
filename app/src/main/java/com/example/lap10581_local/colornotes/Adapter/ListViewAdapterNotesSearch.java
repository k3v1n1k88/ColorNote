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
import com.example.lap10581_local.colornotes.Objects.Note;
import com.example.lap10581_local.colornotes.R;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;

public class ListViewAdapterNotesSearch extends BaseAdapter{
    Context mContext;
    ArrayList<Note> listNotes;

    public ListViewAdapterNotesSearch(Context context){
        mContext = context;
        listNotes = new ArrayList();
    }
    public void setListNotes(ArrayList<Note> listNotes){
        this.listNotes = listNotes;
    }
    @Override
    public int getCount() {
        return listNotes.size();
    }

    @Override
    public Object getItem(int position) {
        return listNotes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 1;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ListViewAdapterNotesSearch.ViewHolderListViewSearch viewHolder = null;
        View row = convertView;

        if(row==null){
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            row = inflater.inflate(R.layout.list_view_row,null);
            viewHolder = new ListViewAdapterNotesSearch.ViewHolderListViewSearch();
            viewHolder.tvContent = (TextView) row.findViewById(R.id.main_listview_row_content);
            viewHolder.tvItemAlarm = (TextView) row.findViewById(R.id.main_listview_row_ic_alarm);
            viewHolder.tvDateCreate = (TextView) row.findViewById(R.id.main_listview_row_date_create);
            row.setTag(viewHolder);
        }
        else{
            viewHolder = (ListViewAdapterNotesSearch.ViewHolderListViewSearch) row.getTag();
        }
        viewHolder.tvContent.setText(listNotes.get(position).getmContent());
        //viewHolder.tvItemAlarm
        LocalDateTime localDateTime = listNotes.get(position).getmDateCreate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        int day = localDateTime.getDayOfMonth();
        int month = localDateTime.getMonthValue();
        int year = localDateTime.getYear();
        viewHolder.tvDateCreate.setText(day+"/"+month+"/"+year);
        row.setBackgroundColor(listNotes.get(position).getmColor().toArgb());
        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "get Click item", Toast.LENGTH_SHORT).show();
            }
        });
        return row;
    }

    private static class ViewHolderListViewSearch{
        TextView tvContent;
        TextView tvItemAlarm;
        TextView tvDateCreate;
    }
}
