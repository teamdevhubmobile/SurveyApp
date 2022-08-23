package com.example.surveyapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import com.example.surveyapp.R
import com.example.surveyapp.utils.Dbhelper2

class DownloadedQuestionViewPagerAdapter(private val mContext: Context, private val itemList: ArrayList<String> ) : PagerAdapter() {
    private var layoutInflater: LayoutInflater? = null
    val dbhelper  = Dbhelper2(mContext,null)


    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = LayoutInflater.from(mContext)
        val view = layoutInflater!!.inflate(R.layout.item_downloaded_question_layout, container, false)
        val textview: TextView = view.findViewById(R.id.downloaded_slide_screen_item_iv)
        val idtextview: TextView = view.findViewById(R.id.donwnloaded_question_id)
        val recycler: RecyclerView = view.findViewById(R.id.inner_downloaded_question_recyclerview)

        recycler.adapter = DownloadedOptionRecyclerAdapter(itemList[position]  ,mContext)

        /*  textview.text = itemList[position].question.toString()
          idtextview.text = itemList[position].questionBankID+". ".toString()
          Toast.makeText(mContext, "$textview", Toast.LENGTH_SHORT).show()

          itemList[position].option


          dbhelper.addQuestion(itemList[position].question.toString(),"",
              "","we will update it later")
  */

        container.addView(view, position)
        return view
    }

    override fun getCount(): Int {
        return itemList.size
    }

    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view === obj
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val view = `object` as View
        container.removeView(view)
    }
}