package com.example.jsonprojectpostapi.models

class PostData {

  data class TopicPostData(val book_id:String,val chapter_id:ArrayList<String>)
  data class SourcePostData(
    val book_id:String,
    val chapter_id: ArrayList<String>,
    val topic_id: ArrayList<String>
    )
  data class availableQuestionPost(
      var class_id: String = "",
      var subject_id: String = "",
      var book_id: String = "",
      var chapter_id: String="",
      var topic_id:String = ""
  )
}