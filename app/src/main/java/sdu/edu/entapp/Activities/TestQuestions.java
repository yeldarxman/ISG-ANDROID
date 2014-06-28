package sdu.edu.entapp.Activities;

import android.annotation.SuppressLint;
import android.app.Activity;

import android.app.AlertDialog;
import android.app.Application;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import sdu.edu.entapp.Models.Question;
import sdu.edu.entapp.R;
import sdu.edu.entapp.Utilities.Constants;

/**
 * Created by Ерболат on 21.05.2014.
 */
public class TestQuestions extends Activity implements View.OnClickListener {
    public String [][] database;
    public TextView mainquestion;
    public Button buttonA;
    public Button buttonB;
    public Button buttonC;
    public Button buttonD;
    public Button button1;
    public Button button2;
    public Button button3;
    public Button button4;
    public Button button5;
    public Button button6;
    public Button button7;
    public Button button8;
    public Button button9;
    public Button button10;
    public Button button11;
    public Button button12;
    public Button button13;
    public Button button14;
    public Button button15;
    public Button button16;
    public Button button17;
    public Button button18;
    public Button button19;
    public Button button20;
    public Button button21;
    public Button button22;
    public Button button23;
    public Button button24;
    public Button button25;

    int x,y;
    public ArrayList<Question> testQuestions;
    public Animation vanish;
    public Animation hidebutton;
    public boolean check;
    public String useranswer;
    public String trueanswer;
    public int correctAnswerIndex;
    public ArrayList<Button> variants;
    public int gotonext;
    public int questionCounter;
    public ArrayList<Button> questionNumberButtons;
    public String testTitle;
    public String subjectTitle;
    public String englishSubjectName;
    public String englishTopicName;
    public int counterCorrectAnswer;



    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_question);
        getActionBar().setCustomView(R.layout.read_topic_title_custom_view);
        TextView title = (TextView) getActionBar().getCustomView().findViewById(R.id.titleview);
        title.setText(getIntent().getExtras().getString("test"));
        testTitle = getIntent().getExtras().getString("test");
        subjectTitle = getIntent().getExtras().getString("subject");

        englishSubjectName = getIntent().getExtras().getString("englishSubjectName");
        englishTopicName = getIntent().getExtras().getString("englishTopicName");

        //final Animation animTranslate = AnimationUtils.loadAnimation(this, R.anim.anim_translate);
        mainquestion = (TextView) findViewById(R.id.question);
        vanish = AnimationUtils.loadAnimation(TestQuestions.this,R.anim.animbutton);
        hidebutton = AnimationUtils.loadAnimation(TestQuestions.this, R.anim.hide_button);
       // question_layout = (LinearLayout) findViewById(R.id.question_layout);
        check = false;
        variants = new ArrayList<Button>();
        testQuestions = Question.createQuestions(this, englishSubjectName, englishTopicName);
        gotonext = 0;
        questionCounter = 0;
        questionNumberButtons = new ArrayList<Button>();
        counterCorrectAnswer = 0;

        buttonA = (Button) findViewById(R.id.buttonA);
        buttonB = (Button) findViewById(R.id.buttonB);
        buttonC = (Button) findViewById(R.id.buttonC);
        buttonD = (Button) findViewById(R.id.buttonD);

        button1 = (Button) findViewById(R.id.button1);
        button1.setTag(0);
        button1.setEnabled(false);
        button2 = (Button) findViewById(R.id.button2);
        button2.setTag(1);
        button2.setEnabled(false);
        button3 = (Button) findViewById(R.id.button3);
        button3.setTag(2);
        button3.setEnabled(false);
        button4 = (Button) findViewById(R.id.button4);
        button4.setTag(3);
        button4.setEnabled(false);
        button5 = (Button) findViewById(R.id.button5);
        button5.setTag(4);
        button5.setEnabled(false);
        button6 = (Button) findViewById(R.id.button6);
        button6.setTag(5);
        button6.setEnabled(false);
        button7 = (Button) findViewById(R.id.button7);
        button7.setTag(6);
        button7.setEnabled(false);
        button8 = (Button) findViewById(R.id.button8);
        button8.setTag(7);
        button8.setEnabled(false);
        button9 = (Button) findViewById(R.id.button9);
        button9.setTag(8);
        button9.setEnabled(false);
        button10 = (Button) findViewById(R.id.button10);
        button10.setTag(9);
        button10.setEnabled(false);
        button11 = (Button) findViewById(R.id.button11);
        button11.setTag(10);
        button11.setEnabled(false);
        button12 = (Button) findViewById(R.id.button12);
        button12.setTag(11);
        button12.setEnabled(false);
        button13 = (Button) findViewById(R.id.button13);
        button13.setTag(12);
        button13.setEnabled(false);
        button14 = (Button) findViewById(R.id.button14);
        button14.setTag(13);
        button14.setEnabled(false);
        button15 = (Button) findViewById(R.id.button15);
        button15.setTag(14);
        button15.setEnabled(false);
        button16 = (Button) findViewById(R.id.button16);
        button16.setTag(15);
        button16.setEnabled(false);
        button17 = (Button) findViewById(R.id.button17);
        button17.setTag(16);
        button17.setEnabled(false);
        button18 = (Button) findViewById(R.id.button18);
        button18.setTag(17);
        button18.setEnabled(false);
        button19 = (Button) findViewById(R.id.button19);
        button19.setTag(18);
        button19.setEnabled(false);
        button20 = (Button) findViewById(R.id.button20);
        button20.setTag(19);
        button20.setEnabled(false);
        button21 = (Button) findViewById(R.id.button21);
        button21.setTag(20);
        button21.setEnabled(false);
        button22 = (Button) findViewById(R.id.button22);
        button22.setTag(21);
        button22.setEnabled(false);
        button23 = (Button) findViewById(R.id.button23);
        button23.setTag(22);
        button23.setEnabled(false);
        button24 = (Button) findViewById(R.id.button24);
        button24.setTag(23);
        button24.setEnabled(false);
        button25 = (Button) findViewById(R.id.button25);
        button25.setTag(24);
        button25.setEnabled(false);

        buttonA.setOnClickListener(this);
        buttonB.setOnClickListener(this);
        buttonC.setOnClickListener(this);
        buttonD.setOnClickListener(this);

        ButtonListOnClickListener listener = new ButtonListOnClickListener();

        button1.setOnClickListener(listener);
        button2.setOnClickListener(listener);
        button3.setOnClickListener(listener);
        button4.setOnClickListener(listener);
        button5.setOnClickListener(listener);
        button6.setOnClickListener(listener);
        button7.setOnClickListener(listener);
        button8.setOnClickListener(listener);
        button9.setOnClickListener(listener);
        button10.setOnClickListener(listener);
        button11.setOnClickListener(listener);
        button12.setOnClickListener(listener);
        button13.setOnClickListener(listener);
        button14.setOnClickListener(listener);
        button15.setOnClickListener(listener);
        button16.setOnClickListener(listener);
        button17.setOnClickListener(listener);
        button18.setOnClickListener(listener);
        button19.setOnClickListener(listener);
        button20.setOnClickListener(listener);
        button21.setOnClickListener(listener);
        button22.setOnClickListener(listener);
        button23.setOnClickListener(listener);
        button24.setOnClickListener(listener);
        button25.setOnClickListener(listener);

        questionNumberButtons.add(button1);
        questionNumberButtons.add(button2);
        questionNumberButtons.add(button3);
        questionNumberButtons.add(button4);
        questionNumberButtons.add(button5);
        questionNumberButtons.add(button6);
        questionNumberButtons.add(button7);
        questionNumberButtons.add(button8);
        questionNumberButtons.add(button9);
        questionNumberButtons.add(button10);
        questionNumberButtons.add(button11);
        questionNumberButtons.add(button12);
        questionNumberButtons.add(button13);
        questionNumberButtons.add(button14);
        questionNumberButtons.add(button15);
        questionNumberButtons.add(button16);
        questionNumberButtons.add(button17);
        questionNumberButtons.add(button18);
        questionNumberButtons.add(button19);
        questionNumberButtons.add(button20);
        questionNumberButtons.add(button21);
        questionNumberButtons.add(button22);
        questionNumberButtons.add(button23);
        questionNumberButtons.add(button24);
        questionNumberButtons.add(button25);

        setTitle(getIntent().getExtras().getString("test"));

        setNewQuestion(testQuestions.get(0));
    }

    public class  ButtonListOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            setQuestion(testQuestions.get((Integer)v.getTag()));
        }
    }
    @SuppressLint("NewApi")
    public void onClick(View view) {
        Button b = (Button)view;
        useranswer = b.getText().toString();
        testQuestions.get(questionCounter).selectedAnswer = useranswer;
        testQuestions.get(questionCounter).answered = true;
        questionNumberButtons.get(questionCounter).setEnabled(true);


        if(useranswer.equalsIgnoreCase(testQuestions.get(questionCounter).trueanswer)){
            questionNumberButtons.get(questionCounter).setBackground(getResources().getDrawable(R.drawable.sequencetrue));
            counterCorrectAnswer++;
        } else {
            questionNumberButtons.get(questionCounter).setBackground(getResources().getDrawable(R.drawable.sequencewrong));
        }

        for(int i=0; i<variants.size(); i++) {
            Button bb = variants.get(i);
            bb.setEnabled(false);

            //means the user got it wrong
            if (bb.getText().toString().equalsIgnoreCase(trueanswer)) {
                bb.setBackground(getResources().getDrawable(R.drawable.truebutton));
            } else if (bb.equals(b)) {
                //means the user got it wrong
                bb.setBackground(getResources().getDrawable(R.drawable.wrongbutton));
                variants.get(correctAnswerIndex).setBackground(getResources().getDrawable(R.drawable.truebutton));
            } else {
                //other buttons that should be hidden
                if (correctAnswerIndex != i) {
                    variants.get(i).setVisibility(View.GONE);
                }
            }
        }

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(questionCounter < testQuestions.size()-1){
                            questionNumberButtons.get(questionCounter+1).setBackground(getResources().getDrawable(R.drawable.sequence));
                            questionNumberButtons.get(questionCounter+1).setEnabled(true);
                            final HorizontalScrollView hsv = (HorizontalScrollView) findViewById(R.id.horiscroll);
                            hsv.smoothScrollTo((int) questionNumberButtons.get(questionCounter + 1).getX(), 0);

                            setNewQuestion(testQuestions.get(++questionCounter));
                        } else {
                            //SharedPreferences
                            SharedPreferences settings = getApplicationContext().getSharedPreferences(Constants.PREFS_NAME, 0);
                            SharedPreferences.Editor editor = settings.edit();
                            System.out.println("PUTTING INT: " + subjectTitle + testTitle);
                            editor.putInt(subjectTitle + testTitle, counterCorrectAnswer);
                            // Commit the edits!
                            editor.commit();

                            //read from shared preferences
                            settings = getApplicationContext().getSharedPreferences(Constants.PREFS_NAME, 0);
                            int sum = 0;
                            for(int i=1; i<6; i++){
                                String tempString = subjectTitle + testTitle.substring(0, testTitle.length()-1) + i;
                                System.out.println(tempString);
                                sum = sum + settings.getInt(tempString,0);
                            }
                            double percentage = (sum/75.0) * 100;

                            //write subject percentage to the shared preferences
                            editor = settings.edit();
                            editor.putString(subjectTitle, percentage + "");
                            // Commit the edits!
                            editor.commit();


                            AlertDialog.Builder builder = new AlertDialog.Builder(TestQuestions.this);
                            builder.setTitle("Testovoe zadanie zakoncheno!")
                                    .setMessage("Vi nabrali!"+counterCorrectAnswer)
                                    .setCancelable(true)
                                    .setNegativeButton("ОК, back",
                                            new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int id) {
                                                    dialog.cancel();
                                                }
                                            });

                            AlertDialog alert = builder.create();
                            alert.show();

                            //Toast.makeText(getApplicationContext(), "You have finished the test! Go Back!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }, 2000);
    }



    public void setNewQuestion(final Question question) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                variants = new ArrayList<Button>();

                String testquestion = String.valueOf(question.testquestion);
                mainquestion.setText(testquestion);

                mainquestion.setVisibility(View.VISIBLE);
                testquestion = String.valueOf(question.VariantA);
                buttonA.setText(testquestion);
                variants.add(buttonA);
                buttonA.setBackgroundResource(R.drawable.shape);
                buttonA.setVisibility(View.VISIBLE);
                buttonA.setEnabled(true);

                testquestion = String.valueOf(question.VariantB);
                buttonB.setText(testquestion);
                variants.add(buttonB);
                buttonB.setBackgroundResource(R.drawable.shape);
                buttonB.setVisibility(View.VISIBLE);
                buttonB.setEnabled(true);

                testquestion = String.valueOf(question.VariantC);
                buttonC.setText(testquestion);
                variants.add(buttonC);
                buttonC.setBackgroundResource(R.drawable.shape);
                buttonC.setVisibility(View.VISIBLE);
                buttonC.setEnabled(true);

                testquestion = String.valueOf(question.VariantD);
                buttonD.setText(testquestion);
                variants.add(buttonD);
                buttonD.setBackgroundResource(R.drawable.shape);
                buttonD.setVisibility(View.VISIBLE);
                buttonD.setEnabled(true);

                trueanswer = String.valueOf(question.trueanswer);

                if(buttonA.getText().toString().equalsIgnoreCase(trueanswer)){
                    correctAnswerIndex = 0;
                } else if(buttonB.getText().toString().equalsIgnoreCase(trueanswer)){
                    correctAnswerIndex = 1;
                } else if(buttonC.getText().toString().equalsIgnoreCase(trueanswer)){
                    correctAnswerIndex = 2;
                } else {
                    correctAnswerIndex = 3;
                }
            }
        });
    }

    public void setQuestion(final Question question) {
       if(!question.answered){
           setNewQuestion(question);
           return;
       }

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                variants = new ArrayList<Button>();
                trueanswer = String.valueOf(question.trueanswer);
                String testquestion = String.valueOf(question.testquestion);
                mainquestion.setText(testquestion);
                mainquestion.setVisibility(View.VISIBLE);

                if(question.answered){
                    buttonA.setEnabled(false);
                    buttonB.setEnabled(false);
                    buttonC.setEnabled(false);
                    buttonD.setEnabled(false);
                } else {
                    buttonA.setEnabled(true);
                    buttonB.setEnabled(true);
                    buttonC.setEnabled(true);
                    buttonD.setEnabled(true);
                }

                testquestion = String.valueOf(question.VariantA);
                buttonA.setText(testquestion);
                variants.add(buttonA);

                if(testquestion.equalsIgnoreCase(question.selectedAnswer)) {
                    buttonA.setVisibility(View.VISIBLE);
                    if(testquestion.equalsIgnoreCase(question.trueanswer)){
                        buttonA.setBackgroundResource(R.drawable.truebutton);
                    } else {
                        buttonA.setBackgroundResource(R.drawable.wrongbutton);
                    }
                } else {
                    if(question.answered) {
                        if(question.trueanswer.equalsIgnoreCase(testquestion)){
                            buttonA.setBackgroundResource(R.drawable.truebutton);
                            buttonA.setVisibility(View.VISIBLE);
                        } else {
                            buttonA.setVisibility(View.GONE);
                        }
                    }  else {
                        buttonA.setBackgroundResource(R.drawable.shape);
                        buttonA.setVisibility(View.GONE);
                    }
                }

                testquestion = String.valueOf(question.VariantB);
                buttonB.setText(testquestion);
                variants.add(buttonB);

                if(testquestion.equalsIgnoreCase(question.selectedAnswer)) {
                    buttonB.setVisibility(View.VISIBLE);
                    if(testquestion.equalsIgnoreCase(question.trueanswer)){
                        buttonB.setBackgroundResource(R.drawable.truebutton);
                    } else {
                        buttonB.setBackgroundResource(R.drawable.wrongbutton);
                    }
                } else {
                    if(question.answered) {
                        if(question.trueanswer.equalsIgnoreCase(testquestion)){
                            buttonB.setBackgroundResource(R.drawable.truebutton);
                            buttonB.setVisibility(View.VISIBLE);
                        } else {
                            buttonB.setVisibility(View.GONE);
                        }
                    } else {
                        buttonB.setBackgroundResource(R.drawable.shape);
                        buttonB.setVisibility(View.GONE);
                    }
                }

                testquestion = String.valueOf(question.VariantC);
                buttonC.setText(testquestion);
                variants.add(buttonC);

                if(testquestion.equalsIgnoreCase(question.selectedAnswer)) {
                    buttonC.setVisibility(View.VISIBLE);
                    if(testquestion.equalsIgnoreCase(question.trueanswer)){
                        buttonC.setBackgroundResource(R.drawable.truebutton);
                    } else {
                        buttonC.setBackgroundResource(R.drawable.wrongbutton);

                    }

                } else {
                    if(question.answered) {
                        if(question.trueanswer.equalsIgnoreCase(testquestion)){
                            buttonC.setBackgroundResource(R.drawable.truebutton);
                            buttonC.setVisibility(View.VISIBLE);
                        } else {
                            buttonC.setVisibility(View.GONE);
                        }
                    } else {
                        buttonC.setBackgroundResource(R.drawable.shape);
                        buttonC.setVisibility(View.GONE);
                    }
                }

                testquestion = String.valueOf(question.VariantD);
                buttonD.setText(testquestion);
                variants.add(buttonD);

                if(testquestion.equalsIgnoreCase(question.selectedAnswer)) {
                    buttonD.setVisibility(View.VISIBLE);
                    if(testquestion.equalsIgnoreCase(question.trueanswer)){
                        buttonD.setBackgroundResource(R.drawable.truebutton);
                    } else {
                        buttonD.setBackgroundResource(R.drawable.wrongbutton);
                    }
                } else {
                    if(question.answered) {
                        if(question.trueanswer.equalsIgnoreCase(testquestion)){
                            buttonD.setBackgroundResource(R.drawable.truebutton);
                            buttonD.setVisibility(View.VISIBLE);
                        } else {
                            buttonD.setVisibility(View.GONE);
                        }
                    } else {
                        buttonD.setBackgroundResource(R.drawable.shape);
                        buttonD.setVisibility(View.GONE);
                    }
                }

                if(buttonA.getText().toString().equalsIgnoreCase(trueanswer)){
                    correctAnswerIndex = 0;
                } else if(buttonB.getText().toString().equalsIgnoreCase(trueanswer)){
                    correctAnswerIndex = 1;
                } else if(buttonC.getText().toString().equalsIgnoreCase(trueanswer)){
                    correctAnswerIndex = 2;
                } else {
                    correctAnswerIndex = 3;
                }
            }
        });
    }
}