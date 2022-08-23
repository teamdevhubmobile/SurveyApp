package com.example.surveyapp.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.surveyapp.utils.Dbhelper
import com.example.surveyapp.databinding.DownloadedSurveyBinding


class DownloadedSurvey : Fragment() {

    lateinit var binding: DownloadedSurveyBinding

    @SuppressLint("Range")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DownloadedSurveyBinding.inflate(inflater,container,false)


        //val txtView : TextView = requireView().findViewById(R.id.ds_txt)

        val db = Dbhelper(requireContext(), null)
        val cursor = db.getName()

        cursor!!.moveToFirst()


        while(cursor.moveToNext()) {
            val Sno =  cursor.getString(cursor.getColumnIndex(Dbhelper.ID))
            val name =  cursor.getString(cursor.getColumnIndex(Dbhelper.SURVEYNAME))
            val id =  cursor.getString(cursor.getColumnIndex(Dbhelper.SURVEYID))

            binding.dsTxt.setText(Sno.toString()+" "+name.toString()+" "+id.toString())

//        val name =  cursor.getString(cursor.getColumnIndex(Dbhelper.SURVEYNAME))
         /*   var mylist = DownloadedModel(Sno, name,id)
            list.add(mylist)*/
        }
        cursor.close()

        return binding.root
    }
}