package sdu.edu.entapp.Activities;

/**
 * Created by Ерболат on 22.05.2014.
 */


        import java.util.ArrayList;
        import java.util.List;

public class Question {

    public String VariantA = "";
    public String VariantB = "";
    public String VariantC = "";
    public String VariantD = "";

    public String testquestion;
    public String trueanswer;

    public static ArrayList<Question> createQuestions(){
        ArrayList<Question> questions = new ArrayList<Question>();


        Question q1 = new Question();
        q1.testquestion = "Ашық буынды сөзді табыңыз:";
        q1.VariantA = "Тақтай";
        q1.VariantB = "Әшекей";
        q1.VariantC = "Ана";
        q1.VariantD = "Қорамсақ";
        q1.trueanswer = q1.VariantC;

        Question q2 = new Question();
        q2.testquestion = "Үнді дыбыстарды табыңыз:";
        q2.VariantA = "ғ, д, ж, з ";
        q2.VariantB = "р, л, м, н";
        q2.VariantC = "п, к, қ, т";
        q2.VariantD = "х, ц, ч, ш";
        q2.trueanswer = q2.VariantB;



        Question q3 = new Question();
        q3.testquestion = "Тек бітеу буын сөздерді көрсетіңіз:";
        q3.VariantA = "Сұлу, жұқа, кіші";
        q3.VariantB = "Мереке, үлгі, аула";
        q3.VariantC = "Дәптер, мектеп, бұлбұл";
        q3.VariantD = "Оқушы, бала, оқытушы";
        q3.trueanswer = q3.VariantC;



        questions.add(q1);
        questions.add(q2);
        questions.add(q3);

        return questions;
    }
}
