package com.example.surveyapp.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.survey.ResponseItem
import com.example.surveyapp.activity.CustomerDetailActivity
import com.example.surveyapp.activity.SurveyQuestionsActivity
import com.example.surveyapp.databinding.MySurveylistBinding
import com.example.surveyapp.interfaces.OptionsListenerInterface
import com.example.surveyapp.utils.Dbhelper

class RecyclerAdapterSurveyList(var mList: ArrayList<ResponseItem>, val context : Context,val surveyIdListener : OptionsListenerInterface
) : RecyclerView.Adapter<RecyclerAdapterSurveyList.ViewHolder>() {

    lateinit var intent : Intent
    val dbhelper  = Dbhelper(context,null)

    inner class ViewHolder(val binding: MySurveylistBinding) : RecyclerView.ViewHolder(binding.root){}

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        val binding = MySurveylistBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        holder.binding.model = mList[position]


        //  intent.putExtra("M",mList[position].surveyId)
        //val surveyId = holder.binding.model.surveyId.toString()
        holder.binding.downloadImg.setOnClickListener {

          //  dbhelper.addSurvey(mList[position].surveyId.toString(),mList[position].name.toString(),"this will show after uploaded")

            val name = mList[position].name
            Toast.makeText(context, "$name Survey Downloaded ", Toast.LENGTH_SHORT).show()
        }
        holder.binding.nametext.setOnClickListener {

            surveyIdListener.onSurveyId(mList[position].surveyId.toString())

            intent = Intent(context, SurveyQuestionsActivity::class.java)
            //intent = Intent(context, CustomerDetailActivity::class.java)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.putExtra("surveyId",mList[position].surveyId.toString())
            intent.putExtra("surveyName",mList[position].name.toString())
           // Toast.makeText(context, "$surveyId", Toast.LENGTH_SHORT).show()
            context.startActivity(intent)
          //  intent = Intent(context, SurveyQuestionsActivity::class.java)
           /* intent.putExtra("id", mList[position].surveyId.toString())
            intent.putExtra("nid", mList[position].name.toString())
           context.startActivity(intent)*/
        }

    }

    override fun getItemCount(): Int {
        return mList.size
    }

}