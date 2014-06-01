package sdu.edu.entapp.Activities;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;


import sdu.edu.entapp.R;
import sdu.edu.entapp.Utilities.Constants;

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
        getActionBar().hide();

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
