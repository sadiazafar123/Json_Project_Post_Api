package com.example.jsonprojectpostapi.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jsonprojectpostapi.R
import com.example.jsonprojectpostapi.adapters.SelectChapterAdp
import com.example.jsonprojectpostapi.extensionclassses.UtilFunction
import com.example.jsonprojectpostapi.extensionclassses.UtilFunction.Companion.token
import com.example.jsonprojectpostapi.models.ChapterClassModel
import com.example.jsonprojectpostapi.models.PostData
import com.example.jsonprojectpostapi.retrofit.MainViewModel
import com.example.jsonprojectpostapi.retrofit.MyViewModelFactory
import com.example.jsonprojectpostapi.retrofit.Repository
import com.example.jsonprojectpostapi.retrofit.RetrofitClient
import com.google.gson.Gson

class SelectChapterFragment : Fragment() {
    lateinit var viewModel: MainViewModel
    lateinit var navController: NavController
    lateinit var bookId: String
    lateinit var recyclerview:RecyclerView
    lateinit var buttonNext:AppCompatButton
    lateinit var rspChapterList2 : List<ChapterClassModel>
     var rspChapterList = ArrayList<ChapterClassModel>()
    lateinit var tvTotalChapterQues:TextView


    // var clickList = ArrayList<String>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_select_chapter, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController= Navigation.findNavController(requireActivity(),R.id.fragment_container_view)
        buttonNext = view.findViewById(R.id.btn_Next)
        tvTotalChapterQues = view.findViewById(R.id.tvTotalChapterQues)
       // buttonClick()

        recyclerview = view.findViewById(R.id.recyclerView_chapters)
        val args= SelectChapterFragmentArgs.fromBundle(requireArguments())
        //argument no 1
        bookId= args.bookId.toString()
        viewModel= ViewModelProvider(this, MyViewModelFactory(Repository(RetrofitClient))).get(MainViewModel::class.java)
        requestChapterList()
        btnClick()





    }
    fun btnClick(){
        buttonNext.setOnClickListener(){
            var clickList = mutableListOf<String>()
            //var clickList = ArrayList<String>()

            for (i in 0 until rspChapterList.size){
                if (rspChapterList[i].isSelected==true && rspChapterList[i].bkdid.equals("-1"))
                {
                    clickList.add(rspChapterList[i].bkdid.toString())
                   // Log.v("clickList","clickList"+clickList[i])

                } else if (rspChapterList[i].isSelected)
                {
                    clickList.add(rspChapterList[i].bkdid.toString())
                   // Log.v("clickList","clickList"+clickList[i])

                }
                //  clickList.add(rspChapterList2[i].isSelected.toString())

            }

            Log.v("clickList","clickList data"+Gson().toJson(clickList))
            navController.navigate(SelectChapterFragmentDirections.
            actionSelectChapterFragmentToSelectTopicFragment(bookId, clickList.toTypedArray()))
        }

    }

    fun requestChapterList(){
       // var token: String= "Bearer 15892|Jb9Ca6yIdf3m2it3tlgUdgQjXio7MfXylgQuoGPT"

        //api calling of chapter total
        val postData: PostData.availableQuestionPost= PostData.availableQuestionPost()
        postData.book_id = bookId
        viewModel.getAvailableChapterQues(token,postData)
        viewModel.chapterAvailableQues.observe(viewLifecycleOwner){
            if (it.code== 200){
                val resChapterTotal= it.data.question_counts
                val count= UtilFunction.availableTotalQues(resChapterTotal)
                tvTotalChapterQues.text= count.toString()



            } else{
                Toast.makeText(requireContext(),"something went wrong with server",Toast.LENGTH_SHORT).show()

            }


        }
        // api calling of
        // api calling of get All chapters
        viewModel.getAllChapter(token, bookId)
        viewModel.chapterList.observe(viewLifecycleOwner){
            if (it.code==200){
               // val rspChapterList = ArrayList<ChapterClassModel>()

                val chapterClassModel:ChapterClassModel = ChapterClassModel(false,"-1","All Chapters","",
                    emptyList()
                )


                 rspChapterList2 = it.data.chapters
                rspChapterList.add(chapterClassModel)


                rspChapterList.addAll(rspChapterList2)
                /*for(i in 0 until rspChapterList.size){
                    Log.v("rspChapterList","record of all index"+rspChapterList[i])
                }*/

                chapterListAdp(rspChapterList)

            } else{
                Toast.makeText(requireContext(),"something went wrong with server",Toast.LENGTH_SHORT).show()

            }

        }
        //rspChapterList2






    }
    fun chapterListAdp(rspChapterList: List<ChapterClassModel>) {
        var chapterAdapter:SelectChapterAdp= SelectChapterAdp(rspChapterList)
        recyclerview.layoutManager = LinearLayoutManager(requireContext())
        recyclerview.adapter = chapterAdapter




    }


}