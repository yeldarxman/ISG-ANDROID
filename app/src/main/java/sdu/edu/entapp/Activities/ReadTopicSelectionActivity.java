package sdu.edu.entapp.Activities;

import android.app.ActionBar;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;



import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import sdu.edu.entapp.ListAdapters.Read.ReadTopicSelectionListAdapter;
import sdu.edu.entapp.R;

/**
 * Created by 1 on 7/24/13.
 */
public class ReadTopicSelectionActivity extends Activity {
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
        getActionBar().setCustomView(R.layout.read_topic_title_custom_view);

        TextView title = (TextView) getActionBar().getCustomView().findViewById(R.id.titleview);
        title.setText(getIntent().getExtras().getString("subject"));
        getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM | ActionBar.DISPLAY_SHOW_HOME);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        //this.getActionBar().setIcon(R.drawable);
        listview.setAdapter(adapter);




        listview.setOnItemClickListener(
         new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                File file = new File(getExternalFilesDir(null), "test.pdf");

                try {
                    // Very simple code to copy a picture from the application's
                    // resource into the external file.  Note that this code does
                    // no error checking, and assumes the picture is small (does not
                    // try to copy it in chunks).  Note that if external storage is
                    // not currently mounted this will silently fail.
                    InputStream is = getResources().openRawResource(R.raw.test);
                    OutputStream os = new FileOutputStream(file);
                    byte[] data = new byte[is.available()];
                    is.read(data);
                    os.write(data);
                    is.close();
                    os.close();
                } catch (IOException e) {
                    // Unable to create file, likely because external storage is
                    // not currently mounted.
                    Log.w("ExternalStorage", "Error writing " + file, e);
                }

                if (file.exists()) {

                    Uri path = Uri.fromFile(file);
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setDataAndType(path, "application/pdf");
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);


                    try {
                        startActivity(intent);
                    }
                    catch (ActivityNotFoundException e) {
                        Toast.makeText(ReadTopicSelectionActivity.this,
                                "No Application Available to View PDF",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }

             //Intent intent = new Intent(ReadTopicSelectionActivity.this,PdfAsImageShowActivity.class);
                //intent.putExtra(PdfViewerActivity.EXTRA_PDFFILENAME, "/sdcard/test.pdf");
               // startActivity(intent);
                //readFromAssets("test2.pdf");
                //pdfLoadImages();//load images
                //pdfIntoBitmap();
         }
        );

        //hide app icon
       getActionBar().setDisplayUseLogoEnabled(false);
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