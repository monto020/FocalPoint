package com.example.montoya.focalpoint;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Montoya on 5/16/2016.
 */
public class AppNameAdapter2 extends ArrayAdapter<AppName> {

    MySQLiteHelper mysqlite=new MySQLiteHelper(this.getContext());

    ViewHolder viewHolder;

    public AppNameAdapter2(Context context, int textViewResourceId,
                                ArrayList<AppName> nameList) {

        super(context, textViewResourceId, nameList);

    }


    //class for caching the views in a row
    private class ViewHolder
    {
        CheckBox checkBox;
    }



    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final AppName one_app=getItem(position);

        if(convertView==null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_row, parent, false);

//            viewHolder = new ViewHolder();
//
//            viewHolder.checkBox = (CheckBox) convertView.findViewById(R.id.checkBox);

            //link the cached views to the convertview
//            convertView.setTag(viewHolder);


        }
//        else {
//            viewHolder = (ViewHolder) convertView.getTag();
//        }

//        if (convertView == null) {
//            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_start_focusing, parent, false);
//        }

  //      viewHolder.checkBox.setText(one_app.getName());

        //Boolean status=false;

    //    if(mysqlite.getBlockedAppsStatus(one_app.getName(),StartFocusingActivity.id).getString(0).equals("1")) {
      //      status=true;
            //nameCheckBox.setChecked(true);
        //}

     //  viewHolder.checkBox.setChecked(status);


//        viewHolder.checkBox.setOnClickListener(new View.OnClickListener() {
//
//            public void onClick(View v) {
//
//                if (((CheckBox)v).isChecked()) {
//
//
//                    mysqlite.changeBlocked(1, one_app.getName(), StartFocusingActivity.id);
//                   StartFocusingActivity.blockedAppNamesList.clear();
//
//                    Cursor cursor=mysqlite.getBlockedApps(StartFocusingActivity.id);
//
//                    while( cursor.moveToNext()){
//                        StartFocusingActivity.blockedAppNamesList.add(cursor.getString(1));
//                        // do what ever you want here
//
//                    }cursor.close();
//                    //Toast.makeText(getContext(), "Blocked App List: " + ""+StartFocusingActivity.blockedAppNamesList, Toast.LENGTH_LONG).show();
//
//                    Toast.makeText(getContext(), "checked, \nthe name is: "+one_app.getName()+
//                            "\n the blocked status: "+mysqlite.getBlockedAppsStatus(one_app.getName(), StartFocusingActivity.id).getString(0),
//                            Toast.LENGTH_SHORT).show();
//
//
//                }
//                else{
//
//                    mysqlite.changeBlocked(0, one_app.getName(), StartFocusingActivity.id);
//                   // mysqlite.changeBlocked(1, one_app.getName(), StartFocusingActivity.id);
//                    StartFocusingActivity.blockedAppNamesList.clear();
//                    Cursor cursor=mysqlite.getBlockedApps(StartFocusingActivity.id);
//
//                    while( cursor.moveToNext()){
//                        StartFocusingActivity.blockedAppNamesList.add(cursor.getString(1));
//                        // do what ever you want here
//
//                    }cursor.close();
//
//                   // Toast.makeText(getContext(), "Blocked App List: " + ""+StartFocusingActivity.blockedAppNamesList, Toast.LENGTH_LONG).show();
//
//                    Toast.makeText(getContext(), "unchecked, \nthe name is: "+one_app.getName()+
//                                    "\n the blocked status: "+mysqlite.getBlockedAppsStatus(one_app.getName(), StartFocusingActivity.id).getString(0),
//                            Toast.LENGTH_SHORT).show();
//
//
//                }

          //  }
        //});


        mysqlite.close();
        //return the view to be displayed
        return convertView;
    }

}



