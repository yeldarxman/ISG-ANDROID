package com.isg.entapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.isg.entapp.ListAdapters.Read.ReadTopicSelectionListAdapter;
import com.isg.entapp.R;

/**
 * Created by 1 on 7/24/13.
 */
public class ReadTopicSelectionActivity extends SherlockActivity {
    String [] readTopicArray ;
    public ReadTopicSelectionListAdapter adapter;
    private ListView listview;
    byte[] bytes;

    WebView wv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.read_topics_list);
        listview = (ListView) findViewById(R.id.listview2);
        this.loadTopics();
        adapter = new ReadTopicSelectionListAdapter(this, readTopicArray);
        getSupportActionBar().setCustomView(R.layout.read_topic_title_custom_view);

        TextView title = (TextView) getSupportActionBar().getCustomView().findViewById(R.id.titleview);
        title.setText(getIntent().getExtras().getString("subject"));
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM | ActionBar.DISPLAY_SHOW_HOME);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //this.getActionBar().setIcon(R.drawable);
        listview.setAdapter(adapter);




        listview.setOnItemClickListener(
         new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ReadTopicSelectionActivity.this,PdfAsImageShowActivity.class);
                startActivity(intent);
                //readFromAssets("test2.pdf");
                //pdfLoadImages();//load images
                //pdfIntoBitmap();

            }


         }
        );

        //hide app icon
       getSupportActionBar().setDisplayUseLogoEnabled(false);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void loadTopics(){
        readTopicArray = getResources().getStringArray(R.array.read_topics_array);
    }


}