package com.example.jsonprojectpostapi.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.jsonprojectpostapi.R
import com.example.jsonprojectpostapi.extensionclassses.UtilFunction
import com.example.jsonprojectpostapi.models.TopicsClassModel

class SelectTopicAdp(  var rspTopicList: List<TopicsClassModel>): RecyclerView.Adapter<SelectTopicAdp.MyViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.chapters_layout, parent,false)
        return MyViewHolder(view)

    }

    override fun getItemCount(): Int {
        return rspTopicList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.topicName.text = rspTopicList[position].ttitle
      //  var count= 0
        val count = UtilFunction.availableTotalQues(rspTopicList[position].question_counts)
/*
        if(rspTopicList[position].question_counts.size > 0){
            for (i in 0 until rspTopicList[position].question_counts.size){
                rspTopicList[position].question_counts[i].total?.let {
                    count=count+it.toInt()

                }

            }

        }
*/
        if(rspTopicList[position].tpid.equals("-1")){
            holder.totalQuestion.text= ""

        } else
        {
            holder.totalQuestion.text = count.toString()+"Question"


        }
        imageSelected(rspTopicList[position].isSelected,holder.ivSelected)

        holder.itemClick.setOnClickListener(){
            if(rspTopicList[position].isSelected){
                rspTopicList[position].isSelected=false
                imageSelected(rspTopicList[position].isSelected,holder.ivSelected)

            } else
            {
                rspTopicList[position].isSelected= true
                imageSelected(rspTopicList[position].isSelected,holder.ivSelected)

            }
            ////////////
            if(rspTopicList[position].tpid.equals("-1")){
                changeAllImg(rspTopicList[position].isSelected, rspTopicList)

            } else{
                if (rspTopicList[position].isSelected==false && rspTopicList[0].isSelected==true){
                 rspTopicList[0].isSelected= false
                    imageSelected(rspTopicList[0].isSelected,holder.ivSelected)
                    notifyDataSetChanged()

                }
            }


        }


    }



    class MyViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        var topicName:TextView= itemView.findViewById(R.id.tvChapterName)
        var totalQuestion:TextView=itemView.findViewById(R.id.tvTotalQuestion)
        var ivSelected: ImageView =itemView.findViewById(R.id.ivSelected)
        var itemClick:LinearLayout=itemView.findViewById(R.id.linearlayout_chapters)

    }
    fun imageSelected(selected: Boolean, ivSelected: ImageView) {
        if (selected){
            ivSelected.setImageResource(R.drawable.ic_path_item_selected)
        } else
        {
            ivSelected.setImageResource(R.drawable.ic_path_item_unselected)
        }

    }
    fun changeAllImg(selected: Boolean, rspTopicList: List<TopicsClassModel>) {
        for (i in 0 until rspTopicList.size){
            rspTopicList[i].isSelected= selected
        }
        notifyDataSetChanged()

    }

}