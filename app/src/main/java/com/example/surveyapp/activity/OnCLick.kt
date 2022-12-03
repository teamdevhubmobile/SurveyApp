package com.example.surveyapp.activity

interface OnCLick {

    fun onClick(sId:Int,spId:Int,position : Int)
    fun onListClick(sId:Int,spId:Int)

}