package com.wzp.todayinhistory.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.wzp.todayinhistory.base.BaseViewModel
import com.wzp.todayinhistory.base.ResponseData
import com.wzp.todayinhistory.data.DayDate
import com.wzp.todayinhistory.model.ArticleRepository

class DayViewModel : BaseViewModel() {

    private var datas = MutableLiveData<ResponseData<List<DayDate>>>()
    private val repository = ArticleRepository()

    fun getArticle(): LiveData<ResponseData<List<DayDate>>> {
        return datas
    }

    fun loadDatas(date: String) {
        launchUI {
            val result = repository.getDatas(date)
            datas.value = result
        }
    }
}