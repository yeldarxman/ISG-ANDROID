package com.isg.entapp.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;

import com.actionbarsherlock.app.SherlockActivity;
import com.isg.entapp.R;
import com.isg.entapp.Utilities.Constants;

/**
 * Created by 1 on 9/7/13.
 */
public class RedirectingActivity extends SherlockActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences settings = getSharedPreferences(Constants.PREFS_NAME, 0);
        String language = settings.getString("language", "");
        System.out.println("Activity started ");
        if (language.equals("kk") || language.equals("ru")){
            Intent intent1 = new Intent(this,MainTabActivity.class);
            startActivity(intent1);
        }else {
            Intent intent2 = new Intent(this,MainActivity.class);
            startActivity(intent2);
        }
        this.finish();
    }


}
