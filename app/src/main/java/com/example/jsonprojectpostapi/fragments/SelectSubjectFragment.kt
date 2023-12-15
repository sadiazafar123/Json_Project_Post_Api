package com.example.jsonprojectpostapi.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jsonprojectpostapi.R
import com.example.jsonprojectpostapi.adapters.SelectSubjectAdapter
import com.example.jsonprojectpostapi.extensionclassses.UtilFunction
import com.example.jsonprojectpostapi.extensionclassses.UtilFunction.Companion.token
import com.example.jsonprojectpostapi.models.PostData
import com.example.jsonprojectpostapi.models.SubjectClassModel
import com.example.jsonprojectpostapi.retrofit.MainViewModel
import com.example.jsonprojectpostapi.retrofit.MyViewModelFactory
import com.example.jsonprojectpostapi.retrofit.Repository
import com.example.jsonprojectpostapi.retrofit.RetrofitClient


class SelectSubjectFragment : Fragment(), SelectSubjectAdapter.onSubjectItemClickListener {
    lateinit var viewModel: MainViewModel
    lateinit var recyclerView: RecyclerView
    lateinit var navController: NavController
    lateinit var tvSubjectTotal:TextView
    lateinit var _id: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_select_subject, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvSubjectTotal=view.findViewById(R.id.tvSubjectTotal)
        recyclerView = view.findViewById(R.id.recyclerView_subject)
        navController = Navigation.findNavController(requireActivity(), R.id.fragment_container_view)
        viewModel = ViewModelProvider(this, MyViewModelFactory(Repository(RetrofitClient))).get(
            MainViewModel::class.java
        )
        val args = SelectSubjectFragmentArgs.fromBundle(requireArguments())
        _id = args.idClassesModel!!
        Log.v("id", "id" + _id)

        Toast.makeText(requireContext(), "id receiver" + _id, Toast.LENGTH_SHORT).show()
        requestSubjectList()
    }

    fun requestSubjectList() {
      //  var token: String = "Bearer 15892|Jb9Ca6yIdf3m2it3tlgUdgQjXio7MfXylgQuoGPT"
        //make object
        val postData: PostData.availableQuestionPost = PostData.availableQuestionPost()
        postData.class_id = _id
        //api calling of available total question
        viewModel.subjectAvailableQuestion(token,postData)
        viewModel.subjectAvailableQues.observe(viewLifecycleOwner){
            if (it.code==200){
                val respsubjectAvailableQues = it.data.question_counts
                val count = UtilFunction.availableTotalQues(respsubjectAvailableQues)
                tvSubjectTotal.text = count.toString()

            } else{

                Toast.makeText(requireContext(),"something went wrong with server",Toast.LENGTH_SHORT).show()
            }

        }

        viewModel.getSubjectList(token, _id)
        viewModel.subjectList.observe(viewLifecycleOwner) {
            //kotlin function used for direct remove duplication("distinct by")
            //"distinct by" used for each key or value, and "distict" used for whole model check
            //  var rsponSubjectlist= it.data.subjects.distinctBy { it.sjid }
            if (it.code==200){
                val rsponSubjectlist = it.data.subjects
                val responseSubjectListNew = ArrayList<SubjectClassModel>()
          //"indices" used for to get position like rsponSubjectlist.size
            for (i in rsponSubjectlist.indices) {
                var contain = 0
                for (j in 0 until i + 1) {
                    if (rsponSubjectlist[j].sjid.equals(rsponSubjectlist[i].sjid)) {
                        contain++
                    }
                }
                if (contain == 1) {
                    responseSubjectListNew.add(rsponSubjectlist[i])
                }
            }

            subjectListAdap(responseSubjectListNew)
        }else
            {
                Toast.makeText(requireContext(),"something went wrong in response",Toast.LENGTH_SHORT).show()
            }
        }

    }

    fun subjectListAdap(list: List<SubjectClassModel>) {
        var subjectAdp: SelectSubjectAdapter = SelectSubjectAdapter(list, this)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = subjectAdp
    }

    override fun onItemClick(position: Int, subjectClassModel: SubjectClassModel) {
        navController.navigate(
            SelectSubjectFragmentDirections.actionSelectSubjectFragmentToSelectBookFragment(_id,subjectClassModel.sjid.toString()
            )
        )
       // Log.v("subjectfragment","classid"+_id)
       // Log.v("subjectfragment","subjectid"+subjectClassModel.sjid)

    }

}