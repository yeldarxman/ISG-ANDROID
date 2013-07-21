package com.isg.entapp.Utilities;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.isg.entapp.Models.Subject;
import com.isg.entapp.Models.Test;
import com.isg.entapp.Models.Topic;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.List;

public class Database extends OrmLiteSqliteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "entapp.db";
    private static final int DATABASE_VERSION = 1;

    // Subject DAO object
    private Dao<Subject, String> subjectDAO = null;
    private RuntimeExceptionDao<Subject, String> subjectRuntimeDAO = null;
    // Topic DAO object
    private Dao<Topic, String> topicDAO = null;
    private RuntimeExceptionDao<Topic, String> topicRuntimeDAO = null;
    // Test DAO object
    private Dao<Test, String> testDAO = null;
    private RuntimeExceptionDao<Test, String> testRuntimeDAO = null;


    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    public Dao<Subject, String> subjectDAO() throws SQLException {
        if (subjectDAO == null) {
            subjectDAO = getDao(Subject.class);
        }
        return subjectDAO;
    }

    public Dao<Topic, String> topicDAO() throws SQLException {
        if (topicDAO == null) {
            topicDAO = getDao(Topic.class);
        }
        return topicDAO;
    }

    public Dao<Test, String> testDAO() throws SQLException {
        if (testDAO == null) {
            testDAO = getDao(Test.class);
        }
        return testDAO;
    }

    public RuntimeExceptionDao<Subject, String> subjectRuntimeDAO() {
        if (subjectRuntimeDAO == null) {
            subjectRuntimeDAO = getRuntimeExceptionDao(Subject.class);
        }
        return subjectRuntimeDAO;
    }

    public RuntimeExceptionDao<Topic, String> topicRuntimeDAO() {
        if (topicRuntimeDAO == null) {
            topicRuntimeDAO = getRuntimeExceptionDao(Topic.class);
        }
        return topicRuntimeDAO;
    }

    public RuntimeExceptionDao<Test, String> testRuntimeDAO() {
        if (testRuntimeDAO == null) {
            testRuntimeDAO = getRuntimeExceptionDao(Test.class);
        }
        return testRuntimeDAO;
    }

// -------------    SUBJECT RELATED STUFF --------------- \\
    /**
     * Returns all subject objects stored in the database
     * @return
     */
    public List<Subject> getAllSubjects()
    {
        RuntimeExceptionDao<Subject, String> simpleDao = subjectRuntimeDAO();
        List<Subject> list = simpleDao.queryForAll();
        return list;
    }

    /**
     * Insets new subject object into the database
     * @param subject
     * @return
     */
    public int addSubject(Subject subject)
    {
        RuntimeExceptionDao<Subject, String> dao = subjectRuntimeDAO();
        int i = dao.create(subject);
        return i;
    }

    /**
     * Deletes all subject objects from the database
     */
    public void deleteAllSubjects()
    {
        RuntimeExceptionDao<Subject, String> dao = subjectRuntimeDAO();
        List<Subject> list = dao.queryForAll();
        dao.delete(list);
    }

// -------------    TOPIC RELATED STUFF --------------- \\
    /**
     * Returns all topic objects stored in the database
     * @return
     */
    public List<Topic> getAllTopics()
    {
        RuntimeExceptionDao<Topic, String> simpleDao = topicRuntimeDAO();
        List<Topic> list = simpleDao.queryForAll();
        return list;
    }

    /**
     * Insets new topic object into the database
     * @param topic
     * @return
     */
    public int addTopic(Topic topic)
    {
        RuntimeExceptionDao<Topic, String> dao = topicRuntimeDAO();
        int i = dao.create(topic);
        return i;
    }

    /**
     * Deletes all topic objects from the database
     */
    public void deleteAllTopics()
    {
        RuntimeExceptionDao<Topic, String> dao = topicRuntimeDAO();
        List<Topic> list = dao.queryForAll();
        dao.delete(list);
    }

// -------------    TEST RELATED STUFF --------------- \\
    /**
     * Returns all test objects stored in the database
     * @return
     */
    public List<Test> getAllTests()
    {
        RuntimeExceptionDao<Test, String> simpleDao = testRuntimeDAO();
        List<Test> list = simpleDao.queryForAll();
        return list;
    }

    /**
     * Insets new test object into the database
     * @param test
     * @return
     */
    public int addTest(Test test)
    {
        RuntimeExceptionDao<Test, String> dao = testRuntimeDAO();
        int i = dao.create(test);
        return i;
    }

    /**
     * Deletes all test objects from the database
     */
    public void deleteAllTests()
    {
        RuntimeExceptionDao<Test, String> dao = testRuntimeDAO();
        List<Test> list = dao.queryForAll();
        dao.delete(list);
    }


    @Override
    public void close() {
        super.close();
        subjectRuntimeDAO = null;
        topicRuntimeDAO = null;
        testRuntimeDAO = null;
    }

    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        try {
            Log.i(Database.class.getName(), "onCreate");
            TableUtils.createTable(connectionSource, Subject.class);
            TableUtils.createTable(connectionSource, Topic.class);
            TableUtils.createTable(connectionSource, Test.class);
        } catch (SQLException e) {
            Log.e(Database.class.getName(), "Can't create database", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            Log.i(Database.class.getName(), "onUpgrade");
            TableUtils.dropTable(connectionSource, Subject.class, true);
            TableUtils.dropTable(connectionSource, Topic.class, true);
            TableUtils.dropTable(connectionSource, Test.class, true);
            onCreate(db, connectionSource);
        } catch (SQLException e) {
            Log.e(Database.class.getName(), "Can't drop databases", e);
            throw new RuntimeException(e);
        }
    }
}
