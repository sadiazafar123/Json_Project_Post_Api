package com.example.jsonprojectpostapi.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jsonprojectpostapi.R
import com.example.jsonprojectpostapi.adapters.SelectSourceAdapter
import com.example.jsonprojectpostapi.extensionclassses.UtilFunction
import com.example.jsonprojectpostapi.extensionclassses.UtilFunction.Companion.token
import com.example.jsonprojectpostapi.models.PostData
import com.example.jsonprojectpostapi.models.SourceListModel
import com.example.jsonprojectpostapi.retrofit.MainViewModel
import com.example.jsonprojectpostapi.retrofit.MyViewModelFactory
import com.example.jsonprojectpostapi.retrofit.Repository
import com.example.jsonprojectpostapi.retrofit.RetrofitClient


class SelectSourceFragment : Fragment() {
    lateinit var bookId: String
    var chapterList = mutableListOf<String>()
    var topicIdList = mutableListOf<String>()
    lateinit var viewModel: MainViewModel
    lateinit var recyclerView: RecyclerView
    lateinit var tvSourceTotal: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_select_source, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvSourceTotal = view.findViewById(R.id.tvSourceTotal)
        recyclerView = view.findViewById(R.id.recyclerView_source)
        val args2 = SelectSourceFragmentArgs.fromBundle(requireArguments())
        bookId = args2.bookId.toString()
        chapterList = args2.chapterId.toMutableList()
        topicIdList = args2.topicId.toMutableList()
        //viewmodel initilaization
        viewModel = ViewModelProvider(this, MyViewModelFactory(Repository(RetrofitClient))).get(
            MainViewModel::class.java
        )

        requestSourceList()

    }

    fun requestSourceList() {
        // var token: String = " Bearer 881|8l6XnpZQLE7NUF6b2ivOVrUM1xqAyj5iJkFwrONa"
        //create object of { post data class }
        val postData: PostData.availableQuestionPost = PostData.availableQuestionPost()
        var chapterId = StringBuilder()
        var topicId = StringBuilder()
        //convert chapterid  array into string
        for (i in 0 until chapterList.size) {
            if (chapterId.isEmpty()) {
                chapterId = chapterId.append(chapterList[i])
            } else {
                chapterId.append(",").append(chapterList[i])

            }
        }
        //convert topic_id  array into string
        for (i in 0 until topicIdList.size) {
            if (topicId.isEmpty()) {
                topicId = topicId.append(topicIdList[i])

            } else {
                topicId.append(",").append(topicIdList[i])

            }

        }
        postData.book_id = bookId
        postData.chapter_id = chapterId.toString()
        postData.topic_id = topicId.toString()
       viewModel.getSourceTotal(token, postData)

        viewModel.sourceTotal.observe(viewLifecycleOwner) {
            if (it.code == 200) {
                val resSourceTotal = it.data.question_counts
                val count = UtilFunction.availableTotalQues(resSourceTotal)
                tvSourceTotal.text = count.toString()
            }
        }

        viewModel.getSourceList(
            token, PostData.SourcePostData(
                bookId,
                chapterList as ArrayList<String>, topicIdList as ArrayList<String>
            )
        )
        viewModel.sourceList.observe(viewLifecycleOwner) {
            if (it.code == 200) {

                val rspSourceList = it.data.source_list
                Log.v("rspSourceList", "rspSourceList size" + rspSourceList.size)
                sourceListAdp(rspSourceList)

            } else {

                Toast.makeText(
                    requireContext(),
                    "something went wrong with server",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }


    }

    fun sourceListAdp(rspSourceList: List<SourceListModel>) {
        var sourceAdp: SelectSourceAdapter = SelectSourceAdapter(rspSourceList)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = sourceAdp

    }


}