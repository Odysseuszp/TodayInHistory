package com.wzp.todayinhistory.model

import android.util.Log
import com.wzp.todayinhistory.base.BaseRepository
import com.wzp.todayinhistory.base.Const
import com.wzp.todayinhistory.base.ResponseData
import com.wzp.todayinhistory.data.DayDate

/**
 * @Author wzp
 * @Date 1/13/21
 * @Des Repository用于获取数据
 */
class ArticleRepository : BaseRepository() {
    suspend fun getDatas(): ResponseData<List<DayDate>> {
        return request {
            Log.d("odysseus","load3 run in ${Thread.currentThread().name}")
            BaseRetrofitClient.getService(RequestService::class.java, Const.BASE_URL)
                .getDayData(Const.INTERFACE_KEY, "12/15")
        }
    }
}