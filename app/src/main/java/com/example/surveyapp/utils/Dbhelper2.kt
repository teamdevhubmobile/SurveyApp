package com.example.surveyapp.utils

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class Dbhelper2 (context: Context, nothing: Nothing?): SQLiteOpenHelper(context, DATABASE_NAME2 ,null,1) {


    override fun onCreate(db: SQLiteDatabase?) {
           val query3 = ("CREATE TABLE " + TABLE_NAME + " ("
                 + ID + " INTEGER PRIMARY KEY, " +
                 FULLNAME + " VARCHAR," +
                 GENDER + " VARCHAR," +
                 AGE + " VARCHAR," +
                 ADDRESS + " VARCHAR," +
                   PHONE + " VARCHAR," +")")
            /*     SURVEYID + " INTEGER," +
                QUESTIONID + " INTEGER," +
                OPID + " INTEGER," +
                ANSWER + " VARCHAR," + ")")*/

        // we are calling sqlite
        // method for executing our query
        db?.execSQL(query3)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        // this method is to check if table already exists
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME2)
        onCreate(db)
    }

    fun addTakenSurvey(fullName : String,gender : String, phone : String, age : String, address : String){

        val cv = ContentValues()


        cv.put(QUESTIONID,fullName)
        cv.put(QUESTION,gender)
        cv.put(QUESTION,age)
        cv.put(OPTION, address)
        cv.put(OPTION, phone)

        //cv.put(ANSWER, answer)

        val db = this.writableDatabase

        db.insert(TABLE_NAME2, null, cv)

        Log.e("TAG1", "addTakenSurvey: $cv", )

        db.close()
    }


    fun getTakenSurvey(): Cursor? {

        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM " + TABLE_NAME2, null)

    }

    companion object{

        private val DATABASE_NAME = "SurveyTableData"
        private val DATABASE_NAME2 = "QuestionTableData"

        private val DATABASE_VERSION = 1

        // below is the variable for table name
        val TABLE_NAME =  "UploadTable"
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

        val OPID = "opId"
        val FULLNAME  = "fullName"
        val GENDER = "gender"
        val PHONE = "phone"
        val AGE = "age"
        val ADDRESS = "address"
    }

}