package org.d3if2013.learntocount.model

import org.d3if2013.learntocount.db.LtcEntity

fun LtcEntity.hitungLtc() : HasilLtc{
    var marks = 0

    when (marks) {
        5 -> {
            mark = "5 dari 5 soal"
            grade = 100
        }
        4 -> {
            mark = "4 dari 5 soal"
            grade = 80
        }
        3 -> {
            mark = "3 dari 5 soal"
            grade = 60
        }
        2 -> {
            mark = "2 dari 5 soal"
            grade = 40
        }
        1 -> {
            mark = "1 dari 5 soal"
            grade = 20
        }
        else -> {
            mark = "0 dari 5 soal"
            grade = 0
        }
    }
    return HasilLtc(marks)
}