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
import com.example.jsonprojectpostapi.adapters.SelectClassAdapter
import com.example.jsonprojectpostapi.extensionclassses.UtilFunction
import com.example.jsonprojectpostapi.extensionclassses.UtilFunction.Companion.token
import com.example.jsonprojectpostapi.models.ClassesModel
import com.example.jsonprojectpostapi.models.PostData
import com.example.jsonprojectpostapi.retrofit.MainViewModel
import com.example.jsonprojectpostapi.retrofit.MyViewModelFactory
import com.example.jsonprojectpostapi.retrofit.Repository
import com.example.jsonprojectpostapi.retrofit.RetrofitClient

class SelectClassFragment : Fragment(), SelectClassAdapter.onItemClicklistener {
    lateinit var viewModel: MainViewModel
    lateinit var recyclerView: RecyclerView
    lateinit var navController: NavController
    lateinit var availbleTotalQuestion:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_select_class, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        availbleTotalQuestion=view.findViewById(R.id.tvTotal_Question)
        recyclerView = view.findViewById(R.id.recyclerViewClass)
        viewModel = ViewModelProvider(this, MyViewModelFactory(Repository(RetrofitClient))).get(
            MainViewModel::class.java
        )
        navController =
            Navigation.findNavController(requireActivity(), R.id.fragment_container_view)
        requestClassList()

    }

    fun requestClassList() {
       //to make object
        val postData:PostData.availableQuestionPost = PostData.availableQuestionPost()
       //api callinpog of available total question
        viewModel.classAvailableQuestion(token ,postData )
        viewModel.getAvailableQuestion.observe(viewLifecycleOwner){
            if (it.code == 200){
                Log.v("getAvailableQuestion", "AllAvailableQuestion" + it.data.question_counts)

                var responseAvailableQuestion = it.data.question_counts
                val count= UtilFunction.availableTotalQues(responseAvailableQuestion)
                availbleTotalQuestion.text= count.toString()

            } else{
                Toast.makeText(requireContext(),"something went wrong with server",Toast.LENGTH_SHORT).show()
            }

        }
   // api calling of ALL CLASS LIST
        viewModel.allClassList(token)
        viewModel.classList.observe(viewLifecycleOwner) {
            if (it.code == 200) {

                Log.v("classlist", "clsasslist" + it.data.classes)
                var responseClassList = it.data.classes
                classListAdapter(responseClassList)

            } else {

                Toast.makeText(requireContext(), "check your server connection", Toast.LENGTH_SHORT)
                    .show()
            }

        }
    }

    fun classListAdapter(list: List<ClassesModel>) {
        var classlistAdpt: SelectClassAdapter = SelectClassAdapter(list, this)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = classlistAdpt

    }
    //extension function

    override fun onItemClick(position: Int, classesModel: ClassesModel) {
        //var id= Gson().toJson(classesModel.clsid)
        // var budnle=Bundle()
        //send data through safe arguments
        Toast.makeText(requireContext(), "msg" + classesModel.clsid, Toast.LENGTH_SHORT).show()
        navController.navigate(
            SelectClassFragmentDirections.actionSelectClassFragmentToSelectSubjectFragment(
                classesModel.clsid
            )
        )
    }
}


