package com.example.surveyapp.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import com.example.surveyapp.*
import com.example.surveyapp.interfaces.OptionsListenerInterface
import com.example.surveyapp.utils.Dbhelper

class ViewPagerAdapter(private val mContext: Context, private val itemList: ArrayList<ResponseItem>,val listener :OptionsListenerInterface ) : PagerAdapter() {
    private var layoutInflater: LayoutInflater? = null
    val dbhelper  = Dbhelper(mContext,null)


    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = LayoutInflater.from(mContext)
        val view = layoutInflater!!.inflate(R.layout.item_questions_layout, container, false)
        val textview: TextView = view.findViewById(R.id.slide_screen_item_iv)
        val idtextview: TextView = view.findViewById(R.id.question_id)
        val recycler: RecyclerView = view.findViewById(R.id.inner_question_recyclerview)
        textview.text = itemList[position].question.toString()
        idtextview.text = itemList[position].questionBankID+". ".toString()
        Toast.makeText(mContext, "$textview", Toast.LENGTH_SHORT).show()

        itemList[position].option


            recycler.adapter =
                OptionsRecyclerAdapter(itemList[position].option as ArrayList<OptionItem>, mContext,listener,itemList[position].questionBankID.toString())


                dbhelper.addQuestion(
                    itemList[position].questionBankID.toString(),
                    itemList[position].question.toString(),
                    itemList[position].option as ArrayList<OptionItem>

                )

        try {


            container.addView(view, position)
            }catch (e:Exception){
            Log.e("TAG234324", "instantiateItem: $e", )
            }
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