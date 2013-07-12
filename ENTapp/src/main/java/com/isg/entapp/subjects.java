package com.isg.entapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;

/**
 * Created by 1 on 7/11/13.
 */

public class subjects extends Activity {
    ProgressBar progressBarMath;
    ImageView myImageView;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_subject_list_item);
        progressBarMath = (ProgressBar) findViewById(R.id.progressmath);
        myImageView = (ImageView) findViewById(R.id.myImageView);
    }
}