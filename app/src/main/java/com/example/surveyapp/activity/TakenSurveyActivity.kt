package com.example.surveyapp.activity

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.data.base.BaseActivity
import com.example.data.response.AnswerSequenceModel
import com.example.data.response.TakenSurveyModel
import com.example.surveyapp.HomeViewModel
import com.example.surveyapp.R
import com.example.surveyapp.adapter.DownloadedSurveyListRecyclerAdapter
import com.example.surveyapp.databinding.TakenSurveyActivityBinding
import com.example.surveyapp.interfaces.TakenSuveyClick
import com.example.surveyapp.mPrefs
import com.example.surveyapp.utils.Dbhelper
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.MultipartBody
import java.io.File
import java.lang.reflect.Type


class TakenSurveyActivity : BaseActivity() ,OnCLick, TakenSuveyClick {

    lateinit var binding: TakenSurveyActivityBinding
    var downloadedRecyclerAdapter: DownloadedSurveyListRecyclerAdapter?= null
    private lateinit var viewModel: HomeViewModel

    var keyanswer = ""
    var keyvalue = ""

    var full_name = ""
    var gender = ""
    var age = ""
    var address = ""
    var phone = ""
    var audiofile = ""
    var surveId = ""
    var surveyName = ""
    lateinit var db: Dbhelper
    var sharedPreferences: SharedPreferences? = null
    var s1 = ""
    //val list = arrayListOf<DownloadedSurveyListModel>()
    val list = arrayListOf<TakenSurveyModel>()
    val anslist = arrayListOf<AnswerSequenceModel>()
    var surveyIDcheck = ""
    var usernameCheck = ""
    var uploadbtnclick = false
    val keyanswermap = hashMapOf<String, String>()

    var opIdArray = arrayListOf<String>()

    var surveySerialId = ""
    var surveyIdnumber = ""
    var uploadedCheck = 0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding = DataBindingUtil.setContentView(this,R.layout.activity_teken_survey)

        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE)


        s1 = sharedPreferences!!.getString("loginuserID", "").toString()
        db = Dbhelper(this, null)

        surveyName = intent.getStringExtra("surveyName").toString()


        setObservers()
        getDBData()

    }

    var spId = 0
    var sid = 0
    var mPosition = -1
    override fun onClick(sid: Int, spId: Int, position: Int) {

        this.spId = spId
        this.sid = sid
        this.mPosition = position
        Log.e("TAG90", "onClick: $sid")

      //  viewModel.getShow(s1, "1")


        // db.uploadSurvey(spId.toInt(),1)
    }

    @SuppressLint("Range")
    fun uploadSurvay(spId: Int, sid: Int) {
        val cursor = db.getQuestion(spId)

        val curs = db.getName()

        Log.e("TAG655", "uploadSurvay: $curs")

        cursor?.moveToFirst()

        val qid = cursor?.getString(cursor.getColumnIndex(Dbhelper.QUESTIONID))
        val position = cursor?.getString(cursor.getColumnIndex(Dbhelper.OPPOSITION))
        val opId = cursor?.getString(cursor.getColumnIndex(Dbhelper.OPID))

        val answermap = hashMapOf<String, String>()

        if (position.isNullOrEmpty() || opId.isNullOrEmpty()) {

            if (position.isNullOrEmpty()) {
                val data =
                    intArrayOf(sid.toInt()).contentToString() + intArrayOf(qid!!.toInt()).contentToString() + intArrayOf(
                        0
                    ).contentToString()
                val answerData = "answer$data"
                answermap.put(answerData, opId.toString())
            } else {
                val data =
                    intArrayOf(sid.toInt()).contentToString() + intArrayOf(qid!!.toInt()).contentToString() + intArrayOf(
                        position!!.toInt()
                    ).contentToString()
                val answerData = "answer$data"
                answermap.put(answerData, "0")
            }

        } else {
            val data =
                intArrayOf(sid.toInt()).contentToString() + intArrayOf(qid!!.toInt()).contentToString() + intArrayOf(
                    position!!.toInt()
                ).contentToString()
            val answerData = "answer$data"
            answermap.put(answerData, opId.toString())
        }

        Log.e("TAGHAT", "onCreate: ${qid + position + opId}")
        while (cursor!!.moveToNext()) {

            val qid = cursor?.getString(cursor.getColumnIndex(Dbhelper.QUESTIONID))
            val position = cursor?.getString(cursor.getColumnIndex(Dbhelper.OPPOSITION))
            val opId = cursor?.getString(cursor.getColumnIndex(Dbhelper.OPID))


            if (position.isNullOrEmpty() || opId.isNullOrEmpty()) {

                if (position.isNullOrEmpty()) {
                    val data =
                        intArrayOf(sid.toInt()).contentToString() + intArrayOf(qid!!.toInt()).contentToString() + intArrayOf(
                            0
                        ).contentToString()
                    val answerData = "answer$data"
                    answermap.put(answerData, opId.toString())
                } else if (opId.isNullOrEmpty()) {
                    val data =
                        intArrayOf(sid.toInt()).contentToString() + intArrayOf(qid!!.toInt()).contentToString() + intArrayOf(
                            position!!.toInt()
                        ).contentToString()
                    val answerData = "answer$data"
                    answermap.put(answerData, "0")
                } else {
                    val data =
                        intArrayOf(sid.toInt()).contentToString() + intArrayOf(qid!!.toInt()).contentToString() + intArrayOf(
                            0
                        ).contentToString()
                    val answerData = "answer$data"
                    answermap.put(answerData, "0")
                }

            } else {
                val data =
                    intArrayOf(sid.toInt()).contentToString() + intArrayOf(qid!!.toInt()).contentToString() + intArrayOf(
                        position!!.toInt()
                    ).contentToString()
                val answerData = "answer$data"
                answermap.put(answerData, opId.toString())
            }

        }

        var audiofile =    intent.getStringExtra("audiofile")

        var file: File = File(""+ mPrefs.prefAudioFileDetails?.filename.toString())
        // var file: File = File(""+audiofile)
        var audio: MultipartBody.Part = getBodyFromAudioFile(file,"files")!!


        Log.e("TAG0012", "uploadSurvay: $audio")
        Log.e("TAG0013", "uploadSurvay:  $answermap")

        viewModel.getAnswerUploadPost( withBody(""+s1)!!,withBody("1")!!, answermap,withBody(full_name)!!,withBody(gender)!!,withBody(phone)!!,withBody(age)!!,withBody(address)!!,
            audio )


    }

    override fun onListClick(sId: Int, spId: Int) {

    }

    private fun setObservers() {
       // observerShow()
        observeUpdateQandA()
    }

    private fun observeUpdateQandA() {

        viewModel.getAnswerUploadLiveData()
            .observe(this, Observer {


                if (it.status!!) {

                    //Log.e("TAG", "this is iyt : $it")

                    db.uploadSurveyComplete(surveySerialId.toInt(),surveyIdnumber.toInt(),1)

                    var uploaded = db.uploadSurvey(spId.toInt(), 1)
                    opIdArray.clear()
                    if (uploaded){

                        getDBData()
                    }
                }

            })
    }

   /* private fun observerShow() {

        viewModel.getShowLiveData()
            .observe(this, Observer {

                //showToast("succes")

                if (it != null) {

                    full_name = it.userExamCheck?.fullName.toString()
                    gender = it.userExamCheck?.gender.toString()
                    age = it.userExamCheck?.age.toString()
                    phone = it.userExamCheck?.phone.toString()
                    address = it.userExamCheck?.address.toString()

                    Log.e("TAG", "this is iit : $it")

                   // uploadSurvay(spId, sid)

                }

            })
    }*/

    @SuppressLint("Range")
    fun getDBData() {

        if (!list.isNullOrEmpty()){
            list.clear()
        }


        try{
            val db = Dbhelper(this, null)

            val cursor = db.getUploadSurvey()

            cursor!!.moveToFirst()

            val Sno = cursor.getInt(cursor.getColumnIndex(Dbhelper.ID))
            val username = cursor.getString(cursor.getColumnIndex(Dbhelper.FULLNAME))
            val surveyName = cursor.getString(cursor.getColumnIndex(Dbhelper.SURVEYNAME))
            val surveyId = cursor.getInt(cursor.getColumnIndex(Dbhelper.SURVEYID))
            val uploaded = cursor.getInt(cursor.getColumnIndex(Dbhelper.UPLOADED))
            val usernAgeCheck = cursor.getString(cursor.getColumnIndex(Dbhelper.AGE))
            val usernAddressCheck = cursor.getString(cursor.getColumnIndex(Dbhelper.ADDRESS))
            val usernGenderCheck = cursor.getString(cursor.getColumnIndex(Dbhelper.GENDER))
            val usernPhoneCheck = cursor.getString(cursor.getColumnIndex(Dbhelper.PHONE))
            val usernAudioCheck = cursor.getString(cursor.getColumnIndex(Dbhelper.AUDIOFILE))


            if (Sno == 1){

                full_name = username

            }


            uploadedCheck = uploaded
            gender = usernGenderCheck
            age = usernAgeCheck
            address = usernAddressCheck
            phone = usernPhoneCheck
            audiofile = usernAudioCheck



            var mylist = TakenSurveyModel(username.toString(),surveyId.toString(),surveyName.toString(),Sno.toString(),uploadedCheck)
            list.add(mylist)

            Log.e("TAG", "getDBData: Survey To Upload  ${Sno.toString() +" = "+username +" = "+surveyName +" = "+surveyId +" = "+uploaded +" = "}" )




            while (cursor.moveToNext()) {
                val Sno = cursor.getInt(cursor.getColumnIndex(Dbhelper.ID))
                val username = cursor.getString(cursor.getColumnIndex(Dbhelper.FULLNAME))
                val surveyName = cursor.getString(cursor.getColumnIndex(Dbhelper.SURVEYNAME))
                val surveyId = cursor.getInt(cursor.getColumnIndex(Dbhelper.SURVEYID))
                val uploaded = cursor.getInt(cursor.getColumnIndex(Dbhelper.UPLOADED))
                val usernAgeCheck = cursor.getString(cursor.getColumnIndex(Dbhelper.AGE))
                val usernAddressCheck = cursor.getString(cursor.getColumnIndex(Dbhelper.ADDRESS))
                val usernGenderCheck = cursor.getString(cursor.getColumnIndex(Dbhelper.GENDER))
                val usernPhoneCheck = cursor.getString(cursor.getColumnIndex(Dbhelper.PHONE))
                val usernAudioCheck = cursor.getString(cursor.getColumnIndex(Dbhelper.AUDIOFILE))

                uploadedCheck = uploaded


                if (Sno == 1){

                    full_name = username

                }


                gender = usernGenderCheck
                age = usernAgeCheck
                address = usernAddressCheck
                phone = usernPhoneCheck
                audiofile = usernAudioCheck


                var mylist = TakenSurveyModel(username.toString(),surveyId.toString(),surveyName.toString(),Sno.toString(),uploadedCheck)
                list.add(mylist)

                Log.e("TAG", "getDBData: Survey To Upload  ${Sno.toString() +" = "+username +" = "+surveyName +" = "+surveyId +" = "+uploaded +" = "}" )

            }
            cursor.close()

            if (downloadedRecyclerAdapter == null){
                downloadedRecyclerAdapter = DownloadedSurveyListRecyclerAdapter(list, this as AppCompatActivity,this ,this)
                binding.downloadedRecycler.adapter = downloadedRecyclerAdapter

            }else{

                downloadedRecyclerAdapter?.updatePosition(mPosition,list)

            }


        }catch (e:Exception){


        }
      /*  try {


            val db = Dbhelper(this, null)



            val cursor = db.getName()

            cursor!!.moveToFirst()

            val Sno = cursor.getString(cursor.getColumnIndex(Dbhelper.ID))
            val name = cursor.getString(cursor.getColumnIndex(Dbhelper.SURVEYNAME))
            val id = cursor.getString(cursor.getColumnIndex(Dbhelper.SURVEYID))
            val upload = cursor.getInt(cursor.getColumnIndex(Dbhelper.UPLOADED))
            val submit = cursor.getInt(cursor.getColumnIndex(Dbhelper.SUBMIT))

            var uploaded = false
            if (upload == 1) {

                uploaded = true

            }
            var submited = false
            if (submit == 1) {

                submited = true

            }

//        val name =  cursor.getString(cursor.getColumnIndex(Dbhelper.SURVEYNAME))
            var mylist = DownloadedSurveyListModel(Sno, name, id, uploaded, submited)
            list.add(mylist)



            while (cursor.moveToNext()) {
                val Sno = cursor.getString(cursor.getColumnIndex(Dbhelper.ID))
                val name = cursor.getString(cursor.getColumnIndex(Dbhelper.SURVEYNAME))
                val id = cursor.getString(cursor.getColumnIndex(Dbhelper.SURVEYID))
                val upload = cursor.getInt(cursor.getColumnIndex(Dbhelper.UPLOADED))
                val submit = cursor.getInt(cursor.getColumnIndex(Dbhelper.SUBMIT))

                var uploaded = false
                if (upload == 1) {

                    uploaded = true

                }
                var submited = false
                if (submit == 1) {

                    submited = true

                }

//        val name =  cursor.getString(cursor.getColumnIndex(Dbhelper.SURVEYNAME))
                var mylist = DownloadedSurveyListModel(Sno, name, id, uploaded, submited)
                list.add(mylist)
            }
            cursor.close()

            if (downloadedRecyclerAdapter == null){
                downloadedRecyclerAdapter = DownloadedSurveyListRecyclerAdapter(list, this as AppCompatActivity, this)
                binding.downloadedRecycler.adapter = downloadedRecyclerAdapter

            }else{

                downloadedRecyclerAdapter?.updatePosition(mPosition,list)

            }

        } catch (e: Exception) {
            Log.e("EOORO", "onCreate: $e",)
        }*/
    }

    override fun onBtnClick(click: Boolean,sid: String,surveyId: String) {

        if (click == true){
            uploadbtnclick = click


           // uploadSurvay(1,8)
            //getUserGeneralData()

            surveySerialId = sid
            surveyIdnumber = surveyId

            getUserData(sid.toString(),surveyId.toString())

        }


    }

    @SuppressLint("Range")
    fun getUserData(surid:String,surveyId:String){



        try{
            val db = Dbhelper(this, null)

            val cursor = db.getTakenSurvey()

            cursor!!.moveToFirst()

            val Sno = cursor.getString(cursor.getColumnIndex(Dbhelper.ID))
            val surveyIDcheck = cursor.getString(cursor.getColumnIndex(Dbhelper.SURVEYID))
            val surveyPrimary = cursor.getString(cursor.getColumnIndex(Dbhelper.SURVEYPRIMARI))
            val questionId = cursor.getString(cursor.getColumnIndex(Dbhelper.QUESTIONID))
            val answer = cursor.getString(cursor.getColumnIndex(Dbhelper.ANSWER))
            val optionId = cursor.getString(cursor.getColumnIndex(Dbhelper.OPID))
            val optionPosition = cursor.getString(cursor.getColumnIndex(Dbhelper.OPCHECK))

            Log.e("TAG001100331", "getUserData: ${surid == surveyPrimary}")


            if (surid == surveyPrimary){

                Log.e("TAG001100221", "getUserData: ${surid == surveyPrimary}")

               /* for (i in optionId){

                    opIdArray.add(i.toString())

                }

                Log.e("TAG1214", "getUserData: $opIdArray", )
*/


                val listType: Type = object : TypeToken<List<String>?>() {}.type

                val myModelList: List<String> = Gson().fromJson(optionId, listType)

                val listTypeOpposition: Type = object : TypeToken<List<String>?>() {}.type

                val myModelListOpPosition: List<String> = Gson().fromJson(optionPosition, listTypeOpposition)

                for (i in 0 until myModelList.size){

                    keyanswer = "answer"+"["+surveyIDcheck+"]"+"["+questionId+"]"+"["+myModelListOpPosition.get(i)+"]"
                    //keyanswer = "answer"+"["+surveyIDcheck+"]"+"["+questionId+"]"+"["+optionPosition+"]"
                  //  opIdArray.add(optionId.split(",").toString().removeSuffix("]").removePrefix("[").trim())
                    keyvalue = myModelList.get(i)

                    keyanswermap.put(keyanswer,keyvalue)
                }

                Log.e("TAG443", "getUserData: $myModelList", )




                //  for (i in opIdArray){

                //    keyvalue = opIdArray.get(i.toInt())

                //  Log.e("TAG1219", "getUserData: $keyvalue", )
                //}


            }



//            Log.e("TAG*************************", "getDBData: $keyanswermap", )

            while (cursor.moveToNext()) {
                val Sno = cursor.getString(cursor.getColumnIndex(Dbhelper.ID))
                val surveyIDcheck = cursor.getString(cursor.getColumnIndex(Dbhelper.SURVEYID))
                val surveyPrimary = cursor.getString(cursor.getColumnIndex(Dbhelper.SURVEYPRIMARI))
                val questionId = cursor.getString(cursor.getColumnIndex(Dbhelper.QUESTIONID))
                val answer = cursor.getString(cursor.getColumnIndex(Dbhelper.ANSWER))
                val optionId = cursor.getString(cursor.getColumnIndex(Dbhelper.OPID))
                val optionPosition = cursor.getString(cursor.getColumnIndex(Dbhelper.OPCHECK))

                Log.e("TAG001100331", "getUserData: ${surid == surveyPrimary}")


                if (surid == surveyPrimary){

                    Log.e("TAG001100221", "getUserData: ${surid == surveyPrimary}")

                    val listType: Type = object : TypeToken<List<String>?>() {}.type

                    val myModelList: List<String> = Gson().fromJson(optionId, listType)

                    val listTypeOpposition: Type = object : TypeToken<List<String>?>() {}.type

                    val myModelListOpPosition: List<String> = Gson().fromJson(optionPosition, listTypeOpposition)

                    for (i in 0 until myModelList.size){

                        keyanswer = "answer"+"["+surveyIDcheck+"]"+"["+questionId+"]"+"["+myModelListOpPosition.get(i)+"]"
                        //keyanswer = "answer"+"["+surveyIDcheck+"]"+"["+questionId+"]"+"["+optionPosition+"]"
                        //  opIdArray.add(optionId.split(",").toString().removeSuffix("]").removePrefix("[").trim())
                        keyvalue = myModelList.get(i)

                        keyanswermap.put(keyanswer,keyvalue)
                    }




                    //  for (i in opIdArray){

                    //    keyvalue = opIdArray.get(i.toInt())

                      //  Log.e("TAG1219", "getUserData: $keyvalue", )
                    //}


                   // keyanswermap.put(keyanswer,keyvalue)

                }




//                Log.e("TAG1************************* 111", "getDBData: $keyanswermap", )


            }

            var file: File = File(""+ audiofile.toString())
            var audio: MultipartBody.Part = getBodyFromAudioFile(file,"files")!!

            for (position in 0 until opIdArray.size){

                Log.e("TAG214",
                    ("getUserData: ${opIdArray.get(position)}  " ==  " ${position}} ").toString()
                )

            }

           /* val listType: Type = object : TypeToken<List<String>?>() {}.type

            val myModelList: List<String> = Gson().fromJson(opIdArray.toString(), listType)

            Log.e("TAG213", "getUserData: ${opIdArray}")
            Log.e("TAG215", "getUserData: ${myModelList}")*/

            viewModel.getAnswerUploadPost( withBody(""+s1)!!,withBody(surveyId)!!, keyanswermap,withBody(full_name)!!,withBody(gender)!!,withBody(phone)!!,withBody(age)!!,withBody(address)!!, audio )


            cursor.close()



        }catch (e:Exception){

        }



/*

        for (i in 0 until downloadedRecyclerAdapter?.anslist!!.size) {

            keyanswer = "answer"+"["+anslist[i].surveyid+"]"+"["+anslist[i].questionid+"]"+"["+anslist[i].optionPosition+"]"
            keyvalue = anslist[i].optionid

            keyanswermap.put(keyanswer,keyvalue)

          //  Log.e("TAG1112212101", "getUserData: ****************************************************** \n$keyanswermap", )

        }


        Log.e("TAG1112212101", "getUserData: ****************************************************** \n$keyanswermap", )



        var file: File = File(""+ audiofile.toString())
        var audio: MultipartBody.Part = getBodyFromAudioFile(file,"files")!!


        viewModel.getAnswerUploadPost( withBody("2")!!,withBody("1")!!, keyanswermap,withBody(full_name)!!,withBody(gender)!!,withBody(phone)!!,withBody(age)!!,withBody(address)!!, audio )



*/

    }





}