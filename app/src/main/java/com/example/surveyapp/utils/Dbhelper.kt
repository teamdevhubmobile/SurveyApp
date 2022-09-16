package com.example.surveyapp.utils

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.surveyapp.OptionItem
import com.google.gson.Gson




class Dbhelper(context: Context, nothing: Nothing?): SQLiteOpenHelper(context, DATABASE_NAME ,null,1) {

    var answer = ""

    override fun onCreate(db: SQLiteDatabase?) {
        val query = ("CREATE TABLE " + TABLE_NAME + " ("
                + ID + " INTEGER PRIMARY KEY, " +
                SURVEYID + " INTEGER," +
                SURVEYNAME + " VARCHAR," +
                UPLOADID + " INTEGER," +
                UPLOADED + " BOOLEAN," +
                SUBMIT + " BOOLEAN" +")")

        // we are calling sqlite
        // method for executing our query
        db?.execSQL(query)
        val query2 = ("CREATE TABLE " + TABLE_NAME2 + " ("
                + ID + " INTEGER PRIMARY KEY, " +
                SURVEYID + " INTEGER," +
                SPID + " INTEGER," +
                QUESTIONID + " INTEGER," +
                OPID + " INTEGER," +
                OPPOSITION + " INTEGER," +
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
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }

    var servayPId :Long = 0
    // This method is for adding data in our database
    fun addSurvey(surveyId : String, surveyName : String, uploadId : String,uploaded: Boolean,submit: Boolean){
        // below we are creating
        // a content values variable
        val cv = ContentValues()

        // we are inserting our values
        // in the form of key-value pair

        cv.put(SURVEYID, surveyId)
        cv.put(SURVEYNAME, surveyName)
        cv.put(UPLOADID, uploadId)
        cv.put(UPLOADED, uploaded)
        cv.put(SUBMIT, submit)




        val db = this.writableDatabase

        // all values are inserted into database
       servayPId =  db.insert(TABLE_NAME, null, cv)

        // at last we are
        // closing our database
        db.close()
    }

    fun addQuestion(questionId : String,surveyId : String, optionId : ArrayList<OptionItem>,spID : Int,sID : Int ){

        val inputArray = ArrayList<String>()
        val gson = Gson()

        val inputString = gson.toJson(optionId)

        // below we are creating
        // a content values variable



        val cv = ContentValues()

        try {
                cv.put(OPTION,inputString)

            cv.put(SURVEYID,sID)
            cv.put(SPID,spID)
            cv.put(QUESTIONID,questionId)
            cv.put(QUESTION,surveyId)


        } catch (e: Exception) {
            Log.e("Problem", "$e ")
        }



//        cv.put(QUESTIONID,questionId)
//        cv.put(QUESTION,surveyId)
//        cv.put(OPTION,optionId)
//




       // cv.put(SURVEYNAME, optionId)
        //cv.put(UPLOADID, answer)

        // here we are creating a
        // writable variable of
        // our database as we want to
        // insert value in our database
        val db = this.writableDatabase

        // all values are inserted into database
        db.insert(TABLE_NAME2, null, cv)

        // at last we are
        // closing our database
        db.close()
    }

    // below method is to get
    // all data from our database
    fun getName(): Cursor? {

        // here we are creating a readable
        // variable of our database
        // as we want to read value from it
        val db = this.readableDatabase

        // below code returns a cursor to
        // read data from the database
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null)

    }

    fun getQuestion(spid :Int): Cursor? {

        // here we are creating a readable
        // variable of our database
        // as we want to read value from it
        val db = this.readableDatabase

        // below code returns a cursor to
        // read data from the database
        return db.rawQuery("SELECT * FROM " + TABLE_NAME2 +" WHERE " + "SpId" + " = "+spid,null)

    }

   /* fun spID(surveyID : String, primaryID : String){

        this.answer = answer

        try {

            val cv = ContentValues()
            cv.put(ANSWER,answer)
            // cv.put(ID,qId)

            val db = this.readableDatabase

            val updateString="UPDATE "+ TABLE_NAME2 + " SET "+ ANSWER + " = '"+answer+"'   WHERE " + "questionID" + " = "+qId
            Log.e("TAG888", "answerSubmit: $updateString", )
            db.execSQL(updateString);
            // db.rawQuery("INSERT "+ TABLE_NAME2 + " SET "+ ANSWER + " = '"+answer+"'   WHERE " + "questionID" + " = "+qId, null);


            val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME2 WHERE questionID = $qId ", null)

            if (cursor.moveToFirst()) {
                val columnNames = cursor.columnNames

                for (i in columnNames) {
                    // Assume every column is int
                    Log.e("TAG102", "answerSubmit: "+ cursor.getString( cursor.getColumnIndex("answer") ) )
                }
            }

            cursor.close()

            // var whereArgs = arrayOf(qId)
            // db.execSQL("UPDATE ${TABLE_NAME2} SET answer=$answer WHERE questionID=${qId.toInt()}")
            //   db.update(TABLE_NAME2,cv,"questionID", whereArgs)
        }catch (e:Exception){
            Log.e("TAG16161", "answerSubmit: ${e.message}")
        }

    }*/

    @SuppressLint("Range")
    fun answerSubmit(qId : String, answer : String, spid: Int,opId:String,opPosition:String){

        this.answer = answer

        try {

            val cv = ContentValues()
            cv.put(ANSWER,answer)
           // cv.put(ID,qId)

            val db = this.readableDatabase

            val updateString="UPDATE "+ TABLE_NAME2 + " SET "+ ANSWER + " = '"+answer+"'   WHERE " + "questionID" + " = "+qId +" AND "+ "SpId" + " = "+ spid
            Log.e("TAG888", "answerSubmit: $updateString", )
            db.execSQL(updateString);

            val opIdSubmit="UPDATE "+ TABLE_NAME2 + " SET "+ OPID + " = '"+opId+"'   WHERE " + "questionID" + " = "+qId +" AND "+ "SpId" + " = "+ spid
            Log.e("TAG888", "answerSubmit: $updateString", )
            db.execSQL(opIdSubmit);

            val opPositionSubmit="UPDATE "+ TABLE_NAME2 + " SET "+ OPPOSITION + " = '"+opPosition+"'   WHERE " + "questionID" + " = "+qId +" AND "+ "SpId" + " = "+ spid
            Log.e("TAG888", "answerSubmit: $updateString", )
            db.execSQL(opPositionSubmit);
           // db.rawQuery("INSERT "+ TABLE_NAME2 + " SET "+ ANSWER + " = '"+answer+"'   WHERE " + "questionID" + " = "+qId, null);


           /* val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME2 WHERE questionID = $qId ", null)

            if (cursor.moveToFirst()) {
                val columnNames = cursor.columnNames

                for (i in columnNames) {
                    // Assume every column is int
                    Log.e("TAG102", "answerSubmit: "+ cursor.getString( cursor.getColumnIndex("answer") ) )
                }
            }

            cursor.close()*/

           // var whereArgs = arrayOf(qId)
            // db.execSQL("UPDATE ${TABLE_NAME2} SET answer=$answer WHERE questionID=${qId.toInt()}")
         //   db.update(TABLE_NAME2,cv,"questionID", whereArgs)
        }catch (e:Exception){
            Log.e("TAG16161", "answerSubmit: ${e.message}")
        }

    }

    fun uploadSurvey(surveyId : Int, uploaded: Int){

        try {

            val cv = ContentValues()
            //cv.put(ANSWER,answer)
            // cv.put(ID,qId)

            val db = this.readableDatabase

            val updateString="UPDATE "+ TABLE_NAME + " SET "+ UPLOADED + " = '"+uploaded+"'   WHERE " + "id" + " = "+surveyId
            Log.e("TAG8008", "answerSubmit: $updateString", )
            db.execSQL(updateString);
            // db.rawQuery("INSERT "+ TABLE_NAME2 + " SET "+ ANSWER + " = '"+answer+"'   WHERE " + "questionID" + " = "+qId, null);

/*
            val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME2 WHERE questionID = $qId ", null)

            if (cursor.moveToFirst()) {
                val columnNames = cursor.columnNames

                for (i in columnNames) {
                    // Assume every column is int
                    Log.e("TAG102", "answerSubmit: "+ cursor.getString( cursor.getColumnIndex("answer") ) )
                }
            }*/

           // cursor.close()

            // var whereArgs = arrayOf(qId)
            // db.execSQL("UPDATE ${TABLE_NAME2} SET answer=$answer WHERE questionID=${qId.toInt()}")
            //   db.update(TABLE_NAME2,cv,"questionID", whereArgs)
        }catch (e:Exception){
            Log.e("TAG16161", "answerSubmit: ${e.message}")
        }
    }

    fun submitSurvey(surveyId : Int, submited: Int){

        try {

            val cv = ContentValues()
            //cv.put(ANSWER,answer)
            // cv.put(ID,qId)

            val db = this.readableDatabase

            val updateString="UPDATE "+ TABLE_NAME + " SET "+ SUBMIT + " = '"+submited+"'   WHERE " + "id" + " = "+surveyId
            Log.e("TAG8008", "answerSubmit: $updateString", )
            db.execSQL(updateString);
            // db.rawQuery("INSERT "+ TABLE_NAME2 + " SET "+ ANSWER + " = '"+answer+"'   WHERE " + "questionID" + " = "+qId, null);

/*
            val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME2 WHERE questionID = $qId ", null)

            if (cursor.moveToFirst()) {
                val columnNames = cursor.columnNames

                for (i in columnNames) {
                    // Assume every column is int
                    Log.e("TAG102", "answerSubmit: "+ cursor.getString( cursor.getColumnIndex("answer") ) )
                }
            }*/

            // cursor.close()

            // var whereArgs = arrayOf(qId)
            // db.execSQL("UPDATE ${TABLE_NAME2} SET answer=$answer WHERE questionID=${qId.toInt()}")
            //   db.update(TABLE_NAME2,cv,"questionID", whereArgs)
        }catch (e:Exception){
            Log.e("TAG16161", "answerSubmit: ${e.message}")
        }
    }

    companion object{
        // here we have defined variables for our database


        // below is variable for database name
        private val DATABASE_NAME = "SurveyTableData"
        private val DATABASE_NAME2 = "QuestionTableData"

        // below is the variable for database version
        private val DATABASE_VERSION = 1

        // below is the variable for table name
        val TABLE_NAME = "SurveyTable"
        val TABLE_NAME2 = "QuestionTable"

        // below is the variable for id column
        val ID = "id"

        // below is the variable for name column
        val SURVEYID = "surveyID"
        val QUESTIONID = "questionID"
        val QUESTION = "question"
        val OPID = "opId"
        val OPPOSITION = "opPosition"
            val OPTION = "option"
        val OPTIONID = "optionID"
        val ANSWER = "answer"
        val STATUS = "status"
        val ATTEMPT = "attempt"

        // below is the variable for age column
        val SURVEYNAME  = "SurveyName"
        val UPLOADID = "UploadId"
        val UPLOADED = "Uploaded"
        val SUBMIT = "Submit"
        val SPID = "SpId"
    }

}