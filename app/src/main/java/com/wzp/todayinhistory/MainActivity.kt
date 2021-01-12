package com.wzp.todayinhistory

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.wzp.todayinhistory.databinding.ActivityMainBinding
import com.wzp.todayinhistory.model.BaseRetrofitClient
import com.wzp.todayinhistory.model.RequestService
import com.wzp.todayinhistory.view.ShowDayActivity
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
                val intent = Intent(this@MainActivity, ShowDayActivity::class.java)
                startActivity(intent)
                coroutineRequest()
            }
        }

    }

    private fun coroutineRequest() {
        launch {
            val result = withContext(Dispatchers.IO) {
                BaseRetrofitClient.getService(RequestService::class.java, BASE_URL)
                        .getDayData(
                                "ba3655c159425c992f8e08bf0f1ac282",
                                mainBinding.searchEdit.text.toString()
                        )
                        .execute()
            }

            if (result.isSuccessful) {
                val jsonText = result.body()!!
                Log.d("odysseus", "text = $jsonText")
            }
        }
    }

    companion object {
        private const val BASE_URL = "https://v.juhe.cn/"
    }
}