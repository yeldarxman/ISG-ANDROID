package com.isg.entapp.Activities;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.actionbarsherlock.app.SherlockActivity;
import com.isg.entapp.R;
import com.isg.entapp.Utilities.Constants;

import java.util.Locale;

public class MainActivity extends SherlockActivity implements View.OnClickListener {
    private Button kaz,rus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        kaz = (Button) findViewById(R.id.kk_button);
        rus = (Button) findViewById(R.id.ru_button);
        kaz.setOnClickListener(this);
        rus.setOnClickListener(this);

        com.actionbarsherlock.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public void onClick(View view) {
        String language = "";
        if(view.equals(kaz)){
            language = "kk";
            System.out.println("Kaz button pressed:");
        } else if(view.equals(rus)){
            language = "ru";
            System.out.println("Rus button pressed:");
        }
        //SharedPreferences
        SharedPreferences settings = getSharedPreferences(Constants.PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("language", language);
        // Commit the edits!
        editor.commit();

        //go to main tabs activity
        Intent tabsIntent;
        tabsIntent = new Intent(MainActivity.this, MainTabActivity.class);
        startActivity(tabsIntent);
        this.finish();
    }
}
