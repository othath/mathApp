package com.example.projets6.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatDelegate;

import com.example.projets6.R;

public class activity_popup extends Activity {
    TextView messageView;
    TextView messageView2;
    TextView messageView3;
    TextView messageView4;
    TextView messageView5;
    TextView messageView6;
    Context context;
    Resources resources;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup);
        SharedPreferences sharedPreferences=getSharedPreferences("save",MODE_PRIVATE);
        messageView = (TextView) findViewById(R.id.textView4);
        messageView2 =(TextView) findViewById(R.id.textView9);
        messageView3 = (TextView) findViewById(R.id.textView05);
        messageView4 = (TextView) findViewById(R.id.textView11);
        messageView5 = (TextView) findViewById(R.id.textView16);
        messageView6 = (TextView) findViewById(R.id.textView17);

        if (sharedPreferences.getBoolean("langue2",true)) {
            context = LocaleHelper.setLocale(activity_popup.this, "hi");
            resources = context.getResources();
            messageView.setText(resources.getString(R.string.classicmode));
            messageView2.setText(resources.getString(R.string.classic_popup));
            messageView3.setText(resources.getString(R.string.adventuremode));
            messageView4.setText(resources.getString(R.string.adventure_popup));
            messageView5.setText(resources.getString(R.string.multimode));
            messageView6.setText(resources.getString(R.string.multi_popup));


        }
        else{
            context = LocaleHelper.setLocale(activity_popup.this, "fr");
            resources = context.getResources();
            messageView.setText(resources.getString(R.string.classicmode));
            messageView2.setText(resources.getString(R.string.classic_popup));
            messageView3.setText(resources.getString(R.string.adventuremode));
            messageView4.setText(resources.getString(R.string.adventure_popup));
            messageView5.setText(resources.getString(R.string.multimode));
            messageView6.setText(resources.getString(R.string.multi_popup));


        }

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;


        getWindow().setLayout((int)(width*.8),(int)(height*.770));
    }




}
