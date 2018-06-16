package com.example.lap10581_local.colornotes.Adapter;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.app.Activity;
import android.widget.Toast;

import com.example.lap10581_local.colornotes.Objects.ListNote;
import com.example.lap10581_local.colornotes.Objects.Note;
import com.example.lap10581_local.colornotes.R;

import java.util.ArrayList;

public class ListViewAdapterNotes extends BaseAdapter{
    Context mContext;
    ListNote listNote = ListNote.getInstance();

    public ListViewAdapterNotes(Context context){
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
        return 1;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolderListView viewHolder = null;
        View row = convertView;

        if(row==null){
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            row = inflater.inflate(R.layout.list_view_row,null);
            viewHolder = new ViewHolderListView();
            viewHolder.tvContent = (TextView) row.findViewById(R.id.main_listview_row_content);
            viewHolder.tvItemAlarm = (TextView) row.findViewById(R.id.main_listview_row_ic_alarm);
            viewHolder.tvDateCreate = (TextView) row.findViewById(R.id.main_listview_row_date_create);
            row.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolderListView) row.getTag();
        }
        viewHolder.tvContent.setText(listNote.getItem(position).getmContent());
        //viewHolder.tvItemAlarm
        viewHolder.tvDateCreate.setText(listNote.getItem(position).getmDateCreate().toString());
        row.setBackgroundColor(listNote.getItem(position).getmColor().toArgb());
        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "get Click item", Toast.LENGTH_SHORT).show();
            }
        });
        return row;
    }

    private static class ViewHolderListView{
        TextView tvContent;
        TextView tvItemAlarm;
        TextView tvDateCreate;
    }
}
