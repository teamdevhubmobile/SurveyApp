package com.example.surveyapp.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.data.response.OptionCheckModel
import com.example.surveyapp.OptionItem
import com.example.surveyapp.databinding.QuestionItemInnerlistBinding
import com.example.surveyapp.interfaces.AnswerListCheck
import com.example.surveyapp.interfaces.OpCheckListener
import com.example.surveyapp.interfaces.OptionsListenerInterface
import com.example.surveyapp.utils.Dbhelper



class OptionsRecyclerAdapter(var mList: ArrayList<OptionItem>, var context : Context, val listener : OptionsListenerInterface, val listenerPosition : OpCheckListener, val qId : String,
                             val type : String, val oftype : String, val answerListCheckListener: AnswerListCheck
) : RecyclerView.Adapter<OptionsRecyclerAdapter.ViewHolder>() {

    private var selectedPosition = -1
    var checkedItem = ""
    var ans = arrayOf<String>()
    var pos = arrayOf<String>()
    var typee = arrayListOf<String>()
    var answerListCheck = arrayListOf<OptionCheckModel>()
    inner class ViewHolder(val binding: QuestionItemInnerlistBinding) : RecyclerView.ViewHolder(binding.root){}

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        val binding = QuestionItemInnerlistBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.binding.model =mList[position]


        //  getSelectType()
        
      /*  holder.binding.checkbox.setOnCheckedChangeListener { compoundButton, b ->

            selectedPosition = holder.adapterPosition

        }
            if (selectedPosition == position ) {

                holder.binding.checkbox.setChecked(true)
                val checked: String = holder.binding.checkbox.getText().toString()

               // Toast.makeText(context,mList.get(position).getDescription().toString(), Toast.LENGTH_SHORT).show()
                //textView.setText(myLists.get(position).getDescription().toString())
            } else {
                holder.binding.checkbox.setChecked(false)
            }*/

        Log.e("TAGCheck", "onBindViewHolder: $typee", )



        if (type.equals("1") || oftype.equals("1")){

            holder.binding.checkbox.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) {

                    if (!answerListCheck.isNullOrEmpty()){
                        answerListCheck.clear()
                    }
                    selectedPosition = position

                    ans = arrayOf(mList[position].name.toString())
                    pos =  arrayOf(position.toString())

                    Log.e("TAG101", "onBindViewHolder: $ans ======== $pos", )

                    /*mList[position].answers = mList[position].name
                    mList[position].position = position
  */
                    mList[position].answers = ans.toString()
                    mList[position].position = pos.size

                    answerListCheck.add(OptionCheckModel(mList[position].optionID!!.toInt(),position))

                    answerListCheckListener.ansListCheck(answerListCheck)

                    listener.onOptionClick(mList[position],qId)

                    //  listenerPosition.opCheckListener(position)

                   // Toast.makeText(context, "1", Toast.LENGTH_SHORT).show()

                    // Toast.makeText(context, "${holder.binding.checkbox.text.toString()}", Toast.LENGTH_SHORT).show()

                    //  }

                    notifyDataSetChanged()

                }

            }


            if (selectedPosition==position){
                holder.binding.checkbox.setChecked(true)

            }
            else {

                holder.binding.checkbox.setChecked(false)

            }

        }else if (type.equals("2") || oftype.equals("2")){

            holder.binding.checkbox.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) {

                    //   selectedPosition = position

                    mList[position].answers = mList[position].name
                    mList[position].position = position

                    var opModel =  OptionCheckModel(mList[position].optionID!!.toInt(), position)

                    if (answerListCheck.isNullOrEmpty()){

                        answerListCheck.add(opModel)
                    }else{

                        if (!answerListCheck.contains(opModel)){
                            answerListCheck.add(opModel)
                        }
                    }


                    Log.e("TAGCheckList", "onBindViewHolder: ${answerListCheck.size}",)

                    answerListCheckListener.ansListCheck(answerListCheck)

                    listener.onOptionClick(mList[position], qId)
                    // listenerPosition.opCheckListener(position)
                    // Toast.makeText(context, "${holder.binding.checkbox.text.toString()}", Toast.LENGTH_SHORT).show()
                    //Toast.makeText(context, "2", Toast.LENGTH_SHORT).show()

                    //   }else if(!type.equals("2")){

                    /*  selectedPosition = holder.adapterPosition

                  mList[position].answers = mList[position].name
                  mList[position].position = position

                  listener.onOptionClick(mList[position],qId)

                //  listenerPosition.opCheckListener(position)

                  Toast.makeText(context, "1", Toast.LENGTH_SHORT).show()


                  // Toast.makeText(context, "${holder.binding.checkbox.text.toString()}", Toast.LENGTH_SHORT).show()

            //  }

              notifyDataSetChanged()*/
                }else{

                    mList[position].answers = mList[position].name
                    mList[position].position = position

                    var opModel =  OptionCheckModel(mList[position].optionID!!.toInt(), position)

                    if (!answerListCheck.isNullOrEmpty()){

                        if (answerListCheck.contains(opModel)){
                            answerListCheck.remove(opModel)
                        }
                    }

                    answerListCheckListener.ansListCheck(answerListCheck)

                }
            }
        }



    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount(): Int {
        return mList.size
    }

  /*  @SuppressLint("Range")
    fun getSelectType(){

            val db = Dbhelper(context, null)



            val cursor = db.getQuestion(2)

            cursor!!.moveToFirst()

        val type = cursor.getString(cursor.getColumnIndex(Dbhelper.OPTIONCHECKTYPE))
        val id2 = cursor.getString(cursor.getColumnIndex(Dbhelper.ID))

         typee.add(type)

        Log.e("TAG1112101", "getSelectType: $typee" )
        Log.e("TAGm", "getSelectType: ${id2 +"=="+ typee}" )

        while (cursor.moveToNext()) {

            val type = cursor.getString(cursor.getColumnIndex(Dbhelper.OPTIONCHECKTYPE))
            val id = cursor.getString(cursor.getColumnIndex(Dbhelper.ID))


            typee.add(type)
            Log.e("TAGm", "getSelectType: ${id +"=="+ typee}" )

        }

        Log.e("TAG1112101", "getSelectType: $typee" )
        Log.e("TAG1112101", "getSelectType: ${typee.size}" )


    }*/

}