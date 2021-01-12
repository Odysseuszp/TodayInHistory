package com.wzp.todayinhistory.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.wzp.todayinhistory.R
import com.wzp.todayinhistory.databinding.ActivityShowDayBinding

class ShowDayActivity:AppCompatActivity() {
    private lateinit var showDayBinding: ActivityShowDayBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showDayBinding = DataBindingUtil.setContentView(this, R.layout.activity_show_day)

        showDayBinding.dayText.text = "1/1"
        showDayBinding.dateText.text = "公元238年1月1日"
        showDayBinding.titleText.text = "始皇出征"
    }
}