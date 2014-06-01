package sdu.edu.entapp.Fragments.Test;

/**
 * Created by yerbolat on 10.03.14.
 */

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import sdu.edu.entapp.Activities.ReadTopicSelectionActivity;
import sdu.edu.entapp.Activities.TestTopicSelectionActivity;



import sdu.edu.entapp.ListAdapters.Test.TestSubjectSelectionListAdapter;
import sdu.edu.entapp.Models.Subject;
import sdu.edu.entapp.Models.Topic;
import sdu.edu.entapp.Models.Test;
import sdu.edu.entapp.R;
import sdu.edu.entapp.Utilities.Database;

public class TestSubjectSelection extends Fragment {
    private Database database;
    private Subject[] subjects;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final ListView listview = (ListView) getActivity().findViewById(R.id.listview2);

        //load the subject objects
        this.loadSubjects();

        //final StableArrayAdapter adapter = new StableArrayAdapter(getActivity(), R.layout.test_subject_list_item, list);
        final TestSubjectSelectionListAdapter adapter = new TestSubjectSelectionListAdapter(getActivity(), subjects);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {
                if (position < 11){
                    final Intent testTopicSelectionActivity = new Intent(getActivity().getApplicationContext(), TestTopicSelectionActivity.class);
                    testTopicSelectionActivity.putExtra("test", subjects[position].getName());
                    testTopicSelectionActivity.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(testTopicSelectionActivity);
                }


            }

        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.read_list_fragment, container, false);
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


            subjects[0].setName("Қазақ тілі");
            subjects[1].setName("Қазақстан тарихы");
            subjects[2].setName("Орыс тілі");
            subjects[3].setName("Ағылшын тілі");
            subjects[4].setName("Биология");
            subjects[5].setName("Физика");
            subjects[6].setName("Математика");
            subjects[7].setName("География");
            subjects[8].setName("Всемирная история");
            subjects[9].setName("Қазақ әдебиеті");
            subjects[10].setName("Химия");


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