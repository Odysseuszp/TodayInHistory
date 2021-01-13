package com.wzp.todayinhistory.model

import com.wzp.todayinhistory.base.ResponseData
import com.wzp.todayinhistory.data.DayDate
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @Author wzp
 * @Date 1/12/21
 * @Des 创建请求接口
 */
interface RequestService {
    @GET("todayOnhistory/queryEvent.php")
    suspend fun getDayData(
        @Query("key") key: String,
        @Query("date") date: String
    ): ResponseData<List<DayDate>>
}