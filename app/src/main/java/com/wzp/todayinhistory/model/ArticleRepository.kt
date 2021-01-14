package com.wzp.todayinhistory.model

import com.wzp.todayinhistory.base.BaseRepository
import com.wzp.todayinhistory.base.Const

/**
 * @Author wzp
 * @Date 1/13/21
 * @Des Repository用于获取数据
 */
class ArticleRepository : BaseRepository() {
    private val api by lazy {
        BaseRetrofitClient.getService(
            RequestService::class.java,
            Const.BASE_URL
        )
    }

    suspend fun getDatas(date: String) = request {
        api.getDayData(Const.INTERFACE_KEY, date)
    }
}