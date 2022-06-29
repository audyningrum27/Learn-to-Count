package org.d3if2013.learntocount.ui.article

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if2013.learntocount.model.Article
import org.d3if2013.learntocount.network.ApiStatus
import org.d3if2013.learntocount.network.LtcApi

class ArticleViewModel : ViewModel() {

    private val data = MutableLiveData<List<Article>>()
    private val status = MutableLiveData<ApiStatus>()


    init {
        retrieveData()
    }

    private fun retrieveData() {
        viewModelScope.launch (Dispatchers.IO) {
            status.postValue(ApiStatus.LOADING)
            try {
                data.postValue(LtcApi.service.getArticle())
                status.postValue(ApiStatus.SUCCESS)
            } catch (e: Exception) {
                Log.d("MainViewModel", "Failure: ${e.message}")
                status.postValue(ApiStatus.FAILED)
            }
        }
    }

    fun getData(): LiveData<List<Article>> = data
    fun getStatus(): LiveData<ApiStatus> = status
}