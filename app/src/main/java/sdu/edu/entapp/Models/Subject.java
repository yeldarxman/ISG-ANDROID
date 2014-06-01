package sdu.edu.entapp.Models;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import sdu.edu.entapp.Utilities.DoubleRounder;

/**
 * Created by yeldar on 13.07.13.
 */

@DatabaseTable(tableName = "subjects")
public class Subject {
    private static final String TOPIC_ID_FIELD = "topic_id";

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField
    private String name;

    @DatabaseField
    private String language;

    @ForeignCollectionField(eager = false)
    private ForeignCollection<Topic> topics;

    @DatabaseField(foreign = true, columnName = TOPIC_ID_FIELD)
    private sdu.edu.entapp.Models.Topic variant;

    /**
     * Calculates the progress for the given subject
     * @return
     */
    public double getProgress(){
        double result = 0;
        int totalTests = 0;

        if(topics != null){
            Topic[] topics1 = new Topic[1];
            topics1 = (Topic[])topics.toArray(topics1);

            for(int i=0; i<topics1.length; i++){
                totalTests = totalTests + topics1[i].getTests().size();
            }

            for (int i=0; i<topics1.length; i++){
                Test[] tests = new Test[1];
                tests =  topics1[i].getTests().toArray(tests);

                for(int j=0; j<tests.length; j++){
                    result = result +  (tests[j].getCorrectAnswers() * 100)/25;
                }
            }

            if(variant != null){
                Test[] tests = new Test[1];
                totalTests = totalTests + variant.getTests().size();
                tests =  (Test[]) variant.getTests().toArray(tests);
                for(int i=0; i<tests.length; i++){
                    result = result + (tests[i].getCorrectAnswers() * 100)/25;
                }
            }

            result = totalTests > 0 ? result/totalTests : 0;

            return DoubleRounder.round(result, 1);
        }

        return 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ForeignCollection<Topic> getTopics() {
        return topics;
    }

    public Topic getVariant() {
        return variant;
    }

    public void setVariant(Topic variant) {
        this.variant = variant;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
