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
import com.example.lap10581_local.colornotes.Objects.ListNoteInTrash;
import com.example.lap10581_local.colornotes.R;

public class ListViewTrashAdapterNotes extends BaseAdapter {
    Context mContext;
    ListNoteInTrash listNote = ListNoteInTrash.getInstance();

    public ListViewTrashAdapterNotes(Context context){
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
        ListViewTrashAdapterNotes.ViewHolderListViewTrash viewHolder = null;
        View row = convertView;

        if(row==null){
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            row = inflater.inflate(R.layout.list_view_row,null);
            viewHolder = new ListViewTrashAdapterNotes.ViewHolderListViewTrash();
            viewHolder.tvContent = (TextView) row.findViewById(R.id.main_listview_row_content);
            viewHolder.tvItemAlarm = (TextView) row.findViewById(R.id.main_listview_row_ic_alarm);
            viewHolder.tvDateCreate = (TextView) row.findViewById(R.id.main_listview_row_date_create);
            row.setTag(viewHolder);
        }
        else{
            viewHolder = (ListViewTrashAdapterNotes.ViewHolderListViewTrash) row.getTag();
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

    private static class ViewHolderListViewTrash{
        TextView tvContent;
        TextView tvItemAlarm;
        TextView tvDateCreate;
    }
}
