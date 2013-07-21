package com.isg.entapp.Activities;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.isg.entapp.R;

public class MainActivity extends Activity implements View.OnClickListener {
    private Button kaz,rus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        kaz = (Button) findViewById(R.id.kk_button);
        rus = (Button) findViewById(R.id.ru_button);
        kaz.setOnClickListener(this);
        rus.setOnClickListener(this);

        ActionBar actionBar = getActionBar();
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
        if(view.equals(kaz)){
            //TODO
            //set the current language to KAZAKH
        } else if(view.equals(rus)){
            //TODO
            //set the current language to RUSSIAN
        }
        //go to main tabs activity
        Intent tabsIntent;
        tabsIntent = new Intent(MainActivity.this, MainTabActivity.class);
        startActivity(tabsIntent);
    }
}
