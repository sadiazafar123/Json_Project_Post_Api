package com.example.jsonprojectpostapi.retrofit

import com.example.jsonprojectpostapi.models.PostData
import com.example.jsonprojectpostapi.models.RequestPaperList

class Repository(private val retrofitService :RetrofitClient) {
    fun getAllPaperList(model: RequestPaperList ,token:String)
    = retrofitService.getInstance().getAllPaperList(token,model)
    fun AllClassList(token:String)=retrofitService.getInstance().getAllClassList(token)
    fun getAllSubjectList(token:String, id:String) = retrofitService.getInstance().getAllSubjectList(token,id)
    fun getAllBookList(token: String ,classid: String,subjectId: String) = retrofitService.getInstance().getBookList(token,classid,subjectId)
    fun getAllChapter(token: String, bookId: String) = retrofitService.getInstance().getChapterList(token,bookId)
    fun getTopicList(token: String, topicPostData: PostData.TopicPostData) = retrofitService.getInstance().getTopicList(token,topicPostData)
    fun getSourceList(token: String, sourcePostData: PostData.SourcePostData) = retrofitService.getInstance().getSourceList(token,sourcePostData)
    fun getAvailableQuestion(token: String, postData: PostData.availableQuestionPost)= retrofitService
        .getInstance().getAvailableQuestion(token,postData)
    fun getSubjAvailableQues(token: String, postData: PostData.availableQuestionPost)= retrofitService.getInstance().getAvailableQuestion(token,postData)
    fun getAvailBookQues(token: String, postData: PostData.availableQuestionPost)= retrofitService.getInstance().getAvailableQuestion(token,postData)
    fun getAvailChapQues(token: String, postData: PostData.availableQuestionPost)= retrofitService.getInstance().getAvailableQuestion(token,postData)
    fun getTopicTotal(token: String, postData: PostData.availableQuestionPost)=retrofitService
        .getInstance().getAvailableQuestion(token,postData)

    fun getSourceTotal(token: String, postData: PostData.availableQuestionPost)=retrofitService.
    getInstance().getAvailableQuestion(token,postData)

}
    //fun getTopicList(token: String, bookId: String, arrayList: ArrayList<String>) = retrofitService.getInstance().getTopicList(token,bookId,arrayList)

