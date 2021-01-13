package com.wzp.todayinhistory.base

import androidx.lifecycle.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel(), LifecycleObserver {
    /**
     * 默认情况下，子Job的失败将会导致父Job被取消。这种默认的行为可以通过@SupervisorJob()来修改
     */
    private val viewModelJob = SupervisorJob()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val error by lazy { MutableLiveData<Exception>() }
    private val closed by lazy { MutableLiveData<Int>() }

    //运行在UI线程的协程
    fun launchUI(block: suspend CoroutineScope.() -> Unit) = viewModelScope.launch {
        try {
            uiScope.launch(Dispatchers.Main) {
                block()
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
     * 请求完成，在此处做关闭操作
     */
    fun getClosed(): LiveData<Int> {
        return closed
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}