package com.example.jsonprojectpostapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jsonprojectpostapi.adapters.PaperListAdapter
import com.example.jsonprojectpostapi.models.PaperList
import com.example.jsonprojectpostapi.models.RequestPaperList
import com.example.jsonprojectpostapi.retrofit.MainViewModel
import com.example.jsonprojectpostapi.retrofit.MyViewModelFactory
import com.example.jsonprojectpostapi.retrofit.Repository
import com.example.jsonprojectpostapi.retrofit.RetrofitClient

class MainActivity : AppCompatActivity() {
    lateinit var viewModel:  MainViewModel
    lateinit var recycler:RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recycler=findViewById(R.id.recyclerView)
          viewModel=ViewModelProvider(this,MyViewModelFactory(Repository(RetrofitClient))).
          get(MainViewModel::class.java)
          requestModel()
      //  viewModel.getPaperList(RequestPaperList)

    }

    fun requestModel(){
        val token:String="Bearer 15892|Jb9Ca6yIdf3m2it3tlgUdgQjXio7MfXylgQuoGPT"
        var requestModel = RequestPaperList("","","179","","10","0","",
            "","")
        viewModel.getPaperList(requestModel,token)
        viewModel.paperList.observe(this){
            Log.v("paperlist","ResponsePaperlist"+it.data.papers_list)
            val responsepaperList=it.data.papers_list
            adapterPaperList(responsepaperList)

        }

    }


    fun adapterPaperList(papersList: List<PaperList>) {
        var paperAdapter: PaperListAdapter= PaperListAdapter(papersList)
        recycler.layoutManager=LinearLayoutManager(this)
        recycler.adapter=paperAdapter




    }

}