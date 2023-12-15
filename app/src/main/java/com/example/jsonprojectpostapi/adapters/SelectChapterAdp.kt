package com.example.jsonprojectpostapi.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.jsonprojectpostapi.R
import com.example.jsonprojectpostapi.extensionclassses.UtilFunction
import com.example.jsonprojectpostapi.models.ChapterClassModel

class SelectChapterAdp(var responseChapterlist: List<ChapterClassModel>): RecyclerView.Adapter<SelectChapterAdp.MyViewHolder>() {
       override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
           val view= LayoutInflater.from(parent.context).inflate(R.layout.chapters_layout, parent,false)
           return MyViewHolder(view)

    }

    override fun getItemCount(): Int {
        return responseChapterlist.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.chapterName.text = responseChapterlist[position].ctitle
       // var count= 0
        val count= UtilFunction.availableTotalQues(responseChapterlist[position].question_counts)
/*
        if (responseChapterlist[position].question_counts.size>0){
            for (i in 0 until responseChapterlist[position].question_counts.size){
                responseChapterlist[position].question_counts[i].total?.let {
                    count= count+it.toInt()

                }
            }
        }
*/
        //

        //notifyItemChanged(position)
        if (responseChapterlist[position].bkdid .equals("-1"))
        {
            holder.totalQuestion.text ="";
        }
        else
        {
            holder.totalQuestion.text = count.toString()+" Question"
        }


         imageSelected(responseChapterlist[position].isSelected , holder.ivSelected )

        holder.itemClick.setOnClickListener(){
            if (responseChapterlist[position].isSelected){
                responseChapterlist[position].isSelected = false
                imageSelected(responseChapterlist[position].isSelected , holder.ivSelected)

            } else{
                responseChapterlist[position].isSelected = true
                imageSelected(responseChapterlist[position].isSelected,holder.ivSelected)

            }


            if (responseChapterlist[position].bkdid.equals("-1")){
                Log.v("imageSelected"," allselected value"+responseChapterlist[position].isSelected )


                changeAll(responseChapterlist[position].isSelected , responseChapterlist)
            } else{
                if(responseChapterlist[position].isSelected== false && responseChapterlist[0].isSelected==true)
                {
                    responseChapterlist[0].isSelected = false
                    imageSelected(responseChapterlist[0].isSelected , holder.ivSelected)
                    notifyDataSetChanged()


                }

               // responseChapterlist[position].isSelected = false
               // imageSelected(responseChapterlist[position].isSelected , holder.ivSelected)


            }

        }






    }



    class MyViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){
        var chapterName: TextView = itemView.findViewById(R.id.tvChapterName)
        var totalQuestion: TextView = itemView.findViewById(R.id.tvTotalQuestion)
        var ivSelected: ImageView = itemView.findViewById(R.id.ivSelected)
        var itemClick: LinearLayout = itemView.findViewById(R.id.linearlayout_chapters)

    }
    fun imageSelected(selected: Boolean, ivSelected: ImageView) {
      //  Log.v("imageSelected","selected value"+selected)
        if (selected){

            ivSelected.setImageResource(R.drawable.ic_path_item_selected)
        } else{
            ivSelected.setImageResource(R.drawable.ic_path_item_unselected)
        }

    }

    private fun changeAll(selected: Boolean, responseChapterlist: List<ChapterClassModel>) {
       // Log.v("changeAll"," changeAll value"+selected )
        for (i in 0 until responseChapterlist.size){
        responseChapterlist[i].isSelected = selected
           // Log.v("changeAll"," changeAll value"+responseChapterlist[i].isSelected )

    }
        notifyDataSetChanged()
    }
}