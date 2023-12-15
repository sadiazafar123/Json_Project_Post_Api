package com.example.jsonprojectpostapi.models


data class ResponseClassList(
         var code: Int,
         var success: Boolean,
         var data: DataClass,
        )
   data class DataClass(
         var classes :  List<ClassesModel>,
         var subjects : List<SubjectClassModel>,
         var books :  List<BooksClassModel>,
         var chapters :  List<ChapterClassModel>,
         var topics :    List<TopicsClassModel>,
         var source_list : List<SourceListModel>,
         var question_counts : List<QuestionCountClass>
   )
data class SourceListModel(
    var qdsrcid: String?=null,
    var qdsrc_title: String?=null,
    var question_counts: List<QuestionCountClass>
)
 data class TopicsClassModel(
     var isSelected: Boolean = false,
     var tpid: String? = null,
     var ttitle:String? = null,
     var tno: String? = null,
     var question_counts: List<QuestionCountClass>
 )
 data class ChapterClassModel(
     var isSelected: Boolean = false,
     var bkdid: String? = null,
     var ctitle:String? = null,
     var cno: String? = null,
     var question_counts: List<QuestionCountClass>
 )
   data class BooksClassModel(
         var bkid:String? = null,
         var btitle:String? = null,
         var sjid:String? = null,
         var question_counts: List<QuestionCountClass>
        )
   data class SubjectClassModel(
         var sjid: String?=null,
         var clsid: String?=null,
         var stitle: String? =null,
         var class_title:String?=null,
         var btitle:String?=null,
         var question_counts:List<QuestionCountClass>
)


   data class ClassesModel(
         var clsid: String?=null,
         var class_title:String?=null,
         var question_counts:List<QuestionCountClass>
)
   data class QuestionCountClass(
         var qtype: String?=null,
         var total:String?=null,
         var easy:String?=null,
         var medium:String?=null,
         var hard:String?=null,
         var vhard:String?=null


   )