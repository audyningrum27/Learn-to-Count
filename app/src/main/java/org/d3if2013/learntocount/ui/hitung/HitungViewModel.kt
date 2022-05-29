package org.d3if2013.learntocount.ui.hitung

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if2013.learntocount.db.LtcDao
import org.d3if2013.learntocount.db.LtcEntity
import org.d3if2013.learntocount.model.HasilLtc
import org.d3if2013.learntocount.model.hitungLtc

class HitungViewModel(private val db: LtcDao) : ViewModel() {
    private val hasilLtc = MutableLiveData<HasilLtc?>()

    fun hitungLtc(mark: String, grade:Int) {
        val dataLtc = LtcEntity(
            mark = mark,
            grade = grade
        )
        hasilLtc.value = dataLtc.hitungLtc()


        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                db.insert(dataLtc)
            }
        }
    }

    fun getHasilLtc(): LiveData<HasilLtc?> = hasilLtc
}