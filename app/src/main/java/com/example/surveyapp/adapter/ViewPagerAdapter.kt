package com.example.surveyapp.adapter

import android.annotation.SuppressLint
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
import com.example.data.response.OptionCheckModel
import com.example.surveyapp.*
import com.example.surveyapp.interfaces.AnswerListCheck
import com.example.surveyapp.interfaces.OpCheckListener
import com.example.surveyapp.interfaces.OptionsListenerInterface
import com.example.surveyapp.utils.Dbhelper
import java.lang.Exception

class ViewPagerAdapter(private val mContext: Context, private val itemList: ArrayList<ResponseItem>,val listener :OptionsListenerInterface,val listenerPosition :OpCheckListener,val surveyPrimary : String ) : PagerAdapter() {
    private var layoutInflater: LayoutInflater? = null
    val dbhelper  = Dbhelper(mContext,null)
   // var submitList = arrayListOf<SubmitAnswersModel>()
   var typee = arrayListOf<String>()



    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = LayoutInflater.from(mContext)
        val view = layoutInflater!!.inflate(R.layout.item_questions_layout, container, false)
        val textview: TextView = view.findViewById(R.id.slide_screen_item_iv)
        val idtextview: TextView = view.findViewById(R.id.question_id)
        val recycler: RecyclerView = view.findViewById(R.id.inner_question_recyclerview)
        //textview.text = itemList[position].question.toString()
        textview.setText(HtmlCompat.fromHtml( itemList[position].question.toString(), HtmlCompat.FROM_HTML_MODE_COMPACT))
        idtextview.text = itemList[position].questionBankID+". ".toString()

        try {
            getSelectType()
        }catch (e:Exception){

        }


        if (typee.isEmpty()){

            recycler.adapter = OptionsRecyclerAdapter(
                itemList[position].option as ArrayList<OptionItem>,
                mContext,
                listener,
                listenerPosition,
                itemList[position].questionBankID.toString(),
                itemList[position].typeNumber.toString(),
                "",object : AnswerListCheck{
                    override fun ansListCheck(anslist: ArrayList<OptionCheckModel>) {
                        itemList[position].ansListCheck = anslist
                    }

                })

        }else {

            recycler.adapter = OptionsRecyclerAdapter(
                itemList[position].option as ArrayList<OptionItem>,
                mContext,
                listener,
                listenerPosition,
                itemList[position].questionBankID.toString(),
                itemList[position].typeNumber.toString(),
                typee[position].toString()
                ,object : AnswerListCheck{
                    override fun ansListCheck(anslist: ArrayList<OptionCheckModel>) {
                        itemList[position].ansListCheck = anslist
                    }

                })
        }
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

    @SuppressLint("Range")
    fun getSelectType() {


        val db = Dbhelper(mContext, null)

        val cursor = db.getQuestion(surveyPrimary.toInt())

        if (cursor == null) {

            Log.e("TAG12", "getSelectType: ${"mdk"}",)

        } else {


            cursor!!.moveToFirst()

            val type = cursor.getString(cursor.getColumnIndex(Dbhelper.OPTIONCHECKTYPE))
            //val id2 = cursor.getString(cursor.getColumnIndex(Dbhelper.ID))

            typee.add(type)

            Log.e("TAG1112101", "getSelectType: $typee")
            //Log.e("TAGm", "getSelectType: ${id2 + "==" + typee}")

            while (cursor.moveToNext()) {

                val type = cursor.getString(cursor.getColumnIndex(Dbhelper.OPTIONCHECKTYPE))
              //  val id = cursor.getString(cursor.getColumnIndex(Dbhelper.ID))


                typee.add(type)
                //Log.e("TAGm", "getSelectType: ${id + "==" + typee}")

            }

            Log.e("TAG1112101", "getSelectType: $typee")
            Log.e("TAG1112101", "getSelectType: ${typee.size}")


        }
    }

}