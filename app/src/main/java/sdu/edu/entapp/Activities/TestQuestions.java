package sdu.edu.entapp.Activities;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.transition.Visibility;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

import sdu.edu.entapp.R;

import static sdu.edu.entapp.R.drawable.shape;

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
    int x,y;
    public ArrayList<Question> testQuestion;
    public Animation vanish;
    public Animation hidebutton;
    public boolean check;
    public String useranswer;
    public String trueanswer;
    public int correctAnswerIndex;
    public ArrayList<Button> variants;
    public int gotonext;
    public int questionCounter;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_question);
        //final Animation animTranslate = AnimationUtils.loadAnimation(this, R.anim.anim_translate);
        mainquestion = (TextView) findViewById(R.id.question);
        vanish = AnimationUtils.loadAnimation(TestQuestions.this,R.anim.animbutton);
        hidebutton = AnimationUtils.loadAnimation(TestQuestions.this, R.anim.hide_button);
       // question_layout = (LinearLayout) findViewById(R.id.question_layout);
        check = false;
        variants = new ArrayList<Button>();
        testQuestion = Question.createQuestions();
        gotonext = 0;
        questionCounter = 0;

        buttonA = (Button) findViewById(R.id.buttonA);
        buttonB = (Button) findViewById(R.id.buttonB);
        buttonC = (Button) findViewById(R.id.buttonC);
        buttonD = (Button) findViewById(R.id.buttonD);

        buttonA.setOnClickListener(this);
        buttonB.setOnClickListener(this);
        buttonC.setOnClickListener(this);
        buttonD.setOnClickListener(this);
        setTitle(getIntent().getExtras().getString("test"));

        setNewQuestion(testQuestion.get(0));
    }


    @SuppressLint("NewApi")
    @Override
    public void onClick(View view) {
        Button b = (Button)view;
        useranswer = b.getText().toString();

        for(int i=0; i<variants.size(); i++) {
            Button bb = variants.get(i);

            if (bb.getText().toString().equalsIgnoreCase(trueanswer)) {
                bb.setBackground(getResources().getDrawable(R.drawable.truebutton));

                //means the user got it right
                if(bb.equals(b)){
                    bb.animate().setDuration(1000);
                    bb.animate().translationYBy(buttonA.getTop() - bb.getTop() + 20).start();
                }
            } else {
                //means the user got it wrong
                if(bb.equals(b)){
                    bb.setBackground(getResources().getDrawable(R.drawable.wrongbutton));
                    variants.get(correctAnswerIndex).setBackground(getResources().getDrawable(R.drawable.truebutton));

                    if(correctAnswerIndex < i) {
                        variants.get(correctAnswerIndex).animate().setDuration(1000);
                        variants.get(correctAnswerIndex).animate().translationYBy(buttonA.getTop() - variants.get(correctAnswerIndex).getTop() + 20).start();
                        bb.animate().setDuration(1000);
                        bb.animate().translationYBy(buttonB.getTop() - bb.getTop()).start();
                    } else {
                        bb.animate().setDuration(1000);
                        bb.animate().translationYBy(buttonA.getTop() - bb.getTop() + 20).start();
                        variants.get(correctAnswerIndex).animate().setDuration(1000);
                        variants.get(correctAnswerIndex).animate().translationYBy(buttonB.getTop() - variants.get(correctAnswerIndex).getTop()).start();
                    }

                } else {
                    //other buttons that should be hidden
                    if(correctAnswerIndex != i){
                        variants.get(i).animate().setDuration(700);
                        variants.get(i).animate().alpha(0).start();
                    }
                }
            }
        }



        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {

                setNewQuestion(testQuestion.get(questionCounter++));
            }
        }, 3000);


    }



    public void setNewQuestion(final Question question) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
//                setNewQuestion(testQuestion.get(questionCounter++));
                variants = new ArrayList<Button>();

                String testquestion = String.valueOf(question.testquestion);
                mainquestion.setText(testquestion);
                // variants.add(testquestion);

                mainquestion.setVisibility(View.VISIBLE);
                testquestion = String.valueOf(question.VariantA);
                buttonA.setText(testquestion);
                variants.add(buttonA);
                buttonA.setY(mainquestion.getHeight() + 20);
                buttonA.setAlpha(1);
                buttonA.setBackgroundResource(R.drawable.shape);

                buttonA.setVisibility(View.VISIBLE);
                testquestion = String.valueOf(question.VariantB);

                buttonB.setText(testquestion);
                variants.add(buttonB);
                buttonB.setY(buttonA.getY() + buttonA.getHeight() + 10);
                buttonB.setAlpha(1);
                buttonB.setBackgroundResource(R.drawable.shape);

                testquestion = String.valueOf(question.VariantC);
                buttonC.setText(testquestion);
                variants.add(buttonC);
                buttonC.setY(buttonB.getY() + buttonC.getHeight() + 10);
                buttonC.setAlpha(1);
                buttonC.setBackgroundResource(R.drawable.shape);

                testquestion = String.valueOf(question.VariantD);

                buttonD.setText(testquestion);
                variants.add(buttonD);
                buttonD.setY(buttonC.getY() + buttonC.getHeight() + 10);
                buttonD.setAlpha(1);
                buttonD.setBackgroundResource(R.drawable.shape);

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

}