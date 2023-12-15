package com.example.jsonprojectpostapi.models

data class RequestPaperList(
    var classid: String?= null,
    var sdate: String?= null,
    var csid: String?=null,
    var chaptersId: String?=null,
    var paginate_end: String?=null,
    var paginate_start:String?= null,
    var edate: String?=null,
    var subjectid:String?=null,
    var bookid: String? =null
)
