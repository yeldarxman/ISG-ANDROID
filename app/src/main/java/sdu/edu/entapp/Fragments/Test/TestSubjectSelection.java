package sdu.edu.entapp.Fragments.Test;

/**
 * Created by yerbolat on 10.03.14.
 */

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import sdu.edu.entapp.Activities.MainTabActivity;
import sdu.edu.entapp.Activities.ReadTopicSelectionActivity;
import sdu.edu.entapp.Activities.TestTopicSelectionActivity;
import sdu.edu.entapp.ListAdapters.Test.TestSubjectSelectionListAdapter;
import sdu.edu.entapp.Models.Subject;
import sdu.edu.entapp.Models.Topic;
import sdu.edu.entapp.Models.Test;
import sdu.edu.entapp.R;
import sdu.edu.entapp.Utilities.Constants;
import sdu.edu.entapp.Utilities.Database;

public class TestSubjectSelection extends Fragment {
    private String[] subjects;
    private Subject[] realSubjects;
    private double subjectProgress[];
    private TestSubjectSelectionListAdapter adapter;
    private ListView listview;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listview = (ListView) getActivity().findViewById(R.id.listview2);
        //load the subject objects
        this.loadSubjects();

        //final StableArrayAdapter adapter = new StableArrayAdapter(getActivity(), R.layout.test_subject_list_item, list);
        adapter = new TestSubjectSelectionListAdapter(getActivity(), subjects);
        adapter.subjectProgresses = subjectProgress;
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {
                if (position < 11) {
                    final Intent testTopicSelectionActivity = new Intent(getActivity().getApplicationContext(), TestTopicSelectionActivity.class);
                    testTopicSelectionActivity.putExtra("test", subjects[position]);
                    testTopicSelectionActivity.putExtra("englishSubjectName", realSubjects[position].englishName);
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

    @Override
    public void onResume() {
        super.onResume();
        if(listview != null) {
            //load the subject objects
            this.loadSubjects();
            //final StableArrayAdapter adapter = new StableArrayAdapter(getActivity(), R.layout.test_subject_list_item, list);
            adapter = new TestSubjectSelectionListAdapter(getActivity(), subjects);
            adapter.subjectProgresses = subjectProgress;
            listview.setAdapter(adapter);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        this.loadSubjects();
    }

    public void loadSubjects() {
        subjects = getResources().getStringArray((R.array.test_subjects_array));
        realSubjects = new Subject[subjects.length];
        subjectProgress = new double[realSubjects.length];

        int counter = 0;
        for(String temp: subjects){
            if(temp.isEmpty()){
                break;
            }
            Subject s = new Subject();
            String names[] = temp.split(",");
            s.name = names[0];
            s.englishName = names[1];
            realSubjects[counter++] = s;
        }

        counter = 0;
        for(Subject subject: realSubjects){
            subjects[counter++] = subject.name;
        }

        //SharedPreferences
        SharedPreferences settings = getActivity().getSharedPreferences(Constants.PREFS_NAME, 0);
        //read from shared preferences
        counter = 0;
        for(Subject subject: realSubjects){
            if(subject != null){
                System.out.println("READING: " + subject.name);
                String progress = settings.getString(subject.name, "0");
                double doubleProgress = Double.parseDouble(progress);
                subjectProgress[counter++] = doubleProgress;
            }
        }
    }
}