package sdu.edu.entapp.Activities;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import sdu.edu.entapp.ListAdapters.Test.TestTopicSelectionListAdapter;
import sdu.edu.entapp.Models.Subject;
import sdu.edu.entapp.Models.Test;
import sdu.edu.entapp.Models.Topic;
import sdu.edu.entapp.R;
import sdu.edu.entapp.Utilities.Constants;


public class TestTopicSelectionActivity extends Activity {
    String [] testTopicArray ;
    Test[] realTopics;
    int[] topicCorrectAnswersCounts;
    public TestTopicSelectionListAdapter adapter;
    private ListView listview;
    public String titleString;
    public String englishSubjectName;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_topics_list);

        titleString = getIntent().getExtras().getString("test");
        englishSubjectName = getIntent().getExtras().getString("englishSubjectName");

        listview = (ListView) findViewById(R.id.listview3);
        this.loadTopics();
        adapter = new TestTopicSelectionListAdapter(this, testTopicArray);
        adapter.correctAnswersCounts = topicCorrectAnswersCounts;
        listview.setAdapter(adapter);

        getActionBar().setCustomView(R.layout.read_topic_title_custom_view);

        TextView title = (TextView) getActionBar().getCustomView().findViewById(R.id.titleview);
        title.setText(getIntent().getExtras().getString("test"));

        getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM | ActionBar.DISPLAY_SHOW_HOME);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        //hide app icon
        getActionBar().setDisplayUseLogoEnabled(false);

        listview.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent testquestion = new Intent(getApplicationContext(),TestQuestions.class);
                        testquestion.putExtra("test", testTopicArray[i]);
                        testquestion.putExtra("subject", titleString);
                        testquestion.putExtra("englishSubjectName", englishSubjectName);
                        testquestion.putExtra("englishTopicName", realTopics[i].englishName);
                        startActivity(testquestion);

                    }
                }
        );
    }

    @Override
    public void onResume(){
        super.onResume();
        if(listview != null) {
            this.loadTopics();
            adapter = new TestTopicSelectionListAdapter(this, testTopicArray);
            adapter.correctAnswersCounts = topicCorrectAnswersCounts;
            listview.setAdapter(adapter);
        }
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
        realTopics = new Test[testTopicArray.length];
        topicCorrectAnswersCounts = new int[realTopics.length];

        int counter = 0;
        for(String temp: testTopicArray){
            if(temp.isEmpty()){
                break;
            }
            System.out.println(temp);
            Test topic = new Test();
            String names[] = temp.split(",");
            topic.name = names[0];
            topic.englishName = names[1];
            realTopics[counter++] = topic;
        }

        counter = 0;
        for(Test topic: realTopics){
            if(topic != null){
                testTopicArray[counter++] = topic.name;
            }
        }


        //SharedPreferences
        SharedPreferences settings = getApplicationContext().getSharedPreferences(Constants.PREFS_NAME, 0);
        //read from shared preferences
        counter = 0;
        for(Test topic: realTopics){
            if(topic != null){
                System.out.println("READING: " + titleString + topic.name);
                topicCorrectAnswersCounts[counter++] = settings.getInt(titleString + topic.name, 0);
            }
        }
    }
}