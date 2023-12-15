package com.example.jsonprojectpostapi.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.jsonprojectpostapi.R
import com.example.jsonprojectpostapi.models.PaperList

class PaperListAdapter (var paperList:List<PaperList>):RecyclerView.Adapter<PaperListAdapter.MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.cardview_layout,parent,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
         return paperList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.date.text = paperList[position].pdate
        holder.class_title.text = paperList[position].class_title
        holder.subject.text = paperList[position].stitle
        holder.chapters.text = paperList[position].chnos
    }
    class MyViewHolder(itemview:View):RecyclerView.ViewHolder(itemview){
        var date:TextView = itemview.findViewById(R.id.tvDate)
        var class_title:TextView = itemview.findViewById(R.id.tvClass)
        var subject:TextView = itemview.findViewById(R.id.tvSubject)
        var chapters:TextView = itemview.findViewById(R.id.tvChapters)

    }

}