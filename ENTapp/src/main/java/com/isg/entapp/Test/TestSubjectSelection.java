package com.isg.entapp.Test;

/**
 * Created by yeldar on 13.07.13.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.isg.entapp.Models.Subject;
import com.isg.entapp.Models.Test;
import com.isg.entapp.Models.Topic;
import com.isg.entapp.R;
import com.isg.entapp.Utilities.Database;

public class TestSubjectSelection extends Fragment {
    private Database database;
    private Subject[] subjects;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final ListView listview = (ListView) getActivity().findViewById(R.id.listview);

        //load the subject objects
        this.loadSubjects();

        //final StableArrayAdapter adapter = new StableArrayAdapter(getActivity(), R.layout.test_subject_list_item, list);
        final TestSubjectSelectionListAdapter adapter = new TestSubjectSelectionListAdapter(getActivity(), subjects);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {
            }

        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.test_subject_selection_fragment, container, false);
    }

    public void loadSubjects(){
        //initialize the database object
        database = new Database(getActivity());

        // if there are no subjects in the database then create them
        if(database.getAllSubjects().size() == 0){

            System.out.println("Creating subjects from scratch");

            subjects = new Subject[11];

            for(int i=0; i<11; i++){
                subjects[i] = new Subject();
                database.addSubject(subjects[i]);
            }

            subjects[0].setName("Math");
            subjects[1].setName("Kaz history");
            subjects[2].setName("Kaz language");
            subjects[3].setName("Rus language");
            subjects[4].setName("Eng language");
            subjects[5].setName("Physics");
            subjects[6].setName("Chemistry");
            subjects[7].setName("Biology");
            subjects[8].setName("Geography");
            subjects[9].setName("World history");
            subjects[10].setName("Kaz literature");


            for(int i=0; i<11; i++){
                Topic variant = new Topic(subjects[i], true, "Variants");
                database.addTopic(variant);

                for(int k=0; k<15; k++){
                    database.addTest(new Test("Test" + (k+1), variant));
                }

                for (int j=0; j<5; j++){
                    Topic topic = new Topic(subjects[i], false, "Topic" + (j+1));
                    database.addTopic(topic);

                    for(int k=0; k<15; k++){
                        database.addTest(new Test("Test" + (k+1), topic));
                    }
                }
            }

            for(int i=0; i<11; i++){
                database.subjectRuntimeDAO().update(subjects[i]);
            }

        } else {
            System.out.println("Loading subjects from database");
            subjects = new Subject[11];
            subjects = database.getAllSubjects().toArray(subjects);
        }
    }
}