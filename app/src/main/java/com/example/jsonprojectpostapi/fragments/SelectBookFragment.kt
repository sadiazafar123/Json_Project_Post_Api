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
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jsonprojectpostapi.R
import com.example.jsonprojectpostapi.adapters.SelectBookAdp
import com.example.jsonprojectpostapi.extensionclassses.UtilFunction
import com.example.jsonprojectpostapi.extensionclassses.UtilFunction.Companion.token
import com.example.jsonprojectpostapi.models.BooksClassModel
import com.example.jsonprojectpostapi.models.PostData
import com.example.jsonprojectpostapi.retrofit.MainViewModel
import com.example.jsonprojectpostapi.retrofit.MyViewModelFactory
import com.example.jsonprojectpostapi.retrofit.Repository
import com.example.jsonprojectpostapi.retrofit.RetrofitClient


class SelectBookFragment : Fragment() ,SelectBookAdp.onItemBookClickListener {
    lateinit var classId : String
    lateinit var subjectId : String
    lateinit var viewModel: MainViewModel
    lateinit var recyclerView:RecyclerView
    lateinit var navController: NavController
    lateinit var tvTotalQuesBook:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_select_book, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvTotalQuesBook=view.findViewById(R.id.tvTotalQuesBook)
        recyclerView=view.findViewById(R.id.recyclerView_book)

        viewModel= ViewModelProvider(this,MyViewModelFactory(Repository(RetrofitClient))).
        get(MainViewModel::class.java)
        navController = Navigation.findNavController(requireActivity(), R.id.fragment_container_view)

        val args2= SelectBookFragmentArgs.fromBundle(requireArguments())
        classId = args2.classId
        subjectId = args2.subjectId
        Log.v("id2" ,"classid"+args2.classId)
        Log.v("id2","subjectid"+args2.subjectId)
        requestBookList()
        /*var click:RelativeLayout=view.findViewById(R.id.relativelayout_book)

        click.setOnClickListener(){
            navController.navigate(SelectBookFragmentDirections.actionSelectBookFragmentToSelectChapterFragment())
        }
*/

    }
    fun requestBookList(){
       // val token: String = "Bearer 15892|Jb9Ca6yIdf3m2it3tlgUdgQjXio7MfXylgQuoGPT"
        val postData: PostData.availableQuestionPost= PostData.availableQuestionPost()
        postData.class_id = classId
        postData.subject_id = subjectId
        //get availabe book question
        viewModel.getAvailableBookQues(token,postData)
        viewModel.bookAvailableQues.observe(viewLifecycleOwner){
            if (it.code==200){
                val resBookTotal= it.data.question_counts
                val count = UtilFunction.availableTotalQues(resBookTotal)
                tvTotalQuesBook.text = count.toString()


            } else{
                Toast.makeText(requireContext(),"something went wrong with server",Toast.LENGTH_SHORT).show()
            }

        }
        //get All book list
        viewModel.getAllBooksList(token,classId,subjectId)
        viewModel.bookList.observe(viewLifecycleOwner){
            if (it.code==200) {
                val responBookList = it.data.books
                bookListAdapter(responBookList)
            }else
            {
                Toast.makeText(requireContext(),"something went wrong with response",Toast.LENGTH_SHORT).show()
            }
        }
    }
    fun bookListAdapter(responBookList: List<BooksClassModel>) {
        var bookAdp :SelectBookAdp= SelectBookAdp(responBookList,this)
        recyclerView.layoutManager= LinearLayoutManager(requireContext())
        recyclerView.adapter=bookAdp
    }

    override fun onItemClick(position: Int, booksClassModel: BooksClassModel) {
        navController.navigate(SelectBookFragmentDirections.actionSelectBookFragmentToSelectChapterFragment(booksClassModel.bkid))

    }

}