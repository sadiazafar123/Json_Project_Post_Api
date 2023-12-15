package com.example.jsonprojectpostapi.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.jsonprojectpostapi.R
import com.example.jsonprojectpostapi.extensionclassses.UtilFunction
import com.example.jsonprojectpostapi.models.SubjectClassModel

class SelectSubjectAdapter(var subjectList:  List<SubjectClassModel> ,var mListener2: onSubjectItemClickListener):RecyclerView.Adapter<SelectSubjectAdapter.MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.class_layout, parent,false)
        return MyViewHolder(view)

    }

    override fun getItemCount(): Int {
        return subjectList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.subjectTitle.text=subjectList[position].stitle
       // var count=0

        val count= UtilFunction.availableTotalQues(subjectList[position].question_counts)
/*
        if (subjectList[position].question_counts.size>0){
            for (i in 0 until subjectList[position].question_counts.size ){
                subjectList[position].question_counts[i].total?.let {
                    count= count+ it.toInt()
                }
            }

        }
*/
        holder.tQuestion.text=count.toString()+"Question"
        holder.itemClick.setOnClickListener(){
            mListener2.onItemClick(position,subjectList[position])

        }


    }
    class MyViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        var subjectTitle:TextView=itemView.findViewById(R.id.tvClassTitle)
        var tQuestion:TextView=itemView.findViewById(R.id.tvTotalQuestion)
        var itemClick:RelativeLayout=itemView.findViewById(R.id.relativeLayout_Class)

    }
    interface onSubjectItemClickListener{
        fun onItemClick(position: Int, subjectClassModel: SubjectClassModel)

    }



}