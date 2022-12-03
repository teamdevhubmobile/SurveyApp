package com.example.surveyapp.utils

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.data.response.DownloadedSurveyListModel
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

        val query3 = ("CREATE TABLE " + TABLE_NAME3 + " ("
                + ID + " INTEGER PRIMARY KEY, " +
                FULLNAME + " TEXT," +
                GENDER + " VARCHAR," +
                AGE + " INTEGER," +
                PHONE + " INTEGER," +
                ADDRESS + " VARCHAR," +
                SURVEYID + " INTEGER," +
                SURVEYNAME + " VARCHAR," +
                QUESTIONID + " INTEGER," +
                OPID + " INTEGER," +
                ANSWER + " VARCHAR" +
                ")")

        // we are calling sqlite
        // method for executing our query
        db?.execSQL(query3)

        val query4 = ("CREATE TABLE " + TABLE_NAME4 + " ("
                + ID + " INTEGER PRIMARY KEY, " +
                FULLNAME + " TEXT," +
                SURVEYID + " INTEGER," +
                SURVEYNAME + " VARCHAR," +
                AUDIOFILE + " VARCHAR," +
                UPLOADED + " BOOLEAN"+
                ")")

        // we are calling sqlite
        // method for executing our query
        db?.execSQL(query4)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        // this method is to check if table already exists
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }

    var servayPId :Long = 0
    // This method is for adding data in our database
    @SuppressLint("Range")
    fun addSurvey(surveyId : String, surveyName : String, uploadId : String, uploaded: Boolean, submit: Boolean):Boolean{
        // below we are creating
        // a content values variabl
        var surveyIdLocal = arrayListOf<String>()
        val cv = ContentValues()
        var surveyExist = false

        // we are inserting our values
        // in the form of key-value pair

        cv.put(SURVEYID, surveyId)
        cv.put(SURVEYNAME, surveyName)
        cv.put(UPLOADID, uploadId)
        cv.put(UPLOADED, uploaded)
        cv.put(SUBMIT, submit)




        val db = this.writableDatabase

        // all values are inserted into database

     /*   var cursor: Cursor? = null
        val sql = "SELECT SURVEYID FROM " + TABLE_NAME+ " WHERE SURVEYID=" + surveyId
        cursor = db.rawQuery(sql, null)

        Log.e("TAG11", "Check addSurvey: ${cursor.count}", )

        if (cursor.count > 0) {
            Log.e("TAG11", " if Check addSurvey: ${cursor.count}", )

        } else {
            Log.e("TAG11", " else Check addSurvey: ${cursor.count}", )

        }
        cursor.close()

*/


        val cursor = getName()


        if (cursor!!.moveToFirst()) {


            surveyIdLocal.add(cursor.getString(cursor.getColumnIndex(Dbhelper.SURVEYID)))

        }


        while (cursor.moveToNext()) {

          surveyIdLocal.add(cursor.getString(cursor.getColumnIndex(Dbhelper.SURVEYID)))


        }


        if (surveyIdLocal.contains(surveyId)){

            Log.e("TAG11", "addSurvey: if =  $surveyIdLocal", )
            surveyExist = true

        }else{

            Log.e("TAG11", "addSurvey: else =  $surveyIdLocal", )

            servayPId =  db.insert(TABLE_NAME, null, cv)


        }


        // at last we are
        // closing our database
        db.close()

        return surveyExist
    }

    @SuppressLint("Range")
    fun addQuestion(questionId : String, surveyId : String, optionId : ArrayList<OptionItem>, spID : Int, sID : Int ){

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

    @SuppressLint("Range")
    fun addTakenSurveyTable(fullname:String,gender:String,age:String,address:String,mobile:String,surveyId: String,surveyName: String,questionId: String,optionId: String, answer: String){

        val inputArray = ArrayList<String>()
        val gson = Gson()

        val inputString = gson.toJson(optionId)

        // below we are creating
        // a content values variable



        val cv = ContentValues()

        try {


            cv.put(FULLNAME,fullname)
            cv.put(GENDER,gender)
            cv.put(AGE,age)
            cv.put(ADDRESS,address)
            cv.put(PHONE,mobile)
            cv.put(SURVEYID,surveyId)
            cv.put(SURVEYNAME, surveyName)
            cv.put(QUESTIONID,questionId)
            cv.put(OPID,optionId)
            cv.put(ANSWER,answer)


        } catch (e: Exception) {
            Log.e("Problem", "$e ")
        }


        val db = this.writableDatabase

        Log.e("TAG121", "addTakenSurveyTable: ${TABLE_NAME3.toString()}", )
        Log.e("TAG121", "addTakenSurveyTable: ${cv.toString()}", )

        db.insert(TABLE_NAME3, null, cv)

        db.close()
    }

    @SuppressLint("Range")
    fun addUploadSurveyTable(fullname:String,surveyId:String,surveyName:String,audiofile:String,upload:Boolean){

        val inputArray = ArrayList<String>()
        val gson = Gson()

       // val inputString = gson.toJson(optionId)

        // below we are creating
        // a content values variable



        val cv = ContentValues()

        try {


            cv.put(FULLNAME,fullname)
            cv.put(SURVEYID,surveyId)
            cv.put(SURVEYNAME,surveyName)
            cv.put(AUDIOFILE,audiofile)
            cv.put(UPLOADED,upload)


        } catch (e: Exception) {
            Log.e("Problem", "$e ")
        }


        val db = this.writableDatabase

        Log.e("TAG121", "addTakenSurveyTable: ${TABLE_NAME4.toString()}", )
        Log.e("TAG121", "addTakenSurveyTable: ${cv.toString()}", )

        db.insert(TABLE_NAME4, null, cv)

        db.close()
    }

    // below method is to get
    // all data from our database
    fun getTakenSurvey(): Cursor? {

        // here we are creating a readable
        // variable of our database
        // as we want to read value from it
        val db = this.readableDatabase

        // below code returns a cursor to
        // read data from the database

        Log.e("TAG111", "getTakenSurvey: ${"SELECT * FROM " + TABLE_NAME3}", )

        return db.rawQuery("SELECT * FROM " + TABLE_NAME3, null)

    }

    fun getUploadSurvey(): Cursor? {

        // here we are creating a readable
        // variable of our database
        // as we want to read value from it
        val db = this.readableDatabase

        // below code returns a cursor to
        // read data from the database

        Log.e("TAG111", "getTakenSurvey: ${"SELECT * FROM " + TABLE_NAME4}", )

        return db.rawQuery("SELECT * FROM " + TABLE_NAME4, null)

    }

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
            Log.e("TAG888", "answerSubmit: $updateString")
            db.execSQL(updateString);

            val opIdSubmit="UPDATE "+ TABLE_NAME2 + " SET "+ OPID + " = '"+opId+"'   WHERE " + "questionID" + " = "+qId +" AND "+ "SpId" + " = "+ spid
            Log.e("TAG888", "answerSubmit: $updateString")
            db.execSQL(opIdSubmit);

            val opPositionSubmit="UPDATE "+ TABLE_NAME2 + " SET "+ OPPOSITION + " = '"+opPosition+"'   WHERE " + "questionID" + " = "+qId +" AND "+ "SpId" + " = "+ spid
            Log.e("TAG888", "answerSubmit: $updateString")
            db.execSQL(opPositionSubmit);

           /* val updateString="INSERT "+ TABLE_NAME2 + " SET "+ ANSWER + " = '"+answer+"'   WHERE " + "questionID" + " = "+qId +" AND "+ "SpId" + " = "+ spid
            Log.e("TAG888", "answerSubmit: $updateString")
            db.execSQL(updateString);

            val opIdSubmit="INSERT "+ TABLE_NAME2 + " SET "+ OPID + " = '"+opId+"'   WHERE " + "questionID" + " = "+qId +" AND "+ "SpId" + " = "+ spid
            Log.e("TAG888", "answerSubmit: $updateString")
            db.execSQL(opIdSubmit);

            val opPositionSubmit="INSERT "+ TABLE_NAME2 + " SET "+ OPPOSITION + " = '"+opPosition+"'   WHERE " + "questionID" + " = "+qId +" AND "+ "SpId" + " = "+ spid
            Log.e("TAG888", "answerSubmit: $updateString")
            db.execSQL(opPositionSubmit);*/
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

    fun uploadSurvey(surveyId : Int, uploaded: Int) : Boolean{

        try {

            val cv = ContentValues()
            //cv.put(ANSWER,answer)
            // cv.put(ID,qId)

            val db = this.readableDatabase

            val updateString="UPDATE "+ TABLE_NAME + " SET "+ UPLOADED + " = '"+uploaded+"'   WHERE " + "id" + " = "+surveyId
            //val updateString="INSERT "+ TABLE_NAME + " SET "+ UPLOADED + " = '"+uploaded+"'   WHERE " + "id" + " = "+surveyId
            Log.e("TAG8008", "answerSubmit: $updateString")
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

        return true
    }

    fun submitSurvey(surveyId : Int, submited: Int){

        try {

            val cv = ContentValues()
            //cv.put(ANSWER,answer)
            // cv.put(ID,qId)

            val db = this.readableDatabase

            val updateString="UPDATE "+ TABLE_NAME + " SET "+ SUBMIT + " = '"+submited+"'   WHERE " + "id" + " = "+surveyId
           // val updateString="INSERT "+ TABLE_NAME + " SET "+ SUBMIT + " = '"+submited+"'   WHERE " + "id" + " = "+surveyId
            Log.e("TAG8008", "answerSubmit: $updateString")
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
        val TABLE_NAME3 = "TakenSurveyTable"
        val TABLE_NAME4 = "UploadTable"

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



        val FULLNAME  = "fullName"
        val GENDER = "gender"
        val PHONE = "phone"
        val AGE = "age"
        val ADDRESS = "address"
        val AUDIOFILE = "audio"

    }

}