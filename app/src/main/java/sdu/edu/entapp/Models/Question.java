package sdu.edu.entapp.Models;

/**
 * Created by Ерболат on 22.05.2014.
 */


        import android.app.Activity;
        import android.content.res.AssetManager;

        import java.io.IOException;
        import java.io.InputStream;
        import java.util.ArrayList;
        import java.util.Scanner;

public class Question  {

    public String VariantA = "";
    public String VariantB = "";
    public String VariantC = "";
    public String VariantD = "";

    public String testquestion;
    public String trueanswer;
    public boolean answered;
    public boolean isCorrect;
    public String selectedAnswer;

    public static ArrayList<Question> createQuestions(Activity context, String subjectTitle , String testTitle){
        ArrayList<Question> questions = new ArrayList<Question>();
        AssetManager txtFile = context.getAssets();
        String neededTxt = subjectTitle + "_" + testTitle + ".Txt";
        Question q1 = null;
        try {
            InputStream is = txtFile.open(neededTxt);
            Scanner sc = new Scanner(is);
            int lineCounter = 0;

            while(sc.hasNextLine()){
                String text = sc.nextLine();
                if(lineCounter==0){
                    q1 = new Question();
                    q1.testquestion = text;
                } else if(lineCounter==1){
                    q1.VariantA = text;
                } else if(lineCounter==2){
                    q1.VariantB = text;
                } else if(lineCounter==3){
                    q1.VariantC = text;
                } else if(lineCounter==4){
                    q1.VariantD = text;
                } else if(lineCounter==5){
                    q1.trueanswer = text;
                } else if(lineCounter==6){
                    lineCounter=-1;
                    questions.add(q1);
                    System.out.println(questions.size());
                }
                System.out.println(text);
                lineCounter++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return questions;
    }
}