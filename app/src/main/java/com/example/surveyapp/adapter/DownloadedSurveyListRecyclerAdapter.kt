package com.example.surveyapp.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.data.response.AnswerSequenceModel
import com.example.surveyapp.activity.DownloadedSurveyOuestionActivity
import com.example.surveyapp.databinding.DownloadedListBinding
import com.example.data.response.DownloadedSurveyListModel
import com.example.data.response.TakenSurveyModel
import com.example.surveyapp.activity.DownloadedCustomerDetailActivity
import com.example.surveyapp.activity.OnCLick
import com.example.surveyapp.apiservice.ApiInterface
import com.example.surveyapp.interfaces.TakenSuveyClick
import com.example.surveyapp.retrofitMain
import com.example.surveyapp.utils.Dbhelper
import io.reactivex.schedulers.Schedulers
import okhttp3.MultipartBody
import okhttp3.internal.notify
import okhttp3.internal.notifyAll
import java.io.File

class DownloadedSurveyListRecyclerAdapter(var mList: ArrayList<TakenSurveyModel>, val context : AppCompatActivity, val OnItemCLick: OnCLick, var click: TakenSuveyClick
) : RecyclerView.Adapter<DownloadedSurveyListRecyclerAdapter.ViewHolder>() {

    lateinit var intent: Intent
    val dbhelper = Dbhelper(context, null)
    lateinit var apiClient: ApiInterface
    var mPosition = -1
    val anslist = arrayListOf<AnswerSequenceModel>()

    var newlist = arrayListOf<String>()



    inner class ViewHolder(val binding: DownloadedListBinding) :
        RecyclerView.ViewHolder(binding.root) {}

    fun updatePosition(position: Int,list:ArrayList<TakenSurveyModel>){

        this.mList = list

        this.notifyDataSetChanged()

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        val binding =
            DownloadedListBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        apiClient = retrofitMain.create<ApiInterface>(ApiInterface::class.java)



        return ViewHolder(binding)
    }

    @SuppressLint("Range")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.binding.model = mList[position]

      //  Log.e("TAG656", "onBindViewHolder: ${ mList[position].uploaded}", )

//        Log.e("TAG91", "onBindViewHolder: ${mList[position].uploaded.toString()}", )
//        Log.e("TAG91", "onBindViewHolder: ${mList[position].submit.toString()}", )

/*
        if (mList[position].submit == false){

            holder.binding.uploadbtn.setBackgroundColor(Color.RED)
            holder.binding.uploadbtn.setText("Pending")

        }else if (mList[position].submit == true){

            if(mList[position].uploaded == true) {

                holder.binding.uploadbtn.setBackgroundColor(Color.GREEN)
                holder.binding.uploadbtn.setText("Uploaded")
            }else{
                holder.binding.uploadbtn.setBackgroundColor(Color.BLUE)
                holder.binding.uploadbtn.setText("Upload")
            }


        }else if(mList[position].uploaded == true){

            holder.binding.uploadbtn.setBackgroundColor(Color.GREEN)
            holder.binding.uploadbtn.setText("Uploaded")
        }*/


        holder.binding.txt.setText(mList[position].username.toString())

        holder.binding.uploadbtn.setOnClickListener {

           // getAnswerSequence(mList[position].id)
            click.onBtnClick(true,mList[position].id,mList[position].Sno)



            if (holder.binding.uploadbtn.text.equals("Upload")) {
                OnItemCLick.onClick(mList[position].id.toInt(), mList[position].Sno.toInt(),position)
            }

          /*  apiClient.getAnswersUploadPost()
                .subscribeOn(Schedulers.io())
                .subscribe ({ result ->
                    Log.e("getLocationUpdate", "success : "+ result )

                }, { error ->
                    Log.e("getLocationUpdate", "error : "+ error.message )
                })*/

           /* if(mList[position].uploaded == true){

                holder.binding.uploadbtn.setBackgroundColor(Color.GREEN)
                holder.binding.uploadbtn.setText("Uploaded")
            }*/

          //  viewModel.getAnswerUploadPost( s1,"1",answermap,full_name,gender,phone,age,address)

        }

       /* holder.binding.txt.setOnClickListener {

            if (holder.binding.uploadbtn.text.equals("Pending")) {
               // val intent = Intent(context, DownloadedSurveyOuestionActivity::class.java)
                val intent = Intent(context, DownloadedCustomerDetailActivity::class.java)
                intent.putExtra("spid", mList[position].Sno)
                intent.putExtra("sid", mList[position].id)
                context.startActivity(intent)
                context.finish()
            }

        }*/

        if (mPosition == position ){


            holder.binding.uploadbtn.setBackgroundColor(Color.GREEN)
            holder.binding.uploadbtn.setText("Uploaded")
        }


/*
        val cursor = dbhelper.getName()
        cursor!!.moveToFirst()

        val id = cursor.getString(cursor.getColumnIndex(Dbhelper.ID))
        val name = cursor.getString(cursor.getColumnIndex(Dbhelper.SURVEYNAME))
        // = holder.binding.txt.append())
      // val name =  holder.binding.txt.append(cursor.getString(cursor.getColumnIndex(Dbhelper.SURVEYNAME)))

        while(cursor.moveToNext()) {
            //binding.txt.append(cursor.getString(cursor.getColumnIndex(Dbhelper.SURVEYNAME)) + "\n")

        }
        cursor.close()



        //holder.binding.txt.setText(dbhelper.addSurvey(mList[position].id.toString(),mList[position].name.toString(),"this will show after uploaded").toString())
        //holder.binding.txt.setText(""+id+" • "+name+"")

        // holder.binding.model = mList[position]
        holder.binding.downloadImg.setOnClickListener {



        }
        holder.binding.nametext.setOnClickListener {


            intent = Intent(context, SurveyQuestionsActivity::class.java)
            intent.putExtra("id", mList[position].surveyId.toString())
            intent.putExtra("nid", mList[position].name.toString())
            context.startActivity(intent)
        }
*/

    }

    override fun getItemCount(): Int {
        return mList.size
    }

    @SuppressLint("Range")
    fun getAnswerSequence(sid : String,surveyid : String) {

        try {
            val db = Dbhelper(context, null)


            val cursor = db.getTakenSurvey()

            cursor!!.moveToFirst()

            val Sno = cursor.getString(cursor.getColumnIndex(Dbhelper.ID))
            val surveyIDcheck = cursor.getString(cursor.getColumnIndex(Dbhelper.SURVEYID))
            val surveyPrimary = cursor.getString(cursor.getColumnIndex(Dbhelper.SURVEYPRIMARI))
            val questionId = cursor.getString(cursor.getColumnIndex(Dbhelper.QUESTIONID))
            val answer = cursor.getString(cursor.getColumnIndex(Dbhelper.ANSWER))
            val optionId = cursor.getString(cursor.getColumnIndex(Dbhelper.OPID))
            val optionPosition = cursor.getString(cursor.getColumnIndex(Dbhelper.OPCHECK))

            val mylist = AnswerSequenceModel(surveyIDcheck, questionId, optionPosition, optionId)

            anslist.add(mylist)

            while (cursor.moveToNext()) {
                val Sno = cursor.getString(cursor.getColumnIndex(Dbhelper.ID))
                val surveyIDcheck = cursor.getString(cursor.getColumnIndex(Dbhelper.SURVEYID))
                val surveyPrimary = cursor.getString(cursor.getColumnIndex(Dbhelper.SURVEYPRIMARI))
                val questionId = cursor.getString(cursor.getColumnIndex(Dbhelper.QUESTIONID))
                val answer = cursor.getString(cursor.getColumnIndex(Dbhelper.ANSWER))
                val optionId = cursor.getString(cursor.getColumnIndex(Dbhelper.OPID))
                val optionPosition = cursor.getString(cursor.getColumnIndex(Dbhelper.OPCHECK))

                val mylist =
                    AnswerSequenceModel(surveyIDcheck, questionId, optionPosition, optionId)

                anslist.add(mylist)


            }

            cursor.close()
            click.onBtnClick(true,sid,surveyid)

        } catch (e: Exception) {

            Log.e("TAG11", "getAnswerSequence: ${e.message}", )

        }
    }
}

