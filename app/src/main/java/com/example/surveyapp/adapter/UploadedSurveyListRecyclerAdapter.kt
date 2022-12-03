package com.example.surveyapp.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.data.response.UploadedSurveyListResponseDataItem
import com.example.survey.ResponseItem
import com.example.surveyapp.activity.SurveyQuestionsActivity
import com.example.surveyapp.activity.UploadedQuestionActivity
import com.example.surveyapp.databinding.MySurveylistBinding
import com.example.surveyapp.databinding.UploadedSurveyItemListBinding
import com.example.surveyapp.interfaces.OptionsListenerInterface
import com.example.surveyapp.utils.Dbhelper

class UploadedSurveyListRecyclerAdapter(var mList: ArrayList<UploadedSurveyListResponseDataItem>, val context : Context
) : RecyclerView.Adapter<UploadedSurveyListRecyclerAdapter.ViewHolder>() {

    lateinit var intent : Intent
    val dbhelper  = Dbhelper(context,null)

    inner class ViewHolder(val binding: UploadedSurveyItemListBinding) : RecyclerView.ViewHolder(binding.root){}

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        val binding = UploadedSurveyItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        val model = mList[position]

        holder.binding.idText.setText(model.surveyIds)
        holder.binding.nametext.setText(model.survey_name+" "+model.surveyId)

        holder.binding.linear.setOnClickListener {

            val intent = Intent(context,UploadedQuestionActivity::class.java)
            intent.putExtra("id",model.surveyIds)
            context.startActivity(intent)

        }

    }

    override fun getItemCount(): Int {
        return mList.size
    }

}