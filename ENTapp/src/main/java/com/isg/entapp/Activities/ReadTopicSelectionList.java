package com.isg.entapp.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.isg.entapp.R;

/**
 * Created by 1 on 7/24/13.
 */
public class ReadTopicSelectionList extends Activity {
    String [] read_topics ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.read_topic);

        final ListView listview = (ListView)findViewById(R.id.listview3);
        this.loadTopics();
}
    public void loadTopics(){
        read_topics = new String[11];

        for(int i=0; i<11; i++){
             read_topics[i] = new String();

        }

        read_topics[0]=("Math");
        read_topics[1]=("Kaz history");
        read_topics[2]=("Kaz language");
        read_topics[3]=("Rus language");
        read_topics[4]=("Eng language");
        read_topics[5]=("Physics");
        read_topics[6]=("Chemistry");
        read_topics[7]=("Biology");
        read_topics[8]=("Geography");
        read_topics[9]=("World history");
        read_topics[10]=("Kaz literature");
    }
}