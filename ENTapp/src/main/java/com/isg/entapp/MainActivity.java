package com.isg.entapp;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends Activity implements View.OnClickListener {
    private Button kaz,rus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        kaz = (Button) findViewById(R.id.kk_button);
        rus = (Button) findViewById(R.id.ru_button);
        kaz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.findViewById(R.id.kk_button);
                Intent subject;
                subject = new Intent(MainActivity.this, subjects.class);
                startActivity(subject);
            }
        });

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

    }
}
