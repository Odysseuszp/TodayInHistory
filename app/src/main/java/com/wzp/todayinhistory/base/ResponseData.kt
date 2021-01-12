package com.wzp.todayinhistory.base

/**
 * @Author wzp
 * @Date 1/12/21
 * @Des 响应数据结果（响应基类）
 */
data class ResponseData<out T>(
    val error_code: Int,
    val reason: String,
    val result: T
)