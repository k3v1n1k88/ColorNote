package com.example.lap10581_local.colornotes;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class DialogChooseSortType extends AppCompatDialogFragment{
    public static int selection = 0;

    DialogListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view =  inflater.inflate(R.layout.dialog_choose_sort_by,null);

        TextView txtName = (TextView) view.findViewById(R.id.dialog_sort_by_name);
        TextView txtColor = (TextView) view.findViewById(R.id.dialog_sort_by_color);
        TextView txtDate = (TextView) view.findViewById(R.id.dialog_sort_by_date_create);
        TextView txtReminder = (TextView) view.findViewById(R.id.dialog_sort_by_reminder);

        txtName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selection = Constant.SORT_BY_NAME;
                listener.choosen(selection);
                dismiss();
            }
        });
        txtColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selection = Constant.SORT_BY_COLOR;
                listener.choosen(selection);
                dismiss();
            }
        });
        txtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selection = Constant.SORT_BY_DATE;
                listener.choosen(selection);
                dismiss();
            }
        });
        txtReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selection = Constant.SORT_BY_REMIND;
                listener.choosen(selection);
                dismiss();
            }
        });
//        .setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int id = v.getId();
//                Log.d("ID: ", String.valueOf(id));
//                switch(id){
//                    case R.id.dialog_sort_by_color:
//                        selection = Constant.SORT_BY_COLOR;
//                        Toast.makeText(getActivity(), String.valueOf(selection), Toast.LENGTH_SHORT).show();
//                        break;
//                    case R.id.dialog_sort_by_name:
//                        selection = Constant.SORT_BY_NAME;
//                        Toast.makeText(getActivity(), String.valueOf(selection), Toast.LENGTH_SHORT).show();
//                        break;
//                    case R.id.dialog_sort_by_reminder:
//                        selection = Constant.SORT_BY_REMIND;
//                        Toast.makeText(getActivity(), String.valueOf(selection), Toast.LENGTH_SHORT).show();
//                        break;
//                    case R.id.dialog_sort_by_date_create:
//                        selection = Constant.SORT_BY_DATE;
//                        Toast.makeText(getActivity(), String.valueOf(selection), Toast.LENGTH_SHORT).show();
//                        break;
//                    default:
//                        break;
//                }
//                //listener.choosen(selection);
//                //dismiss();
//            }
//        });

        builder.setView(view);
        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (DialogListener) context;
        }catch (Exception e){
            throw new ClassCastException(context.toString() + "must implement DialogListener");
        }
    }

    public interface DialogListener{
        void choosen(int selection);
    }
}
