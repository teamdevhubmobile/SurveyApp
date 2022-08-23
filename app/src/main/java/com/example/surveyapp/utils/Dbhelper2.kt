package com.example.surveyapp.utils

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class Dbhelper2 (context: Context, nothing: Nothing?): SQLiteOpenHelper(context, DATABASE_NAME2 ,null,1) {


    override fun onCreate(db: SQLiteDatabase?) {
       /* val query = ("CREATE TABLE " + TABLE_NAME + " ("
                + ID + " INTEGER PRIMARY KEY, " +
                SURVEYID + " INTEGER," +
                SURVEYNAME + " VARCHAR," +
                UPLOADID + " INTEGER" + ")")

        // we are calling sqlite
        // method for executing our query
        db?.execSQL(query)*/
        val query2 = ("CREATE TABLE " + TABLE_NAME2 + " ("
                + ID + " INTEGER PRIMARY KEY, " +
                QUESTIONID + " INTEGER," +
                QUESTION + " VARCHAR," +
                OPTION + " VARCHAR," +
                ANSWER + " VARCHAR," +
                STATUS + " VARCHAR," +
                ATTEMPT + " BOOLEAN" + ")")

        // we are calling sqlite
        // method for executing our query
        db?.execSQL(query2)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        // this method is to check if table already exists
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME2)
        onCreate(db)
    }

    fun addQuestion(questionId : String,question : String, option : String){

        val cv = ContentValues()

        cv.put(QUESTIONID,questionId)
        cv.put(QUESTION,question)
        cv.put(OPTION, option)
        //cv.put(ANSWER, answer)

        val db = this.writableDatabase

        db.insert(TABLE_NAME2, null, cv)

        db.close()
    }


    fun getQuestion(): Cursor? {

        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM " + TABLE_NAME2, null)

    }

    companion object{

        private val DATABASE_NAME = "SurveyTableData"
        private val DATABASE_NAME2 = "QuestionTableData"

        private val DATABASE_VERSION = 1

        // below is the variable for table name
        val TABLE_NAME = "SurveyTable"
        val TABLE_NAME2 = "QuestionTable"

        // below is the variable for id column
        val ID = "id"

        // below is the variable for name column
        val SURVEYID = "surveyID"
        val QUESTION = "question"
        val QUESTIONID = "questionID"
        val OPTION = "option"
        val ANSWER = "answer"
        val STATUS = "status"
        val ATTEMPT = "attempt"

        val SURVEYNAME  = "SurveyName"
        val UPLOADID = "UploadId"
    }

}