package com.oruke.gankio.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.oruke.gankio.R
import com.oruke.gankio.model.GankIO

class ArticleAdapter : BaseQuickAdapter<GankIO, BaseViewHolder>(R.layout.article_item, ArrayList<GankIO>()) {
    override fun convert(helper: BaseViewHolder, item: GankIO) {
        helper.setText(R.id.article_item_desc, item.desc)
        helper.addOnClickListener(R.id.article_item_card)
    }
}