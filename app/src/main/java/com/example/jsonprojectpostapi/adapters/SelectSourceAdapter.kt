package com.example.jsonprojectpostapi.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.jsonprojectpostapi.R
import com.example.jsonprojectpostapi.extensionclassses.UtilFunction
import com.example.jsonprojectpostapi.models.SourceListModel

class SelectSourceAdapter(var rspSourceList: List<SourceListModel>):RecyclerView.Adapter<SelectSourceAdapter.MyViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.chapters_layout, parent,false)
        return MyViewHolder(view)

    }

    override fun getItemCount(): Int {
        return rspSourceList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.sourceName.text = rspSourceList[position].qdsrc_title
      //  var count=0
        val count= UtilFunction.availableTotalQues(rspSourceList[position].question_counts)


/*
        if (rspSourceList[position].question_counts.size > 0){
            for (i in 0 until rspSourceList[position].question_counts.size){
                rspSourceList[position].question_counts[i].total?.let {
                    count= count +it.toInt()
                }
            }
        }
*/
        holder.totalQuestion.text = count.toString()+"Question"

    }

    class MyViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val sourceName:TextView=itemView.findViewById(R.id.tvChapterName)
        val totalQuestion:TextView=itemView.findViewById(R.id.tvTotalQuestion)

    }
}