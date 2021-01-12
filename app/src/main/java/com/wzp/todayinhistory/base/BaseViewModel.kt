package com.wzp.todayinhistory.base

import androidx.lifecycle.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout

class BaseViewModel : ViewModel(), LifecycleObserver {
    private val error by lazy {
        MutableLiveData<Exception>()
    }

    private val closed by lazy {
        MutableLiveData<Int>()
    }

    //运行在UI线程的协程
    fun launchUI(block: suspend CoroutineScope.() -> Unit) = viewModelScope.launch {
        try {
            withTimeout(5000) {
                block
            }
        } catch (e: Exception) {
            error.value = e
        } finally {
            closed.value = 200
        }
    }

    /**
     * 请求失败，出现异常
     */
    fun getError(): LiveData<Exception> {
        return error
    }

    /**
     * 请求完成，统一做关闭处理
     */
    fun doClose(): LiveData<Int> {
        return closed
    }

}