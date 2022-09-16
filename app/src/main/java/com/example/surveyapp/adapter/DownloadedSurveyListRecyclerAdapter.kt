package com.example.surveyapp.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.surveyapp.activity.DownloadedSurveyOuestionActivity
import com.example.surveyapp.databinding.DownloadedListBinding
import com.example.data.response.DownloadedSurveyListModel
import com.example.surveyapp.activity.OnCLick
import com.example.surveyapp.utils.Dbhelper

class DownloadedSurveyListRecyclerAdapter(var mList: ArrayList<DownloadedSurveyListModel>, val context : Context, val OnItemCLick: OnCLick
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


        Log.e("TAG91", "onBindViewHolder: ${mList[position].uploaded.toString()}", )
        Log.e("TAG91", "onBindViewHolder: ${mList[position].submit.toString()}", )


        if (mList[position].submit == false){

            holder.binding.uploadbtn.setBackgroundColor(Color.RED)
            holder.binding.uploadbtn.setText("Pending")

        }else if (mList[position].submit == true){

            holder.binding.uploadbtn.setBackgroundColor(Color.BLUE)
            holder.binding.uploadbtn.setText("Upload")

        }else {

            holder.binding.uploadbtn.setBackgroundColor(Color.GREEN)
            holder.binding.uploadbtn.setText("Uploaded")
        }


        holder.binding.uploadbtn.setOnClickListener {

            if (holder.binding.uploadbtn.text.equals("Upload")) {
                OnItemCLick.onClick(mList[position].id.toInt(), mList[position].Sno.toInt())
            }
          //  viewModel.getAnswerUploadPost( s1,"1",answermap,full_name,gender,phone,age,address)

        }

        holder.binding.txt.setOnClickListener {

            if (holder.binding.uploadbtn.text.equals("Pending")) {
                val intent = Intent(context, DownloadedSurveyOuestionActivity::class.java)
                intent.putExtra("spid", mList[position].Sno)
                intent.putExtra("sid", mList[position].id)
                context.startActivity(intent)
            }

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

