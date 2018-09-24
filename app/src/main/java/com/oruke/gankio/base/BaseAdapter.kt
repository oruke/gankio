package com.oruke.gankio.base

import android.support.annotation.LayoutRes
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.oruke.gankio.model.GankIO

abstract class BaseAdapter<T, K: BaseViewHolder>(@LayoutRes layoutResId: Int): BaseQuickAdapter<T, K>(layoutResId, ArrayList<T>()) {

    fun refreshData(newData: MutableCollection<out T>) {
        newData.removeAll(mData)
        if (newData.isNotEmpty()) {
            mData.addAll(0, newData)
            notifyItemRangeInserted(mData.size - newData.size + headerLayoutCount, newData.size)
            notifyDataSetChanged()
        }
    }
}