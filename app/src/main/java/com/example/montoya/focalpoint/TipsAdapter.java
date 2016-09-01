package com.example.montoya.focalpoint;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Montoya on 5/9/2016.
 */
public class TipsAdapter extends ArrayAdapter<Tip> {

    public TipsAdapter(Context context, ArrayList<Tip> arrayList) {
        super(context, 0, arrayList);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Tip one_tip = getItem(position);


        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.tips_list_row, parent, false);
        }
        TextView tipsText=(TextView)convertView.findViewById(R.id.tips_text);
        tipsText.setText(one_tip.getTip());

        return convertView;
    }
}