package com.example.surveyapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.surveyapp.OptionItem
import com.example.surveyapp.databinding.QuestionItemInnerlistBinding
import com.example.surveyapp.interfaces.OptionsListenerInterface


class OptionsRecyclerAdapter(var mList: ArrayList<OptionItem>, var context : Context, val listener : OptionsListenerInterface, val qId : String
) : RecyclerView.Adapter<OptionsRecyclerAdapter.ViewHolder>() {

    private var selectedPosition = -1
    var checkedItem = ""


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

        holder.binding.checkbox.setOnClickListener { view ->
            selectedPosition = holder.adapterPosition


            listener.onOptionClick(mList[position],qId)
           // Toast.makeText(context, "${holder.binding.checkbox.text.toString()}", Toast.LENGTH_SHORT).show()

            notifyDataSetChanged()

        }

        if (selectedPosition==position){
            holder.binding.checkbox.setChecked(true)




        }
        else {
            holder.binding.checkbox.setChecked(false)
        }





//                if (selectedPosition == position) {
//                Toast.makeText(context, ""+holder.binding.checkbox.getText().toString(), Toast.LENGTH_SHORT).show()
//            }
//
//            Toast.makeText(context, ""+holder.binding.checkbox.getText().toString(), Toast.LENGTH_SHORT).show()


    }

    override fun getItemCount(): Int {
        return mList.size
    }

}