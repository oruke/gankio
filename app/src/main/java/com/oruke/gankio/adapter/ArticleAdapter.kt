package com.oruke.gankio.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.oruke.gankio.R
import com.oruke.gankio.base.BaseAdapter
import com.oruke.gankio.model.GankIO

class ArticleAdapter : BaseAdapter<GankIO, BaseViewHolder>(R.layout.article_item) {

    override fun convert(helper: BaseViewHolder, item: GankIO) {
        helper.setText(R.id.article_item_desc, item.desc)
        helper.setText(R.id.article_item_author, item.who)
        helper.setText(R.id.article_item_date, item.createdAt)
        helper.addOnClickListener(R.id.article_item_card)
    }
}