package sdu.edu.entapp.Activities;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import sdu.edu.entapp.ListAdapters.Test.TestTopicSelectionListAdapter;
import sdu.edu.entapp.R;


public class TestTopicSelectionActivity extends Activity {
    String [] testTopicArray ;
    public TestTopicSelectionListAdapter adapter;
    private ListView listview;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_topics_list);
        listview = (ListView) findViewById(R.id.listview3);
        this.loadTopics();

        adapter = new TestTopicSelectionListAdapter(this, testTopicArray);
        getActionBar().setCustomView(R.layout.read_topic_title_custom_view);

        TextView title = (TextView) getActionBar().getCustomView().findViewById(R.id.titleview);
        title.setText(getIntent().getExtras().getString("test"));
        getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM | ActionBar.DISPLAY_SHOW_HOME);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        listview.setAdapter(adapter);

        //hide app icon
        getActionBar().setDisplayUseLogoEnabled(false);

        listview.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent testquestion = new Intent(getApplicationContext(),TestQuestions.class);
                        testquestion.putExtra("test", testTopicArray[i]);
                        startActivity(testquestion);

                    }
                }
        );
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpTo(this, getIntent());
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void loadTopics(){
        testTopicArray = getResources().getStringArray(R.array.test_topics_array);
    }
}