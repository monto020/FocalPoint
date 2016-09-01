package com.example.montoya.focalpoint;

import android.content.DialogInterface;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.jjoe64.graphview.GraphView;


import java.util.ArrayList;
import java.util.Calendar;

public class ViewPerformanceActivity extends AppCompatActivity {

    ImageView focusStatsLogo;
    Spinner monthSpinner;
    Spinner yearSpinner;
    MySQLiteHelper mysqlite = new MySQLiteHelper(this);
    GraphView graphView;
    int currentYear;
    int currentMonth;
    ArrayList<String> sessionTime = new ArrayList<String>();
    ArrayList<Entry> entries = new ArrayList<>();
    LineChart chart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_view_performance);

        monthSpinner = (Spinner) findViewById(R.id.months_spinner);
        yearSpinner = (Spinner) findViewById(R.id.years_spinner);
        //addListenerOnSpinnerItemSelection();
        focusStatsLogo = (ImageView) findViewById(R.id.focus_stats_image);
        focusStatsLogo.setImageResource(R.drawable.focus_stats_logo);


        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> monthAdapter = ArrayAdapter.createFromResource(this,
                R.array.months_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        monthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner

        Calendar c = Calendar.getInstance();
        currentMonth = c.get(Calendar.MONTH);

        monthSpinner.setAdapter(monthAdapter);
        // monthSpinner.setOnItemSelectedListener(this);
        monthSpinner.setSelection(currentMonth);

        currentYear = c.get(Calendar.YEAR);


//         Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> yearAdapter = ArrayAdapter.createFromResource(this,
                R.array.years_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        yearSpinner.setAdapter(yearAdapter);
        // yearSpinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

        chart = (LineChart) findViewById(R.id.chart);

        currentMonth = monthSpinner.getSelectedItemPosition() ;
//        Toast.makeText(getApplicationContext(), "Month:" + currentMonth,
//                    Toast.LENGTH_LONG).show();
        currentYear = yearSpinner.getSelectedItemPosition() + 2016;
//        Toast.makeText(getApplicationContext(), "Year:" + currentYear,
//                Toast.LENGTH_LONG).show();

        Cursor cursor = mysqlite.getGraphPoint(currentMonth, currentYear);

        while (cursor.moveToNext()) {
            sessionTime.add(cursor.getString(0));
            // do what ever you want here
        }
        cursor.close();

        entries.add(new Entry(0, 0));
        for (int i = 1; i < sessionTime.size(); i++) {
            entries.add(new Entry(Integer.parseInt(sessionTime.get(i)), i));
        }

        LineDataSet dataset = new LineDataSet(entries, "# of Sessions");

        ArrayList<String> labels = new ArrayList<String>();

        int timeAmount = 0;
        for (int i = 0; i < 200; i++) {
            labels.add(timeAmount + "");
            timeAmount = timeAmount + 1;
        }


        LineData data = new LineData(labels, dataset);
        chart.setData(data);

        chart.setDescription("Focus Sessions");
        dataset.setDrawFilled(true);
    }}
//
//    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
//
//        Spinner spinner = (Spinner) parent;
//        if (spinner.getId() == R.id.months_spinner) {
//            currentMonth = monthSpinner.getSelectedItemPosition() + 1;
//            Toast.makeText(getApplicationContext(), "Month:" + currentMonth,
//                    Toast.LENGTH_LONG).show();
//
//            //do this
//        } else if (spinner.getId() == R.id.years_spinner) {
//            currentYear = yearSpinner.getSelectedItemPosition() + 2016;
//            Toast.makeText(getApplicationContext(), "Year:" + currentYear,
//                    Toast.LENGTH_LONG).show();
//            //do this
//        }
//
//        Cursor cursor = mysqlite.getGraphPoint(currentMonth, currentYear);
//
//        while (cursor.moveToNext()) {
//            sessionTime.add(cursor.getString(0));
//            // do what ever you want here
//        }
//        cursor.close();
//
//
//        for (int i = 1; i < sessionTime.size(); i++) {
//            entries.add(new Entry(Integer.parseInt(sessionTime.get(i)), i));
//        }
//
//        LineDataSet dataset = new LineDataSet(entries, "# of Sessions");
//
//
//
//    }
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
////        monthSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
////
////            public void onItemSelected(AdapterView<?> adapterView,
////                                       View view, int i, long l) {
////                // TODO Auto-generated method stub
////                currentMonth = monthSpinner.getSelectedItemPosition() + 1;
//////                Toast.makeText(getApplicationContext(), "Current month from list"+currentMonth,
//////                        Toast.LENGTH_LONG).show();
////
////                Toast.makeText(getApplicationContext(), "Year:"+currentYear,
////                        Toast.LENGTH_LONG).show();
////
////                Cursor cursor = mysqlite.getGraphPoint(currentMonth, currentYear);
////
////                while (cursor.moveToNext()) {
////                    sessionTime.add(cursor.getString(0));
////                    // do what ever you want here
////                }
////                cursor.close();
////
////                for (int a = 1; a < sessionTime.size(); a++) {
////                    entries.add(new Entry(Integer.parseInt(sessionTime.get(a)), a));
////                }
////
////                LineDataSet dataset = new LineDataSet(entries, "# of Sessions");
////
////                ArrayList<String> labels = new ArrayList<String>();
////
////                int timeAmount = 0;
////                for (int b = 0; b < 200; b++) {
////                    labels.add(timeAmount + "");
////                    timeAmount = timeAmount + 1;
////                }
////
////
////                LineData data = new LineData(labels, dataset);
////                chart.setData(data);
////
////                chart.setDescription("Focus Sessions");
////                dataset.setDrawFilled(true);
////
////
////            }
////
////            // If no option selected
////            public void onNothingSelected(AdapterView<?> arg0) {
////                // TODO Auto-generated method stub
////
////            }
////
////        });
////
////        yearSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
////
////            public void onItemSelected(AdapterView<?> adapterView,
////                                       View view, int i, long l) {
////                // TODO Auto-generated method stub
////                currentYear = yearSpinner.getSelectedItemPosition() + 2016;
////                Toast.makeText(getApplicationContext(), "Year:"+currentYear,
////                        Toast.LENGTH_LONG).show();
////                Toast.makeText(getApplicationContext(), "Month:"+currentMonth,
////                        Toast.LENGTH_LONG).show();
////                Cursor cursor = mysqlite.getGraphPoint(currentMonth, currentYear);
////
////                while (cursor.moveToNext()) {
////                    sessionTime.add(cursor.getString(0));
////                    // do what ever you want here
////                }
////                cursor.close();
////
////                for (int a = 1; a < sessionTime.size(); a++) {
////                    entries.add(new Entry(Integer.parseInt(sessionTime.get(a)), a));
////                }
////
////                LineDataSet dataset = new LineDataSet(entries, "# of Sessions");
////
////                ArrayList<String> labels = new ArrayList<String>();
////
////                int timeAmount = 0;
////                for (int b = 0; b < 200; b++) {
////                    labels.add(timeAmount + "");
////                    timeAmount = timeAmount + 1;
////                }
////
////
////                LineData data = new LineData(labels, dataset);
////                chart.setData(data);
////
////                chart.setDescription("Focus Sessions");
////                dataset.setDrawFilled(true);
////
////            }
////
////            // If no option selected
////            public void onNothingSelected(AdapterView<?> arg0) {
////                // TODO Auto-generated method stub
////
////            }
////
////        });
//
//


//    }

