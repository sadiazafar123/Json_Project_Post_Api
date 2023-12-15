package com.example.jsonprojectpostapi.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jsonprojectpostapi.R
import com.example.jsonprojectpostapi.adapters.SelectTopicAdp
import com.example.jsonprojectpostapi.extensionclassses.UtilFunction
import com.example.jsonprojectpostapi.extensionclassses.UtilFunction.Companion.token
import com.example.jsonprojectpostapi.models.PostData
import com.example.jsonprojectpostapi.models.TopicsClassModel
import com.example.jsonprojectpostapi.retrofit.MainViewModel
import com.example.jsonprojectpostapi.retrofit.MyViewModelFactory
import com.example.jsonprojectpostapi.retrofit.Repository
import com.example.jsonprojectpostapi.retrofit.RetrofitClient
import com.google.gson.Gson


class SelectTopicFragment : Fragment() {
    lateinit var recyclerView: RecyclerView
    lateinit var viewModel : MainViewModel
    lateinit var bookId: String
    var chapterList = mutableListOf<String>()
    lateinit var rspTopiclist2: List<TopicsClassModel>
    lateinit var btnNext:AppCompatButton
    val rspTopicList = ArrayList<TopicsClassModel>()
    lateinit var navController:NavController
    lateinit var tvTopicTotal:TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_select_topic, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvTopicTotal= view.findViewById(R.id.tvTopicTotal)

        btnNext= view.findViewById(R.id.btn_Next)
        navController = Navigation.findNavController(requireActivity(),R.id.fragment_container_view)
        recyclerView = view.findViewById(R.id.recyclerView_topics)
        viewModel= ViewModelProvider(this, MyViewModelFactory(Repository(RetrofitClient))).get(MainViewModel::class.java)
        val arg2 = SelectTopicFragmentArgs.fromBundle(requireArguments())
        //book id
        bookId= arg2.bookId!!
        //chapter id
        chapterList = arg2.chaptersId.toMutableList()
      //  Log.v("chapterList","chapterList"+bookId)
     Log.v("chapterList","chapterListreceive"+ Gson().toJson(chapterList))
       // Log.v("chapterList","chapterList"+chapterList)
        requestTopicList()
        buttonClick()



    }
    fun buttonClick(){
        btnNext.setOnClickListener(){
            var clickList= mutableListOf<String>()

            for (i in 0 until rspTopicList.size){

                if (rspTopicList[i].isSelected==true && rspTopicList[i].tpid.equals("-1")){
                    clickList.add(rspTopicList[i].tpid.toString())
                }
                else if(rspTopicList[i].isSelected)

                {

                    clickList.add(rspTopicList[i].tpid.toString())
                }
            }
            navController.navigate(SelectTopicFragmentDirections.
            actionSelectTopicFragmentToSelectSourceFragment(bookId, chapterList.toTypedArray(),clickList.toTypedArray()))


        }
    }
    fun requestTopicList(){
        var chapterid = StringBuilder()
        val postData = PostData.availableQuestionPost()

        for (i in 0 until chapterList.size)
        {
            if (chapterid.isEmpty())
            {
                chapterid = chapterid.append(chapterList[i])
                Log.v("arrayvalues","append arrayvalues"+ Gson().toJson(chapterid))

            }
            else
            {

                chapterid.append(",").append(chapterList[i])
            }
        }
        //api calling for available total topic

        postData.book_id = bookId
        postData.chapter_id = chapterid.toString()
        viewModel.getTopicTotal(token,postData)
        viewModel.topicAvailableTotal.observe(viewLifecycleOwner){
            if (it.code==200){
                val resTopicTotal= it.data.question_counts
                val count= UtilFunction.availableTotalQues(resTopicTotal)
                tvTopicTotal.text = count.toString()

            } else
            {
                Toast.makeText(requireContext(),"something went wrong with server",Toast.LENGTH_SHORT).show()
            }

        }

       // var token: String= "Bearer 15892|Jb9Ca6yIdf3m2it3tlgUdgQjXio7MfXylgQuoGPT"
     //   val map= HashMap<String,String>()
      //  map.put("book_id",bookId)
       // map.put("token",token)
      //  map.put("book_id",bookId)
       // map.putA("chapter_id", ArrayList(chapterList))
       // viewModel.getTopicList(token,bookId, ArrayList(chapterList))

        ///api calling for getting  available topic total question
        // make object of string builder()

      //  var chapterid: String =""
        //convert the arraylist in string and api calling

        Log.v("arrayvalues","append arrayvalues"+ Gson().toJson(chapterid))
        //api calling for available total


        viewModel.getTopicList(token, PostData.TopicPostData(bookId, chapterList as ArrayList<String>)
        )

        viewModel.topicList.observe(viewLifecycleOwner){
            if (it.code==200){
                val topicClassModel:TopicsClassModel= TopicsClassModel(false,"-1","All Topics","",
                    emptyList())
                rspTopiclist2 = it.data.topics
                rspTopicList.add(topicClassModel)
                rspTopicList.addAll(rspTopiclist2)

               // var rspTopicList = it.data.topics
               topicListAdp(rspTopicList)

            }else{

                Toast.makeText(requireContext(),"something went wrong with response",Toast.LENGTH_SHORT).show()
            }

        }

    }
    fun topicListAdp(rspTopicList: List<TopicsClassModel>) {
        var topicAdapt : SelectTopicAdp = SelectTopicAdp(rspTopicList)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = topicAdapt

    }


}