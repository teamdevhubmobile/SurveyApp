package com.example.surveyapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.surveyapp.databinding.DownloadedQuestionItemInnerListBinding

class DownloadedOptionRecyclerAdapter(var mList: String, var context : Context
) : RecyclerView.Adapter<DownloadedOptionRecyclerAdapter.ViewHolder>() {

    private var selectedPosition = -1


    inner class ViewHolder(val binding: DownloadedQuestionItemInnerListBinding) :
        RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        val binding =
            DownloadedQuestionItemInnerListBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        mList[position]

        holder.binding.downloadedCheckbox.setOnClickListener {

            /* if (selectedPosition == position) {
                 holder.binding.checkbox.setChecked(true)

                 Toast.makeText(context,mList.get(position).name.toString(), Toast.LENGTH_SHORT).show()

             } else {
                 holder.binding.checkbox.setChecked(false)
             }*/

          /*  if (selectedPosition == position) {
                Toast.makeText(
                    context,
                    "" + holder.binding.checkbox.getText().toString(),
                    Toast.LENGTH_SHORT
                ).show()
            }

            Toast.makeText(
                context,
                "" + holder.binding.checkbox.getText().toString(),
                Toast.LENGTH_SHORT
            ).show()

*/        }
    }

    override fun getItemCount(): Int {
        return mList.length
    }
}
