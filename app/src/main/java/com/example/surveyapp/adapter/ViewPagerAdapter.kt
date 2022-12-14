package com.example.surveyapp.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import com.example.surveyapp.*
import com.example.surveyapp.interfaces.OpCheckListener
import com.example.surveyapp.interfaces.OptionsListenerInterface
import com.example.surveyapp.utils.Dbhelper

class ViewPagerAdapter(private val mContext: Context, private val itemList: ArrayList<ResponseItem>,val listener :OptionsListenerInterface,val listenerPosition :OpCheckListener ) : PagerAdapter() {
    private var layoutInflater: LayoutInflater? = null
    val dbhelper  = Dbhelper(mContext,null)
   // var submitList = arrayListOf<SubmitAnswersModel>()


    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = LayoutInflater.from(mContext)
        val view = layoutInflater!!.inflate(R.layout.item_questions_layout, container, false)
        val textview: TextView = view.findViewById(R.id.slide_screen_item_iv)
        val idtextview: TextView = view.findViewById(R.id.question_id)
        val recycler: RecyclerView = view.findViewById(R.id.inner_question_recyclerview)
        //textview.text = itemList[position].question.toString()
        textview.setText(HtmlCompat.fromHtml( itemList[position].question.toString(), HtmlCompat.FROM_HTML_MODE_COMPACT))
        idtextview.text = itemList[position].questionBankID+". ".toString()
       // Toast.makeText(mContext, "$textview", Toast.LENGTH_SHORT).show()



            recycler.adapter = OptionsRecyclerAdapter(itemList[position].option as ArrayList<OptionItem>, mContext,listener,listenerPosition,itemList[position].questionBankID.toString(),itemList[position].typeNumber.toString())

        val answerlist= arrayListOf<String>()

        answerlist.addAll(listOf(""+itemList[position].option as ArrayList<OptionItem>))

        container.addView(view as RelativeLayout)


/*

                dbhelper.addQuestion(
                    itemList[position].questionBankID.toString(),
                    itemList[position].question.toString(),
                    itemList[position].option as ArrayList<OptionItem>

                )
*/

     /*   try {
            container.addView(view, position)

        }catch (e:Exception){
            Log.e("TAG8009", "instantiateItem: $e", )
        }*/

            return view

    }

    override fun getCount(): Int {
        return itemList.size
    }



    fun getList() : ArrayList<ResponseItem>{

        return itemList
    }

    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view === obj as RelativeLayout
    }


    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
       // val view = `object` as View
        container.removeView(`object` as RelativeLayout)
    }


}