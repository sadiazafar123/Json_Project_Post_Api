package com.example.jsonprojectpostapi.retrofit

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jsonprojectpostapi.models.PostData
import com.example.jsonprojectpostapi.models.RequestPaperList
import com.example.jsonprojectpostapi.models.ResponseClassList
import com.example.jsonprojectpostapi.models.ResponsePaperList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(private val repository: Repository):ViewModel() {
    var paperList = MutableLiveData<ResponsePaperList>()
    var errorMsg = MutableLiveData<String>()
    var classList = MutableLiveData<ResponseClassList>()
    var subjectList = MutableLiveData<ResponseClassList>()
    var bookList = MutableLiveData<ResponseClassList>()
    var chapterList = MutableLiveData<ResponseClassList>()
    var topicList = MutableLiveData<ResponseClassList>()
    var sourceList = MutableLiveData<ResponseClassList>()
    var getAvailableQuestion = MutableLiveData<ResponseClassList>()
    var subjectAvailableQues = MutableLiveData<ResponseClassList>()
    var bookAvailableQues = MutableLiveData<ResponseClassList>()
    var chapterAvailableQues = MutableLiveData<ResponseClassList>()
    var topicAvailableTotal = MutableLiveData<ResponseClassList>()
    var sourceTotal = MutableLiveData<ResponseClassList>()


    fun getPaperList(model: RequestPaperList,token: String){

        val response= repository.getAllPaperList(model,token)
/*
        response.enqueue(object : Callback<ResponsePaperList>{
            override fun onResponse(call: Call<ResponsePaperList>, response: Response<ResponsePaperList>) {
                Log.v("onResponse","success"+response.body())
                if (response.isSuccessful){
                    paperList.postValue(response.body())

                } else{
                    Log.v("onResponse","failure"+response.body())

                }

            }

            override fun onFailure(call: Call<ResponsePaperList>, t: Throwable) {
                Log.v("onResponse","failure"+t.message)
                errorMsg.postValue(t.message)
            }

        })
*/


    }

    fun allClassList( token:String){
        var response=repository.AllClassList(token)
    response.enqueue(object : Callback<ResponseClassList> {
        override fun onResponse(call: Call<ResponseClassList>, response: Response<ResponseClassList>) {
            if (response.isSuccessful){
                classList.postValue(response.body())
                Log.v("onResponse123","response succesful123"+response.body())
               // val data:String=Gson().toJson(response.body()!!.data.classes)
                //Log.v("onResponse","response data "+ data)
                //Toast.makeText(context,data,Toast.LENGTH_SHORT).show()
            } else
            {
                Log.v("onResponse","response failure"+response.body())

            }
        }

        override fun onFailure(call: Call<ResponseClassList>, t: Throwable) {
            Log.v("onResponse","onFailure"+t.message)
            errorMsg.postValue(t.message)

        }

    })

    }
    fun getSubjectList(token:String ,id:String){
        var response= repository.getAllSubjectList(token,id)
        response.enqueue(object :Callback<ResponseClassList> {
            override fun onResponse(call: Call<ResponseClassList>, response: Response<ResponseClassList>) {
                Log.v("onResponse2","successs" +response.code())
                if (response.isSuccessful){
                    subjectList.postValue(response.body())
                }
                else {
                    Log.v("onResponse2","failure"+response.body())

                }

            }

            override fun onFailure(call: Call<ResponseClassList>, t: Throwable) {
                Log.v("onResponse2" ,"failure"+t.message)
            }

        })

    }


    fun getAllBooksList(token: String, classId: String, subjectId: String) {
        var response= repository.getAllBookList(token,classId,subjectId)
        response.enqueue(object :Callback<ResponseClassList>{
            override fun onResponse(call: Call<ResponseClassList>, response: Response<ResponseClassList>) {
                Log.v("bookListresponse","success"+response.body())
                if (response.isSuccessful){
                    bookList.postValue(response.body())
                }else{
                    Log.v("bookListresponse","success"+response.body())

                }

            }

            override fun onFailure(call: Call<ResponseClassList>, t: Throwable) {
                errorMsg.postValue(t.message)
                Log.v("bookListresponse","failure"+t.message)
            }

        })

    }

    fun getAllChapter(token: String, bookId: String){
        val response = repository.getAllChapter(token,bookId)
        response.enqueue(object : Callback<ResponseClassList>{
            override fun onResponse(call: Call<ResponseClassList>, response: Response<ResponseClassList>) {
                Log.v("onResponse111","succes"+response.body())
                if (response.isSuccessful){
                    chapterList.postValue(response.body())

                } else
                {
                    Log.v("onResponse111","failure"+response.body())

                }

            }

            override fun onFailure(call: Call<ResponseClassList>, t: Throwable) {
                Log.v("onResponse","failure"+t.message)
                errorMsg.postValue(t.message)

            }

        })
    }
    //get alltopiclist
/*
    fun getTopicList(token: String, bookId: String, arrayList: ArrayList<String>) {
        val response= repository.getTopicList(token,bookId,arrayList)
        response.enqueue(object:Callback<ResponseClassList>{
            override fun onResponse(call: Call<ResponseClassList>, response: Response<ResponseClassList>) {
                Log.v("getTopicList","getTopicList"+response.body())
                if (response.isSuccessful){
                    topicList.postValue(response.body())
                } else{
                    Log.v("getTopicList","getTopicList"+response.body())


                }

            }

            override fun onFailure(call: Call<ResponseClassList>, t: Throwable) {
                Log.v("getTopicList","getTopicList"+t.message)
                errorMsg.postValue(t.message)

            }
        })


    }
*/



    fun getTopicList(token: String, topicPostData: PostData.TopicPostData) {
        val response= repository.getTopicList(token,topicPostData)
        response.enqueue(object: Callback<ResponseClassList>{
            override fun onResponse(call: Call<ResponseClassList>, response: Response<ResponseClassList>) {
                Log.v("getTopicList","topic_List_response"+response.body())

                if (response.isSuccessful){
                    topicList.postValue(response.body())
                } else{
                    Log.v("getTopicList","topic_List_response"+response.body())


                }

            }

            override fun onFailure(call: Call<ResponseClassList>, t: Throwable) {
                Log.v("getTopicList","topic_List_response"+t.message)
                errorMsg.postValue(t.message)


            }
        })

    }

    fun getSourceList(token: String, sourcePostData: PostData.SourcePostData) {
        val response= repository.getSourceList(token, sourcePostData)
        response.enqueue(object : Callback<ResponseClassList>{
            override fun onResponse(call: Call<ResponseClassList>, response: Response<ResponseClassList>) {
                Log.v("getSourceList"," getSourceList"+response.body())
                if (response.isSuccessful){
                    sourceList.postValue(response.body())

                }

            }

            override fun onFailure(call: Call<ResponseClassList>, t: Throwable) {
                Log.v("getSourceList"," getSourceList"+ t.message)
                errorMsg.postValue(t.message)



            }
        })

    }

    fun classAvailableQuestion(token: String, postData: PostData.availableQuestionPost) {
        val response= repository.getAvailableQuestion(token ,postData)
        response.enqueue(object : Callback<ResponseClassList>{
            override fun onResponse(call: Call<ResponseClassList>, response: Response<ResponseClassList>) {
                Log.v("classAvailableQuestion","AvailableQuestion"+response.body())
                if (response.isSuccessful){
                    getAvailableQuestion.postValue(response.body())

                }
                 else{
                    Log.v("classAvailableQuestion","failure response"+response.body())


                }


            }

            override fun onFailure(call: Call<ResponseClassList>, t: Throwable) {
                Log.v("classAvailableQuestion","AvailableQuestion"+t.message)
                errorMsg.postValue(t.message)

            }
        })

    }

    fun subjectAvailableQuestion(token: String, postData: PostData.availableQuestionPost) {
        val response= repository.getSubjAvailableQues(token,postData)
        response.enqueue(object:Callback<ResponseClassList>{
            override fun onResponse(call: Call<ResponseClassList>, response: Response<ResponseClassList>) {
                Log.v("subjectAvailQues","subjectAvailableQuestion"+response.body())
                if (response.isSuccessful){
                    subjectAvailableQues.postValue(response.body())

                } else{
                    Log.v("subjectAvailQues","failure case"+response.body())


                }
            }

            override fun onFailure(call: Call<ResponseClassList>, t: Throwable) {
                errorMsg.postValue(t.message)
            }
        })

    }

    fun getAvailableBookQues(token: String, postData: PostData.availableQuestionPost) {
        val response= repository.getAvailBookQues(token,postData)
        response.enqueue(object :Callback<ResponseClassList>{
            override fun onResponse(call: Call<ResponseClassList>, response: Response<ResponseClassList>) {
                Log.v("getAvailableBookQues","getAvailableBookQues"+response.body())
                if (response.isSuccessful){
                    bookAvailableQues.postValue(response.body())
                } else{
                    Log.v("getAvailableBookQues","failure case"+response.body())


                }


            }

            override fun onFailure(call: Call<ResponseClassList>, t: Throwable) {
                errorMsg.postValue(t.message)
            }
        })

    }

    fun getAvailableChapterQues(token: String, postData: PostData.availableQuestionPost) {
        val response=repository.getAvailChapQues(token,postData)
        response.enqueue(object : Callback<ResponseClassList>{
            override fun onResponse(call: Call<ResponseClassList>, response: Response<ResponseClassList>) {
                Log.v("getAvailableChapterQues","getAvailableChapterQues"+response.body())
                if (response.isSuccessful){
                    chapterAvailableQues.postValue(response.body())
                }
                else{
                    Log.v("getAvailableChapterQues","response failure"+response.body())


                }

            }

            override fun onFailure(call: Call<ResponseClassList>, t: Throwable) {
                Log.v("getAvailableChapterQues","getAvailableChapterQues"+t.message)
                errorMsg.postValue(t.message)

            }
        })

    }

    fun getTopicTotal(token: String, postData: PostData.availableQuestionPost) {
        val response=repository.getTopicTotal(token,postData)
        response.enqueue(object:Callback<ResponseClassList>{
            override fun onResponse(call: Call<ResponseClassList>, response: Response<ResponseClassList>) {
                Log.v("getTopicTotal","getTopicTotal"+response.body())
                if (response.isSuccessful){
                    topicAvailableTotal.postValue(response.body())

                } else{
                    Log.v("getTopicTotal","failure"+response.body())


                }

            }

            override fun onFailure(call: Call<ResponseClassList>, t: Throwable) {
                Log.v("getTopicTotal","getTopicTotal"+t.message)
                errorMsg.postValue(t.message)

            }
        })

    }

    fun getSourceTotal(token: String, postData: PostData.availableQuestionPost) {
        val response= repository.getSourceTotal(token,postData)
        response.enqueue(object: Callback<ResponseClassList>{
            override fun onResponse(call: Call<ResponseClassList>, response: Response<ResponseClassList>)
            {
                Log.v("getSourceTotal","succesful"+response.body())
                if (response.isSuccessful)
                {
                    sourceTotal.postValue(response.body())
                }
                else
                {
                    Log.v("getSourceTotal","failure"+response.body())


                }

            }

            override fun onFailure(call: Call<ResponseClassList>, t: Throwable) {
                Log.v("getSourceTotal","failure"+t.message)
                errorMsg.postValue(t.message)

            }
        })

    }
    //available question


}