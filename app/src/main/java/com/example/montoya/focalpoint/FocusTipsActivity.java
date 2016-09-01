package com.example.montoya.focalpoint;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class FocusTipsActivity extends ListActivity {
ImageView focusTipsLogo;
TipsAdapter tipsAdapter;
//    Tip tip=new Tip();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getSupportActionBar().hide();

        setContentView(R.layout.activity_focus_tips);


        focusTipsLogo=(ImageView)findViewById(R.id.focus_time_image);
        focusTipsLogo.setImageResource(R.drawable.focus_tips_logo);

        ListView tipsList=getListView();

       // Toast.makeText(getApplicationContext(), "List: "+tip.tipArrayList,
         //       Toast.LENGTH_LONG).show();
        tipsAdapter=new TipsAdapter(this, Tip.tipArrayList);
        tipsList.setAdapter(tipsAdapter);
    }
}
