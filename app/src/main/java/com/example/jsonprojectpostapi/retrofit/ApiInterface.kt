package com.example.jsonprojectpostapi.retrofit

import com.example.jsonprojectpostapi.models.PostData
import com.example.jsonprojectpostapi.models.RequestPaperList
import com.example.jsonprojectpostapi.models.ResponseClassList
import com.example.jsonprojectpostapi.models.ResponsePaperList
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiInterface {
    /*@GET
    fun getAllUserResult(): Call<Any>
    */
   @POST("my-papers-list")
    fun getAllPaperList(@Header("Authorization") token: String, @Body  requestModel: RequestPaperList )
    : Call<ResponsePaperList>

    //fun getPaperList(@Body model:RequestPaperList): Call<ResponsePaperList>
    @GET("get-class-list")
    fun getAllClassList(@Header("Authorization") token:String):Call<ResponseClassList>


    @FormUrlEncoded
    @POST("get-subject-list")
    fun getAllSubjectList(@Header("Authorization") token:String , @Field("class_id") class_id : String ) : Call<ResponseClassList>

    @FormUrlEncoded
    @POST("get-book-list")
    fun getBookList(@Header("Authorization") token: String , @Field("class_id") class_id: String,@Field("subject_id") subject_id: String ) :Call<ResponseClassList>
   @FormUrlEncoded
   @POST("get-chapter-list")
   fun getChapterList(@Header("Authorization") token:String , @Field("book_id") book_id:String): Call<ResponseClassList>

 /* @FormUrlEncoded
  @POST("get-topic-list")
  fun getTopicList(@Header("Authorization") token: String, @Field("book_id") book_id: String ,
                   @Field("chapter_id") chapter_id: ArrayList<String>): Call<ResponseClassList>
 */

  @POST("get-topic-list")
  fun getTopicList(@Header("Authorization") token: String, @Body topicPostData: PostData.TopicPostData): Call<ResponseClassList>
///
  @POST("get-source-list")
  fun getSourceList(@Header("Authorization") token: String ,@Body sourcePostData: PostData.SourcePostData): Call<ResponseClassList>

  @POST("get-available-questions")
  fun getAvailableQuestion(@Header("Authorization") token:String ,@Body sourcePostData: PostData.availableQuestionPost)
  : Call<ResponseClassList>

}