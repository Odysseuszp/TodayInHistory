package com.wzp.todayinhistory

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.wzp.todayinhistory.base.BaseViewModel
import com.wzp.todayinhistory.base.ResponseData
import com.wzp.todayinhistory.data.DayDate
import com.wzp.todayinhistory.model.ArticleRepository

class DayViewModel : BaseViewModel() {
    private val datas: MutableLiveData<ResponseData<List<DayDate>>> by lazy {
        MutableLiveData<ResponseData<List<DayDate>>>().also {
            loadDatas()
        }
    }

    private val repository = ArticleRepository()

    fun getArticle(): LiveData<ResponseData<List<DayDate>>> {
        return datas
    }

    //执行异步操作获取数据
    private fun loadDatas() {
        launchUI {
            Log.d("odysseus","load1 run in ${Thread.currentThread().name}")
            val result = repository.getDatas()
            Log.d("odysseus","load2 run in ${Thread.currentThread().name}")
            datas.value = result
        }
    }
}