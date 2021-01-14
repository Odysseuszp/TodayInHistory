package com.wzp.todayinhistory.adapter

import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.wzp.todayinhistory.R
import com.wzp.todayinhistory.data.DayDate

class ShowTitleAdapter(layoutResId: Int, mList: ArrayList<DayDate>) :
    BaseQuickAdapter<DayDate, BaseViewHolder>(layoutResId, mList) {

    override fun convert(helper: BaseViewHolder, item: DayDate) {
        val dayTV: TextView = helper.getView(R.id.day_text1)
        val dateTV: TextView = helper.getView(R.id.date_text1)
        val titleTV: TextView = helper.getView(R.id.title_text1)
        val idTV: TextView = helper.getView(R.id.id_text1)

        dayTV.text = item.day
        dateTV.text = item.date
        titleTV.text = item.title
        idTV.text = item.e_id
    }

}