package com.wzp.todayinhistory

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.wzp.todayinhistory.base.Const
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
    private lateinit var dayViewModel: DayViewModel
    private lateinit var job: Job

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        job = Job()

        dayViewModel = ViewModelProvider(this)[DayViewModel::class.java]
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        mainBinding.searchBtn.apply {
            text = getString(R.string.search_btn_text)
            setOnClickListener {
//                val intent = Intent(this@MainActivity, ShowDayActivity::class.java)
//                startActivity(intent)
//                coroutineRequest()
                initDatas()
            }
        }

        handleExceptions()
    }

    //使用协程进行请求
    private fun coroutineRequest() {
        val apiService = BaseRetrofitClient.getService(RequestService::class.java, Const.BASE_URL)
        launch {
            try {
                val result =
                    apiService.getDayData(
                        Const.INTERFACE_KEY,
                        mainBinding.searchEdit.text.toString()
                    )
                val json = result.result
                Log.d("odysseus", "result = $json")
            } catch (e: Exception) {
                Toast.makeText(this@MainActivity, "错了", Toast.LENGTH_SHORT).show()
            }
        }
    }

    /**
     * @Author wzp
     * @Date 1/13/21
     * @Des  使用ViewModel请求网络
     */
    private fun initDatas() {
        dayViewModel.getArticle().observe(this, {
            for (i in it.result.indices) {
                Log.d("odysseus", "json = ${it.result[i].title}")
                mainBinding.showText.text = it.result[i].title
            }
        })
    }

    /**
     * @Author wzp
     * @Date 1/13/21
     * @Des  处理异常
     */
    private fun handleExceptions() {
        dayViewModel.run {
            getError().observe(this@MainActivity, {
                Toast.makeText(this@MainActivity, "连接错误", Toast.LENGTH_SHORT).show()
            })

            getClosed().observe(this@MainActivity, {
                Toast.makeText(this@MainActivity, "关闭请求", Toast.LENGTH_SHORT).show()
            })
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycle.removeObserver(dayViewModel)
    }


}