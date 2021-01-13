package com.wzp.todayinhistory

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.wzp.todayinhistory.databinding.ActivityMainBinding
import com.wzp.todayinhistory.model.BaseRetrofitClient
import com.wzp.todayinhistory.model.RequestService
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

/**
 * @Author wzp
 * @Date 1/12/21
 * @Des MainActivity使用Retrofit请求数据
 */
class MainActivity : AppCompatActivity(), CoroutineScope {
    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var job: Job

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        job = Job()

        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        mainBinding.searchBtn.apply {
            text = getString(R.string.search_btn_text)
            setOnClickListener {
//                val intent = Intent(this@MainActivity, ShowDayActivity::class.java)
//                startActivity(intent)
                coroutineRequest()
            }
        }

    }

    //使用协程进行请求
    private fun coroutineRequest() {
        val apiService = BaseRetrofitClient.getService(RequestService::class.java, BASE_URL)
        launch {
            try {
                val result =
                    apiService.getDayData(INTERFACE_KEY, mainBinding.searchEdit.text.toString())
                val json = result.result
                Log.d("odysseus", "result = $json")
            } catch (e: Exception) {
                Toast.makeText(this@MainActivity, "错了", Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        private const val BASE_URL = "https://v.juhe.cn/"
        private const val INTERFACE_KEY = "ba3655c159425c992f8e08bf0f1ac282"
    }
}