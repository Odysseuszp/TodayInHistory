package com.wzp.todayinhistory.base

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * @Author wzp
 * @Date 1/13/21
 * @Des  基础库  每次只需执行request
 */
open class BaseRepository {
    suspend fun <T : Any> request(call: suspend () -> ResponseData<T>): ResponseData<T> {
        return withContext(Dispatchers.IO) { call.invoke() }
    }
}