package com.example.surveyapp.interfaces

import com.example.surveyapp.OptionItem

interface OptionsListenerInterface {

    fun onOptionClick(item: OptionItem,qId : String)
}