package com.example.surveyapp.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.surveyapp.activity.DownloadedSurveyOuestionActivity
import com.example.surveyapp.databinding.DownloadedListBinding
import com.example.surveyapp.response.DownloadedSurveyListModel
import com.example.surveyapp.utils.Dbhelper

class DownloadedSurveyListRecyclerAdapter(var mList: ArrayList<DownloadedSurveyListModel>, val context : Context
) : RecyclerView.Adapter<DownloadedSurveyListRecyclerAdapter.ViewHolder>() {

    lateinit var intent: Intent
    val dbhelper = Dbhelper(context, null)

    inner class ViewHolder(val binding: DownloadedListBinding) :
        RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        val binding =
            DownloadedListBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    @SuppressLint("Range")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.model = mList[position]


        holder.binding.txt.setOnClickListener {

            val intent = Intent(context, DownloadedSurveyOuestionActivity::class.java)
            context.startActivity(intent)

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
        cursor.close()*/



        //holder.binding.txt.setText(dbhelper.addSurvey(mList[position].id.toString(),mList[position].name.toString(),"this will show after uploaded").toString())
        //holder.binding.txt.setText(""+id+" • "+name+"")

        // holder.binding.model = mList[position]
      /*  holder.binding.downloadImg.setOnClickListener {



        }*/
       /* holder.binding.nametext.setOnClickListener {


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
}

