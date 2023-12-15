package com.example.jsonprojectpostapi.models

 data class ResponsePaperList(
    var code: Int,
    var success: Boolean,
    var data: DataModel
    )
data class DataModel(
    var total_rows: Int,
    var papers_list: List<PaperList>
    )
data class PaperList(
    var pid: String?= null,
    var clsid: String?=null,
    var class_title: String?=null,
    var clno:   String?=null,
    var stitle: String?= null,
    var pdate:  String?=null,
    var chnos:  String?=null
)
