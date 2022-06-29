package org.d3if2013.learntocount.ui.histori

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if2013.learntocount.db.LtcDao

class HistoriViewModel(private val db: LtcDao) : ViewModel()  {
    val data = db.getLastLtc()

    fun hapusData() = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            db.clearData()
        }
    }
}