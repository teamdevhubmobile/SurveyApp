package com.example.surveyapp.activity

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.example.data.base.BaseActivity
import com.example.data.response.SubmitAnswersModel
import com.example.data.response.SurvIdModel
import com.example.surveyapp.*
import com.example.surveyapp.adapter.ViewPagerAdapter
import com.example.surveyapp.databinding.QuestionsActivityBinding
import com.example.surveyapp.interfaces.OptionsListenerInterface
import com.example.surveyapp.utils.Dbhelper
import okhttp3.MultipartBody
import java.io.File
import java.io.IOException

class SurveyQuestionsActivity : BaseActivity(),OptionsListenerInterface {

    lateinit var binding: QuestionsActivityBinding
    private lateinit var viewModel: HomeViewModel
    var id = ""
    var nid = ""
   // private lateinit var viewPager: ViewPager
     lateinit var mViewPagerAdapter: ViewPagerAdapter
    var textListt = ArrayList<OptionItem>()
    var survId = ""
    var qId = ""
    var answer = ""
    val dbhelper  = Dbhelper(this,null)
    var submitList = arrayListOf<SubmitAnswersModel>()
    var survIdList = arrayListOf<SurvIdModel>()
    var responseList = arrayListOf<ResponseItem>()
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
    var countDownTimer: CountDownTimer? = null
    var second = -1
    var minute:Int = 0
    var mRecorder: MediaRecorder? = null
    var mPlayer: MediaPlayer? = null
    var mFileName: String? = null
    val REQUEST_AUDIO_PERMISSION_CODE = 1
    var number = 0
    var audioList: ArrayList<MultipartBody.Part> = ArrayList<MultipartBody.Part>()
    var pathh = ""

    var pagenumber = 0




    val answr = arrayListOf<List<List<String>>>()

    private val galleryLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                val uri = it.data!!.data

              //  val model = SellsImage(uri!!.path)


            }
        }

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_survey_ouestions)

        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE)

        surveId = intent.getStringExtra("surveyId").toString()
        surveyName = intent.getStringExtra("surveyName").toString()

         s1 = sharedPreferences!!.getString("loginuserID","").toString()


        startRecording()


      //  Toast.makeText(this, "$s1", Toast.LENGTH_SHORT).show()

        /*if (btnpoint != 0){

           */
        /* btnpoint--
            binding.recordBtn.setImageDrawable(getDrawable(R.drawable.mic_microphone_icon))
            second = -1
            minute = 0
            stopTimer()
            binding.timerText.text="00:00"
            mRecorder!!.stop()
            //showImage()
            //playAudio()*//*

            autoStop()

        }else{

            binding.recordBtn.setImageDrawable(getDrawable(R.drawable.square_icon))
            startRecording()
            btnpoint++

        }
*/


/*
        binding.recordBtn.setOnClickListener {

            if (btnpoint != 0){

                btnpoint--
                binding.recordBtn.setImageDrawable(getDrawable(R.drawable.mic_microphone_icon))
                second = -1
                minute = 0
                stopTimer()
                binding.timerText.text="00:00"
                mRecorder!!.stop()
                //showImage()
                //playAudio()

            }else{

                binding.recordBtn.setImageDrawable(getDrawable(R.drawable.square_icon))
                startRecording()
                btnpoint++

            }


        }
*/




        binding.fabBtn.setOnClickListener {

            dbhelper.addTakenSurveyTable("","","","","","","","","","")

            var surveyExits =   dbhelper.addSurvey(surveId.toString(),surveyName.toString(),"this will show after uploaded",false,false)


            Log.e("TAGid00p", "onCreate: ${dbhelper.servayPId}")


            var spid = dbhelper.servayPId.toInt()

            if (!surveyExits){

                if (!responseList.isNullOrEmpty()) {


                    for (i in 0 until responseList.size){



                        dbhelper.addQuestion(

                            responseList.get(i).questionBankID.toString(),
                            responseList.get(i).question.toString(),
                            responseList.get(i).option as ArrayList<OptionItem>,

                            spid,surveId.toInt()

                        )
                    }

                }else {

                    Toast.makeText(this, "", Toast.LENGTH_SHORT).show()
                }
            }else{

                Toast.makeText(this, "Survey Already Exists", Toast.LENGTH_SHORT).show()
                
            }
        }


        binding.submitBtn.setOnClickListener {

            /*val answermap = hashMapOf<String, String>()


            var newSList = mViewPagerAdapter.getList()

            if (!newSList.isNullOrEmpty()){

                for (item in newSList){

                    var optionList = item.option

                    var answer =""
                    var opId =""
                    var opPosition =""

                    Log.e("TAGop", "onCreate: "+ item)

                    for (op in optionList!!){
                        Log.e("TAGop1", "onCreate: "+ op)
                        if (!op?.answers.isNullOrEmpty()){
                            answer = op?.answers.toString()
                            opId = op?.optionID.toString()
                            opPosition = op?.position.toString()

                            val data = intArrayOf(surveId.toInt()).contentToString()+intArrayOf(item.questionBankID!!.toInt()).contentToString()+intArrayOf(opPosition.toInt()).contentToString()
                            val answerData="answer$data"
                            answermap.put(answerData,opId.toString())


                        }

                    }
                  //  db.answerSubmit(item.questionBankID.toString(), answer,spid.toInt(),opId,opPosition)

                }

                Log.e("TAG5612", "onCreate: $answermap")

                var file: File = File(""+mFileName)
                var audio:MultipartBody.Part = getBodyFromAudioFile(file,"files")!!

                viewModel.getAnswerUploadPost( withBody(s1)!!,withBody("1")!!,answermap,withBody(full_name)!!,withBody(gender)!!,withBody(phone)!!,withBody(age)!!,withBody(address)!!,
                   audio )


            }else{
                Toast.makeText(this, "Select Answer", Toast.LENGTH_SHORT).show()
            }

            //Toast.makeText(this, "Submit Button Successfully", Toast.LENGTH_SHORT).show()


            var myList = mViewPagerAdapter.getList()

            for (item in myList){

                Toast.makeText(this, "$survId", Toast.LENGTH_SHORT).show()
                Log.e("TAG987", "onCreate: $item")
            }*/

            autoStop()
            submitApiCall()
            restartApp()

        }

        //viewPager = findViewById(R.id.viewPager)


        setObservers()
        viewModel.getShow(s1,"1")
        viewModel.getQuestions(intent.getStringExtra("surveyId").toString())


    }

   /* fun showImage() {

        galleryLauncher.launch(
            ImagePicker.with(this)
                .crop()
                .galleryMimeTypes( // no gif images at all
                    mimeTypes = arrayOf(
                        "/Recording1.3gp",
                        "/Recording2.3gp",
                        "/Recording3.3gp"
                    )
                )
                .createIntent()
        )
    }*/

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

    private fun setObservers() {
                observeQuestion()
                observerShow()
                observeUpdateQandA()
    }

    private fun observeQuestion() {

                viewModel.getQuestionsLiveData()
                    .observe(this@SurveyQuestionsActivity, Observer {

                        //showToast("succes")

                        if (it != null) {

                          //  Log.e("TAG", "this is it : $it")

                            responseList = it.response as ArrayList<ResponseItem>


                            // textListt =  it.response?.get() as ArrayList<OptionItem>

                            mViewPagerAdapter = ViewPagerAdapter(this@SurveyQuestionsActivity,   it.response as ArrayList<ResponseItem>,this )
                            binding.viewPager.pageMargin = 15
                            binding.viewPager.setPadding(50, 0, 50, 0);
                            binding.viewPager.setClipToPadding(false)
                            binding.viewPager.setPageMargin(25)
                            binding.viewPager.adapter = mViewPagerAdapter
                            binding.viewPager.addOnPageChangeListener(viewPagerPageChangeListener)

                            binding.nextBtn.setOnClickListener {
                                binding.viewPager.setCurrentItem(pagenumber+1, true)
                                Log.e("TAG453", "observeQuestion: $pagenumber", )

                            }

                            //textList =

/*


                            binding.downloadQuestionfab.setOnClickListener {

                            }
*/

                            // binding.questionsRecyclerview.adapter = RecyclerAdapterSurveyQuestions(it.response as ArrayList<ResponseItem>,this)
                        }

                    })
            }

    private fun observeUpdateQandA() {

        viewModel.getAnswerUploadLiveData()
            .observe(this@SurveyQuestionsActivity, Observer {

                if (it != null) {

                    Log.e("TAG", "this is iyt : $it")

                }

            })
    }

    private fun observerShow() {

        viewModel.getShowLiveData()
            .observe(this@SurveyQuestionsActivity, Observer {

                if (it != null) {

                  full_name =   it.userExamCheck?.fullName.toString()
                  gender =  it.userExamCheck?.gender.toString()
                   age =  it.userExamCheck?.age.toString()
                   phone =  it.userExamCheck?.phone.toString()
                    address = it.userExamCheck?.address.toString()

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


    override fun onOptionClick(item: OptionItem,qId: String) {

    var model = SubmitAnswersModel(item,qId)

    try {


        if (!submitList.isNullOrEmpty()) {

            for (i in 0 until submitList.size) {

                if (submitList.get(i).questionId.equals(qId)) {

                    Log.e("TAG77", "onOptionClick: " + submitList.get(i).questionId.equals(qId))

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

        Log.e("TAG444", "onOptionClick: $e")

    }

}

    override fun onSurveyId(surveyId: String) {

        var model = SurvIdModel(surveyId)
        survIdList.add(model)

        try {


            if (!survIdList.isNullOrEmpty()) {

                for (i in 0 until survIdList.size) {

                    if (survIdList.get(i).equals(survId)) {

                        Log.e("TAG77", "onOptionClick: " + survIdList.get(i).equals(survId))

                        survIdList.removeAt(i)
                        survIdList.add(model)
                        return
                    }

                }


                survIdList.add(model)


            } else {

                this.survId = surveyId
                survIdList.add(SurvIdModel(survId))

            }
        }catch (e:Exception){

            Log.e("TAG444", "onOptionClick: $e")

        }

    }

    private fun startRecording() {

        if (CheckPermissions()) {

            mFileName = application.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)!!.getAbsolutePath()

            if (number == 1) {
                mFileName += "/Recording1.mp3"
            }else if (number == 2){
                mFileName += "/Recording2.mp3"
            }else{
                mFileName += "/Recording3.mp3"
            }

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
                //binding.timerText.setText(recorderTime())
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

        Log.e("TAG*********", "playAudio: ************************** File Name ***********************${mFileName.toString()}" )
        try {
            // below method is used to set the
            // data source which will be our file name
            mPlayer!!.setDataSource(mFileName)

            // below method will prepare our media player
            mPlayer!!.prepare()

            // below method will start our media player.
            mPlayer!!.start()
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

    override fun onBackPressed() {

        //Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show()
        val dialogClickListener =
            DialogInterface.OnClickListener { dialog, which ->
                when (which) {
                    // on below line we are setting a click listener
                    // for our positive button
                    DialogInterface.BUTTON_POSITIVE -> {
                        // on below line we are displaying a toast message.
                      //  Toast.makeText(this, "Yes clicked", Toast.LENGTH_SHORT).show()
                        autoStop()
                        submitApiCall()
                        restartApp()


                    }

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
        builder.setMessage("Do you want to exit ?")
            // on below line we are setting positive
            // button and setting text to it.
            .setPositiveButton("Yes", dialogClickListener)
            // on below line we are setting negative button
            // and setting text to it.
            .setNegativeButton("No", dialogClickListener)
            // on below line we are calling
            // show to display our dialog.
            .show()
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

    fun submitApiCall(){

            val answermap = hashMapOf<String, String>()

            //*********//

            var newSList = mViewPagerAdapter.getList()

            if (!newSList.isNullOrEmpty()){

                for (item in newSList){

                    var optionList = item.option

                    var answer =""
                    var opId =""
                    var opPosition =""

                   // Log.e("TAGop", "onCreate: "+ item)

                    for (op in optionList!!){
                        Log.e("TAGop1", "onCreate: "+ op)
                        if (!op?.answers.isNullOrEmpty()){
                            answer = op?.answers.toString()
                            opId = op?.optionID.toString()
                            opPosition = op?.position.toString()

                            val data = intArrayOf(surveId.toInt()).contentToString()+intArrayOf(item.questionBankID!!.toInt()).contentToString()+intArrayOf(opPosition.toInt()).contentToString()
                            val answerData="answer$data"
                            answermap.put(answerData,opId.toString())


                        }

                    }
                    //  db.answerSubmit(item.questionBankID.toString(), answer,spid.toInt(),opId,opPosition)

                }

                Log.e("TAG5612", "onCreate: $answermap")

                var file: File = File(""+mFileName)
                var audio:MultipartBody.Part = getBodyFromAudioFile(file,"files")!!

                viewModel.getAnswerUploadPost( withBody(s1)!!,withBody("1")!!,answermap,withBody(full_name)!!,withBody(gender)!!,withBody(phone)!!,withBody(age)!!,withBody(address)!!,
                    audio )


            }else{
                Toast.makeText(this, "Select Answer", Toast.LENGTH_SHORT).show()
            }

            //************//
            //Toast.makeText(this, "Submit Button Successfully", Toast.LENGTH_SHORT).show()


            var myList = mViewPagerAdapter.getList()

            for (item in myList){

                Toast.makeText(this, "$survId", Toast.LENGTH_SHORT).show()
                Log.e("TAG987", "onCreate: $item")
            }

    }

}