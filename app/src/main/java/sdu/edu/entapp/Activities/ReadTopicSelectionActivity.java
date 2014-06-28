package sdu.edu.entapp.Activities;

import android.app.ActionBar;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
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

import sdu.edu.entapp.ListAdapters.Read.ReadTopicSelectionListAdapter;
import sdu.edu.entapp.R;

/**
 * Created by 1 on 7/24/13.
 */
public class ReadTopicSelectionActivity extends Activity {
    String [] readTopicArray ;
    public ReadTopicSelectionListAdapter adapter;
    private ListView listview;
    private String subjectName;
    byte[] bytes;

    WebView wv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        subjectName = getIntent().getExtras().getString("subject");
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
                String subname = adapterView.getItemAtPosition(i).toString().toLowerCase();
                Log.d("Chapter name",subname);
                String fileName = ((ReadTopicSelectionListAdapter.ViewHolder)view.getTag()).subjectTopicName;
                File file = new File(getExternalFilesDir(null), fileName+".pdf");
                //Toast.makeText(getApplicationContext(), fileName, Toast.LENGTH_LONG).show();

                try {
                    InputStream is = null;
                    if (subjectName.equalsIgnoreCase("Казахский язык")) {
                        if (fileName.equals("read1")) {
                            is = getResources().openRawResource(R.raw.kaz_lang_topic1_ru);
                        } else if (fileName.equals("read2")) {
                            is = getResources().openRawResource(R.raw.kaz_lang_topic2_ru);
                        } else if (fileName.equals("read3")) {
                            is = getResources().openRawResource(R.raw.kaz_lang_topic3_ru);
                        } else if (fileName.equals("read4")) {
                            is = getResources().openRawResource(R.raw.kaz_lang_topic4_ru);
                        }else if (fileName.equals("read5")) {
                            is = getResources().openRawResource(R.raw.kaz_lang_topic5_ru);
                        }
                    }
                    if (subjectName.equalsIgnoreCase("Қазақ тілі")){
                        if (fileName.equals("read1"))  {
                            is = getResources().openRawResource(R.raw.kaz_lang_topic1_kz);
                        } else if (fileName.equals("read2")) {
                            is = getResources().openRawResource(R.raw.kaz_lang_topic2_kz);
                        } else if (fileName.equals("read3")) {
                            is = getResources().openRawResource(R.raw.kaz_lang_topic3_kz);
                        } else if (fileName.equals("read4")) {
                            is = getResources().openRawResource(R.raw.kaz_lang_topic4_kz);
                        } else if (fileName.equals("read5")) {
                            is = getResources().openRawResource(R.raw.kaz_lang_topic5_kz);
                        }
                    }
                    if (subjectName.equalsIgnoreCase("Орыс тілі") || subjectName.equalsIgnoreCase("Русский язык")){
                        if (fileName.equals("read1"))  {
                            is = getResources().openRawResource(R.raw.rus_lang_topic1);
                        } else if (fileName.equals("read2")) {
                            is = getResources().openRawResource(R.raw.rus_lang_topic2);
                        } else if (fileName.equals("read3")) {
                            is = getResources().openRawResource(R.raw.rus_lang_topic3);
                        } else if (fileName.equals("read4")) {
                            is = getResources().openRawResource(R.raw.rus_lang_topic4);
                        } else if (fileName.equals("read5")) {
                            is = getResources().openRawResource(R.raw.rus_lang_topic5);
                        }
                    }

                    if (subjectName.equalsIgnoreCase("Қазақстан тарихы")){
                        if (fileName.equals("read1"))  {
                            is = getResources().openRawResource(R.raw.kaz_hist_topic1_kz);
                        } else if (fileName.equals("read2")) {
                            is = getResources().openRawResource(R.raw.kaz_hist_topic2_kz);
                        } else if (fileName.equals("read3")) {
                            is = getResources().openRawResource(R.raw.kaz_hist_topic3_kz);
                        } else if (fileName.equals("read4")) {
                            is = getResources().openRawResource(R.raw.kaz_hist_topic4_kz);
                        } else if (fileName.equals("read5")) {
                            is = getResources().openRawResource(R.raw.kaz_hist_topic5_kz);
                        }
                    }
                    if (subjectName.equalsIgnoreCase("История Казахстана")){
                        if (fileName.equals("read1"))  {
                            is = getResources().openRawResource(R.raw.kaz_hist_topic1_ru);
                        } else if (fileName.equals("read2")) {
                            is = getResources().openRawResource(R.raw.kaz_hist_topic2_ru);
                        } else if (fileName.equals("read3")) {
                            is = getResources().openRawResource(R.raw.kaz_hist_topic3_ru);
                        } else if (fileName.equals("read4")) {
                            is = getResources().openRawResource(R.raw.kaz_hist_topic4_ru);
                        } else if (fileName.equals("read5")) {
                            is = getResources().openRawResource(R.raw.kaz_hist_topic5_ru);
                        }
                    }

                    if (subjectName.equalsIgnoreCase("Английский язык") || subjectName.equalsIgnoreCase("Ағылшын тілі")){
                        if (fileName.equals("read1"))  {
                            is = getResources().openRawResource(R.raw.eng_lang_topic1);
                        } else if (fileName.equals("read2")) {
                            is = getResources().openRawResource(R.raw.eng_lang_topic2);
                        } else if (fileName.equals("read3")) {
                            is = getResources().openRawResource(R.raw.eng_lang_topic3);
                        } else if (fileName.equals("read4")) {
                            is = getResources().openRawResource(R.raw.eng_lang_topic4);
                        } else if (fileName.equals("read5")) {
                            is = getResources().openRawResource(R.raw.eng_lang_topic5);
                        }
                    }

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

    public void loadTopics() {
        if (subjectName.equalsIgnoreCase("Казахский язык")) {
            readTopicArray = getResources().getStringArray(R.array.read_kazakh_lang_ru);
        }
        if (subjectName.equalsIgnoreCase("Қазақ тілі")) {
            readTopicArray = getResources().getStringArray(R.array.read_kazakh_lang_kz);
        }
        if (subjectName.equalsIgnoreCase("Орыс тілі") || subjectName.equalsIgnoreCase("Русский язык")) {
            readTopicArray = getResources().getStringArray(R.array.read_russian_lang);
        }
        if (subjectName.equalsIgnoreCase("Қазақстан тарихы")) {
            readTopicArray = getResources().getStringArray(R.array.read_kazakh_hist_kz);
        }
        if (subjectName.equalsIgnoreCase("История Казахстана")) {
            readTopicArray = getResources().getStringArray(R.array.read_kazakh_hist_ru);
        }
        if (subjectName.equalsIgnoreCase("Английский язык") || subjectName.equalsIgnoreCase("Ағылшын тілі")) {
            readTopicArray = getResources().getStringArray(R.array.read_eng_lang);
        }
    }

}