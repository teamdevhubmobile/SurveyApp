package com.example.surveyapp.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.data.response.DownloadedSurveyListModel
import com.example.surveyapp.activity.DownloadedCustomerDetailActivity
import com.example.surveyapp.activity.OnCLick
import com.example.surveyapp.apiservice.ApiInterface
import com.example.surveyapp.databinding.ItemTakeSurveyListBinding
import com.example.surveyapp.retrofitMain
import com.example.surveyapp.utils.Dbhelper

class TakeSurveyRecyclerAdapter(var mList: ArrayList<DownloadedSurveyListModel>, val context : AppCompatActivity, val OnItemCLick: OnCLick
) : RecyclerView.Adapter<TakeSurveyRecyclerAdapter.ViewHolder>() {

    lateinit var intent: Intent
    val dbhelper = Dbhelper(context, null)
    lateinit var apiClient: ApiInterface
    var mPosition = -1


    inner class ViewHolder(val binding: ItemTakeSurveyListBinding) :
        RecyclerView.ViewHolder(binding.root) {}

    fun updatePosition(position: Int, list: ArrayList<DownloadedSurveyListModel>) {

        // mList.clear()
        //this.mPosition = position
        this.mList = list

        this.notifyDataSetChanged()

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        val binding =
            ItemTakeSurveyListBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        apiClient = retrofitMain.create<ApiInterface>(ApiInterface::class.java)



        return ViewHolder(binding)
    }

    @SuppressLint("Range")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.binding.model = mList[position]

        Log.e("TAG656", "onBindViewHolder: ${mList[position].uploaded}",)

//        Log.e("TAG91", "onBindViewHolder: ${mList[position].uploaded.toString()}", )
//        Log.e("TAG91", "onBindViewHolder: ${mList[position].submit.toString()}", )


    /*    if (mList[position].submit == false) {

            holder.binding.uploadbtn.setBackgroundColor(Color.RED)
            holder.binding.uploadbtn.setText("Pending")

        } else if (mList[position].submit == true) {

            if (mList[position].uploaded == true) {

                holder.binding.uploadbtn.setBackgroundColor(Color.GREEN)
                holder.binding.uploadbtn.setText("Uploaded")
            } else {
                holder.binding.uploadbtn.setBackgroundColor(Color.BLUE)
                holder.binding.uploadbtn.setText("Upload")
            }


        } else if (mList[position].uploaded == true) {

            holder.binding.uploadbtn.setBackgroundColor(Color.GREEN)
            holder.binding.uploadbtn.setText("Uploaded")
        }


        holder.binding.uploadbtn.setOnClickListener {

            if (holder.binding.uploadbtn.text.equals("Upload")) {
                OnItemCLick.onClick(
                    mList[position].id.toInt(),
                    mList[position].Sno.toInt(),
                    position
                )
            }

            *//*  apiClient.getAnswersUploadPost()
                  .subscribeOn(Schedulers.io())
                  .subscribe ({ result ->
                      Log.e("getLocationUpdate", "success : "+ result )

                  }, { error ->
                      Log.e("getLocationUpdate", "error : "+ error.message )
                  })*//*

            *//* if(mList[position].uploaded == true){

                 holder.binding.uploadbtn.setBackgroundColor(Color.GREEN)
                 holder.binding.uploadbtn.setText("Uploaded")
             }*//*

            //  viewModel.getAnswerUploadPost( s1,"1",answermap,full_name,gender,phone,age,address)

        }*/

        holder.binding.takeSurveyBtn.setOnClickListener {

            if (holder.binding.takeSurveyBtn.text.equals("Take Survey")) {
                // val intent = Intent(context, DownloadedSurveyOuestionActivity::class.java)
                val intent = Intent(context, DownloadedCustomerDetailActivity::class.java)
                intent.putExtra("spid", mList[position].Sno)
                intent.putExtra("sid", mList[position].id)
                intent.putExtra("surveyName", mList[position].name)
                context.startActivity(intent)
                context.finish()
            }

        }

      /*  if (mPosition == position) {


            holder.binding.uploadbtn.setBackgroundColor(Color.GREEN)
            holder.binding.uploadbtn.setText("Uploaded")
        }
*/

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