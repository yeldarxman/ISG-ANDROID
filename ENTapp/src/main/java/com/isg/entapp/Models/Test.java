package com.isg.entapp.Models;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by yeldar on 13.07.13.
 */
public class Test {
    private static final String TOPIC_ID_COLUMN = "topic_id";

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = TOPIC_ID_COLUMN)
    private Topic topic;

    @DatabaseField
    private String name;

    @DatabaseField
    private int correctAnswers;

    @DatabaseField
    private int incorrectAnswers;

    public Test(){

    }

    public Test(String name, Topic topic) {
        this.topic = topic;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(int correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    public int getIncorrectAnswers() {
        return incorrectAnswers;
    }

    public void setIncorrectAnswers(int incorrectAnswers) {
        this.incorrectAnswers = incorrectAnswers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }
}
