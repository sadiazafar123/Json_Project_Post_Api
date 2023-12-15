package com.example.jsonprojectpostapi.extensionclassses

import com.example.jsonprojectpostapi.models.QuestionCountClass

class UtilFunction() {
    companion object{

        val token: String = "Bearer 881|8l6XnpZQLE7NUF6b2ivOVrUM1xqAyj5iJkFwrONa"

        fun availableTotalQues(questionCounts: List<QuestionCountClass>): Int {
            var count=0
            for (i in 0 until  questionCounts.size){
                questionCounts[i].total?.let {
                    count = count + it.toInt()
                    // count += it.toInt()
                }
            }
            return count
        }
    }

}