package com.example.jsonprojectpostapi.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.jsonprojectpostapi.R
import com.example.jsonprojectpostapi.extensionclassses.UtilFunction
import com.example.jsonprojectpostapi.models.ClassesModel

class SelectClassAdapter(var classList:List<ClassesModel> , var mListener:onItemClicklistener):RecyclerView.Adapter<SelectClassAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.class_layout, parent,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return classList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.cTitle.text=classList[position].class_title
       // var count=0
        val count= UtilFunction.availableTotalQues(classList[position].question_counts)
     /*   for (i in 0 until  classList[position].question_counts.size){
            classList[position].question_counts[i].total?.let {
              count = count + it.toInt()
               // count += it.toInt()
            }
        }*/
        holder.totalQuestion.text = count.toString()+ "Questions"
        holder.itemClick.setOnClickListener(){
            mListener.onItemClick(position,classList[position])

        }

    }
    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var cTitle:TextView=itemView.findViewById(R.id.tvClassTitle)
        var totalQuestion:TextView=itemView.findViewById(R.id.tvTotalQuestion)
        var itemClick: RelativeLayout=itemView.findViewById(R.id.relativeLayout_Class)

    }
    interface onItemClicklistener{
        fun onItemClick(position: Int, classesModel: ClassesModel)

    }

}