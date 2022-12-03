package com.example.surveyapp.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import com.example.data.response.Questions
import com.example.data.response.UploadedQAModel
import com.example.data.response.UploadedSurveyQuestionResponseResponse
import com.example.survey.ResponseItem
import com.example.surveyapp.*
import com.example.surveyapp.activity.SurveyQuestionsActivity
import com.example.surveyapp.databinding.MySurveylistBinding
import com.example.surveyapp.databinding.UploadedSurveyQuestionItemList
import com.example.surveyapp.interfaces.OptionsListenerInterface
import com.example.surveyapp.utils.Dbhelper

class UploadedQuestionRecyclerAdapter(
    var mList: ArrayList<UploadedQAModel>, val context: Context
) : RecyclerView.Adapter<UploadedQuestionRecyclerAdapter.ViewHolder>() {

    lateinit var intent : Intent
    val dbhelper  = Dbhelper(context,null)

    inner class ViewHolder(val binding: UploadedSurveyQuestionItemList) : RecyclerView.ViewHolder(binding.root){}

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        val binding = UploadedSurveyQuestionItemList.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        val model = mList[position]

        holder.binding.questionText.setText(HtmlCompat.fromHtml(model.question.toString(), HtmlCompat.FROM_HTML_MODE_COMPACT))
        holder.binding.answerText.setText(HtmlCompat.fromHtml(model.answer.toString(), HtmlCompat.FROM_HTML_MODE_COMPACT))

    }

    override fun getItemCount(): Int {
        return mList.size
    }

}