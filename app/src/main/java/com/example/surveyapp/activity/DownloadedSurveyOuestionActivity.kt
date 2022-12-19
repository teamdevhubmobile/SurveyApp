package com.example.surveyapp.activity

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.example.data.base.BaseActivity
import com.example.data.response.AudioModel
import com.example.data.response.SubmitAnswersModel
import com.example.surveyapp.*
import com.example.surveyapp.adapter.ViewPagerAdapter
import com.example.surveyapp.databinding.DownloadedSurveyQuestionActivityBinding
import com.example.surveyapp.interfaces.OpCheckListener
import com.example.surveyapp.interfaces.OptionsListenerInterface
import com.example.surveyapp.utils.Dbhelper
import com.example.surveyapp.utils.Dbhelper2
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONArray
import java.io.IOException
import java.lang.reflect.Type


class DownloadedSurveyOuestionActivity : BaseActivity(),OptionsListenerInterface,OpCheckListener {

    lateinit var binding: DownloadedSurveyQuestionActivityBinding
   // private lateinit var viewPager: ViewPager
    lateinit var mViewPagerAdapter: ViewPagerAdapter
    var mList = arrayListOf<ResponseItem>()
    var qId = ""
    var answer = ""
    var submitList = arrayListOf<SubmitAnswersModel>()
   // val dbhelper  = Dbhelper(this,null)
   var id = ""
    var question = ""
    var OId = ""
    private lateinit var viewModel: HomeViewModel
    var sharedPreferences: SharedPreferences? = null
    var s1 = ""
    var full_name = ""
    var gender = ""
    var age = ""
    var address = ""
    var phone = ""
    var surveId = ""
    var surveyName = ""
    var btnpoint = 0
    var opcheck = arrayListOf<Int>()


    var countDownTimer: CountDownTimer? = null
    var second = -1
    var minute:Int = 0


    var mRecorder: MediaRecorder? = null
    var mPlayer: MediaPlayer? = null
    var mFileName: String? = null
    val REQUEST_AUDIO_PERMISSION_CODE = 1

    var number = 0
    var pagenumber = 0
    var audiofilename = ""

    var customername = ""
    var customergender = ""
    var customerage = ""
    var customeraddress = ""
    var customermobile = ""
    //val dbhelper2 = Dbhelper2(this,null)

    var surveyPrimary = ""


    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_downloaded_survey_ouestion)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        setObservers()

        val db = Dbhelper(this, null)

        val sid = intent.getStringExtra("sid")
        val spid = intent.getStringExtra("spid")

       customername =  intent.getStringExtra("name").toString()
       customerage =  intent.getStringExtra("age").toString()
       customergender =  intent.getStringExtra("gender").toString()
       customermobile =  intent.getStringExtra("mobile").toString()
       customeraddress =  intent.getStringExtra("address").toString()
        surveyName =  intent.getStringExtra("surveyName").toString()
        surveyPrimary = intent.getStringExtra("spid").toString()

        val cursor = db.getQuestion(spid!!.toInt())



        startRecording()

      /*  binding.recordBtn.setOnClickListener {

            if (btnpoint != 0){

                btnpoint--
                binding.recordBtn.setImageDrawable(getDrawable(R.drawable.mic_microphone_icon))
                second = -1
                minute = 0
                stopTimer()
                binding.timerText.text="00:00"
                mRecorder!!.stop()

                //playAudio()

            }else{

                binding.recordBtn.setImageDrawable(getDrawable(R.drawable.square_icon))
                startRecording()
                btnpoint++

            }


        }*/

  /*      binding.submitFab.setOnClickListener {

            var file: File = File(""+mFileName)
            var audio: MultipartBody.Part = getBodyFromAudioFile(file,"image")!!

            viewModel.getMukulAudio(audio)

        }*/

        binding.submitBtn.setOnClickListener {

            //db.addTakenSurveyTable("","","","","","")
          val insertedid =  db.addUploadSurveyTable(customername,customergender,customerage,customeraddress,customermobile,sid.toString(),surveyName,mFileName.toString(),false)

            //db.setPosition(sid.toString(),customername.toString())
            autoStop()

            var newSList = mViewPagerAdapter.getList()

            if (!newSList.isNullOrEmpty()){

                for (item in newSList){

                    var optionList = item.option

                    var answer =""
                    var opId =""
                    var opIdmulti = arrayListOf<String>()
                    var opPositionmulti = arrayListOf<String>()
                    var opPosition =""

                    Log.e("TAGop", "onCreate: "+ item)

                    for (op in optionList!!){
                     //   Log.e("TAGop1", "onCreate: "+ op)
                        if (!op?.answers.isNullOrEmpty()){
                           answer = op?.answers.toString()
                            opId = op?.optionID.toString()
                           // opIdmulti.add(opId)
                            opPosition = op?.position.toString()


                        }

                    }

                    if (!item.ansListCheck.isNullOrEmpty()){

                        var optioncheckList = item.ansListCheck

                        for (optionItem in optioncheckList!!){

                            opIdmulti.add(optionItem.optionId.toString())
                            opPositionmulti.add(optionItem.optionPosition.toString())

                        }

                    }

                    db.answerSubmit(item.questionBankID.toString(), answer,spid.toInt(),opId,opPosition)

                   /* for(i in 0 until opcheck.size){

                        db.addTakenSurveyTable(customername,customergender,customerage,customeraddress,customermobile,sid.toString(),surveyName,item.questionBankID.toString(),opId,
                            opcheck[i].toString(),answer)


                    }*/

                    Log.e("TAG332", "onCreate: ${opIdmulti}" )


                    db.addTakenSurveyTable(insertedid.toString(),sid.toString(),item.questionBankID.toString(),opIdmulti.toString(),
                        opPositionmulti.toString(),answer)




                }

                db.submitSurvey(spid.toInt(),1)

               // val intent = Intent(this,DownloadedSurveyActivity::class.java)
                val intent = Intent(this,TakenSurveyActivity::class.java)
                audiofilename = mFileName.toString()
                mPrefs.prefAudioFileDetails = AudioModel(mFileName.toString())
                intent.putExtra("audiofile",audiofilename)
                intent.putExtra("surveyName",surveyName)
                startActivity(intent)
                finish()

            }else{
                Toast.makeText(this, "Select Answer", Toast.LENGTH_SHORT).show()
            }

            /*if (!submitList.isNullOrEmpty()) {

                for (item in submitList) {

                    Log.e("TAG97", "onCreate: ${item.questionId+ item.item.name.toString()}",)



                   // db.submitSurvey("1".toInt(),1)

                }
            }else{

                Toast.makeText(this, "Select Answer", Toast.LENGTH_SHORT).show()

            }*/

        }


        val gson = Gson()


        cursor!!.moveToFirst()

        id =  cursor.getString(cursor.getColumnIndex(Dbhelper.QUESTIONID))
        question =  cursor.getString(cursor.getColumnIndex(Dbhelper.QUESTION))
        OId =  cursor.getString(cursor.getColumnIndex(Dbhelper.OPTION))
        val answers =  cursor.getString(cursor.getColumnIndex(Dbhelper.ANSWER))

        Log.e("TAG 122112", "onCreate: ${answers.toString()}")
//            val OptionId =  cursor.getString(cursor.getColumnIndex(Dbhelper.OPTIONID))
//            val OptionName =  cursor.getString(cursor.getColumnIndex(Dbhelper.QUESTIONID))

        //  Toast.makeText(this, ""+id.toString(), Toast.LENGTH_SHORT).show()
        //binding.dqTxt.setText(id.toString()+" "+OptionName.toString()+" "+OptionId.toString())

        val type: Type = object : TypeToken<ArrayList<OptionItem?>?>() {}.type



        val finalOutputString: ArrayList<OptionItem> = gson.fromJson(OId, type)

        var array12 = JSONArray(OId)


        Log.e("TAG12", "onCreate: "+array12 )

        var optionList = arrayListOf<OptionItem>()

        for (i in 0 until array12.length()){

            var optionItem  = array12.getJSONObject(i)

            //val objec: OptionItem = gson.fromJson(optionItem, OptionItem::class.java)
            val objecj = gson.fromJson(optionItem.toString(),OptionItem::class.java)

            optionList.add(objecj)
            // Log.e("TAG77", "onCreate: "+objecj.name )

        }

        mList.add(ResponseItem(id,question,"2",optionList))
        Log.e("TAG111", "DataBase: QuestionId = "+id.toString()+"   Question = "+question.toString()+"   Option = "+OId )
        Log.e("TAG611", "DataBase: list = "+mList )

       // Log.e("TAG 122112", "onCreate: ${answers.toString()}", )

        while(cursor.moveToNext()) {

             id =  cursor.getString(cursor.getColumnIndex(Dbhelper.QUESTIONID))
            question =  cursor.getString(cursor.getColumnIndex(Dbhelper.QUESTION))
             OId =  cursor.getString(cursor.getColumnIndex(Dbhelper.OPTION))
            val answers =  cursor.getString(cursor.getColumnIndex(Dbhelper.ANSWER))

            Log.e("TAG 122112", "onCreate: ${answers.toString()}")
//            val OptionId =  cursor.getString(cursor.getColumnIndex(Dbhelper.OPTIONID))
//            val OptionName =  cursor.getString(cursor.getColumnIndex(Dbhelper.QUESTIONID))

          //  Toast.makeText(this, ""+id.toString(), Toast.LENGTH_SHORT).show()
            //binding.dqTxt.setText(id.toString()+" "+OptionName.toString()+" "+OptionId.toString())

            val type: Type = object : TypeToken<ArrayList<OptionItem?>?>() {}.type



            val finalOutputString: ArrayList<OptionItem> = gson.fromJson(OId, type)

            var array12 = JSONArray(OId)


            Log.e("TAG12", "onCreate: "+array12 )

            var optionList = arrayListOf<OptionItem>()

            for (i in 0 until array12.length()){

                var optionItem  = array12.getJSONObject(i)

                //val objec: OptionItem = gson.fromJson(optionItem, OptionItem::class.java)
                val objecj = gson.fromJson(optionItem.toString(),OptionItem::class.java)

                optionList.add(objecj)
               // Log.e("TAG77", "onCreate: "+objecj.name )

            }

            

            mList.add(ResponseItem(id,question,"2",optionList))
            Log.e("TAG111", "DataBase: QuestionId = "+id.toString()+"   Question = "+question.toString()+"   Option = "+OId )
            Log.e("TAG611", "DataBase: list = "+mList )

//            var mylist = DownloadedQuestionOptionModel(id, name,qId,)
//
//            list.add(mylist)
//            //     list2.add(mylist2)
        }
        cursor.close()

        viewPager()


    }

    var viewPagerPageChangeListener: ViewPager.OnPageChangeListener =
        object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
                // your logic here
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                // your logic here
            }

            override fun onPageSelected(position: Int) {
                // your logic here

                pagenumber = position

            }
        }

    private fun viewPager() {

        try {


            mViewPagerAdapter = ViewPagerAdapter(this@DownloadedSurveyOuestionActivity, mList,this,this,surveyPrimary)
            binding.viewPager.pageMargin = 15
            binding.viewPager.setPadding(50, 0, 50, 0);
            binding.viewPager.setClipToPadding(false)
            binding.viewPager.setPageMargin(25)
            binding.viewPager.adapter = mViewPagerAdapter
            binding.viewPager.addOnPageChangeListener(viewPagerPageChangeListener)
            binding.viewPager.offscreenPageLimit = 1000

            binding.nextBtn.setOnClickListener {

                binding.viewPager.setCurrentItem(pagenumber+1, true)

            }

        }catch (e:Exception){

            Log.e("TAG555", "viewPager: $e")
        }
    }

    override fun onOptionClick(item: OptionItem,qId : String) {

        var model = SubmitAnswersModel(item,qId)

        try {


            if (!submitList.isNullOrEmpty()) {

                for (i in 0 until submitList.size) {

                    if (submitList.get(i).questionId.equals(qId)) {

                        Log.e("TAG7", "onOptionClick: " + submitList.get(i).questionId.equals(qId))

                        submitList.removeAt(i)
                        submitList.add(model)
                        return
                    }

                }


                    submitList.add(model)


            } else {

                answer = item.name.toString()
                this.qId = qId.toString()
                submitList.add(SubmitAnswersModel(item, qId))

            }
        }catch (e:Exception){

            Log.e("TAG44", "onOptionClick: $e")

        }

    }

    override fun onSurveyId(surveyId: String) {

    }

    private fun setObservers() {
        observerShow()
        observeUpdateQandA()
        observerAudioSubmit()
    }


    private fun observeUpdateQandA() {

        viewModel.getAnswerUploadLiveData()
            .observe(this@DownloadedSurveyOuestionActivity, Observer {

                //showToast("succes")

                if (it != null) {

                    Log.e("TAG", "this is iyt : $it")

                }

            })
    }
    private fun observerShow() {

        viewModel.getShowLiveData()
            .observe(this@DownloadedSurveyOuestionActivity, Observer {

                //showToast("succes")

                if (it != null) {

                    full_name =   it.userExamCheck?.fullName.toString()
                    gender =  it.userExamCheck?.gender.toString()
                    age =  it.userExamCheck?.age.toString()
                    phone =  it.userExamCheck?.phone.toString()
                    address = it.userExamCheck?.address.toString()

                /*    Log.e("TAG", "observerShow55: ${it.userExamCheck?.fullName.toString()}")
                    Log.e("TAG", "observerShow55: ${it.userExamCheck?.gender.toString()}")
                    Log.e("TAG", "observerShow55: ${it.userExamCheck?.address.toString()}")
                    Log.e("TAG", "observerShow55: ${it.userExamCheck?.phone.toString()}")
                    Log.e("TAG", "observerShow55: ${it.userExamCheck?.age.toString()}")
*/
                    Log.e("TAG", "this is iit : $it")

                }

            })
    }

    private fun observerAudioSubmit() {

        viewModel.getAudioUploadLiveData()
            .observe(this, Observer {

                if (it != null) {


                    //Toast.makeText(this, "Finally audio uploaded", Toast.LENGTH_SHORT).show()
                    /*  Log.e("TAG", "observerShow55: ${it.userExamCheck?.fullName.toString()}")
                      Log.e("TAG", "observerShow55: ${it.userExamCheck?.gender.toString()}")
                      Log.e("TAG", "observerShow55: ${it.userExamCheck?.address.toString()}")
                      Log.e("TAG", "observerShow55: ${it.userExamCheck?.phone.toString()}")
                      Log.e("TAG", "observerShow55: ${it.userExamCheck?.age.toString()}")

                      Log.e("TAG", "this is iit : $it")
  */
                }

            })
    }




    private fun startRecording() {

        if (CheckPermissions()) {

            mFileName = application.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)!!.getAbsolutePath()

            audiofilename = mFileName.toString()
            mPrefs.prefAudioFileDetails = AudioModel(mFileName.toString())


            if (number == 1) {
                mFileName += "/Recording1.mp3"
            }else if (number == 2){
                mFileName += "/Recording2.mp3"
            }else{
                mFileName += "/Recording3.mp3"
            }

           /* if (number == 1) {
                mFileName += "/Recording1.3gp"
            }else if (number == 2){
                mFileName += "/Recording2.3gp"
            }else{
                mFileName += "/Recording3.3gp"
            }*/

            mRecorder = MediaRecorder()

            mRecorder!!.setAudioSource(MediaRecorder.AudioSource.MIC)

            mRecorder!!.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)

            mRecorder!!.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)

            mRecorder!!.setOutputFile(mFileName)
            try {
                mRecorder!!.prepare()
            } catch (e: IOException) {
                Log.e("TAG", "prepare() failed"+e.message+e.printStackTrace())
            }
            // start method will start
            // the audio recording.
            mRecorder!!.start()

            showTimer()
            Toast.makeText(applicationContext,"Recording Started", Toast.LENGTH_SHORT).show()
        } else {
            RequestPermissions()
        }
    }

    fun showTimer() {
        countDownTimer = object : CountDownTimer(Long.MAX_VALUE, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                second++
                binding.timerText.setText(recorderTime())
            }

            override fun onFinish() {}
        }
        (countDownTimer as CountDownTimer).start()
    }

    fun stopTimer(){
        (countDownTimer as CountDownTimer).cancel()
    }

    fun recorderTime(): String? {
        if (second == 60) {
            minute++
            second = 0
        }
        return java.lang.String.format("%02d:%02d", minute, second)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_AUDIO_PERMISSION_CODE -> if (grantResults.size > 0) {
                val permissionToRecord = grantResults[0] == PackageManager.PERMISSION_GRANTED
                val permissionToStore = grantResults[1] == PackageManager.PERMISSION_GRANTED
                if (permissionToRecord && permissionToStore) {
                    Toast.makeText(applicationContext, "Permission Granted", Toast.LENGTH_LONG).show()
                    startRecording()
                } else {
                    Toast.makeText(applicationContext, "Permission Denied", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    fun playAudio() {

        // for playing our recorded audio
        // we are using media player class.
        mPlayer = MediaPlayer()



        try {
            // below method is used to set the
            // data source which will be our file name
            mPlayer!!.setDataSource(mFileName)

            // below method will prepare our media player
            mPlayer!!.prepare()

            // below method will start our media player.
            mPlayer!!.start()


            mPrefs.prefAudioFileDetails = AudioModel(mFileName.toString())
           // statusTV.setText("Recording Started Playing")
        } catch (e: IOException) {
            Log.e("TAG", "prepare() failed")
        }
    }


    fun CheckPermissions(): Boolean {
        val result = ContextCompat.checkSelfPermission(applicationContext,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        val result1 = ContextCompat.checkSelfPermission(applicationContext,
            Manifest.permission.RECORD_AUDIO
        )
        return result == PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED
    }

    private fun RequestPermissions() {
        ActivityCompat.requestPermissions(this, arrayOf(
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        ), REQUEST_AUDIO_PERMISSION_CODE)
    }

    fun autoStop(){

        btnpoint--
        // binding.recordBtn.setImageDrawable(getDrawable(R.drawable.mic_microphone_icon))
        second = -1
        minute = 0
        stopTimer()
        // binding.timerText.text="00:00"
        mRecorder!!.stop()
        //showImage()
        // playAudio()


    }

    override fun opCheckListener(opPosition: Int) {

        Log.e("TAG0901", "opCheckListener: $opPosition", )
        opcheck.add(opPosition)

    }

    override fun onBackPressed() {
    //    super.onBackPressed()

        val dialogClickListener =
            DialogInterface.OnClickListener { dialog, which ->
                when (which) {
                    // on below line we are setting a click listener
                    // for our positive button
                  /*  DialogInterface.BUTTON_POSITIVE -> {
                        // on below line we are displaying a toast message.
                        //  Toast.makeText(this, "Yes clicked", Toast.LENGTH_SHORT).show()


                    }
*/
                    // on below line we are setting click listener
                    // for our negative button.
                    DialogInterface.BUTTON_NEGATIVE -> {
                        // on below line we are dismissing our dialog box.
                        dialog.dismiss()
                    }
                }
            }

        // on below line we are creating a builder variable for our alert dialog
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)

        // on below line we are setting message for our dialog box.
        builder.setMessage("Please Complete Your Survey!")
            // on below line we are setting positive
            // button and setting text to it.
           // .setPositiveButton("Yes", dialogClickListener)
            // on below line we are setting negative button
            // and setting text to it.
            .setNegativeButton("Ok", dialogClickListener)
            // on below line we are calling
            // show to display our dialog.
            .show()

    }

}