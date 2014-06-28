package sdu.edu.entapp.Models;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;

/**
 * Created by yerbolat on 13.12.13.
 */
public class Topic {

    private static final String SUBJECT_ID_COLUMN = "subject_id";

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = SUBJECT_ID_COLUMN)
    private Subject subject;

    @DatabaseField
    private boolean isVariant = false;

    @DatabaseField
    private String name;

    @ForeignCollectionField
    private ForeignCollection<Test> tests;

    public Topic(){

    }

    public Topic(Subject subject, boolean variant, String name) {
        this.subject = subject;
        isVariant = variant;
        this.name = name;
    }

    public Topic(boolean variant){
        this.isVariant = variant;
    }

    public boolean isVariant() {
        return isVariant;
    }

    public void setVariant(boolean variant) {
        isVariant = variant;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ForeignCollection<Test> getTests() {
        return tests;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
