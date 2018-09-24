package com.oruke.gankio.ui

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chad.library.adapter.base.BaseQuickAdapter
import com.oruke.gankio.R
import com.oruke.gankio.adapter.ArticleAdapter
import com.oruke.gankio.base.BaseFragment
import com.oruke.gankio.base.BaseSubscriber
import com.oruke.gankio.model.GankIO
import com.oruke.gankio.service.ApiService
import com.oruke.gankio.utils.HttpMothed
import com.scwang.smartrefresh.layout.api.RefreshLayout
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.content_main.*

class ArticleListFragment: BaseFragment() {
    var type = "iOS"
    var page = 1
    val size = 20

    val articleAdapter = ArticleAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.content_main, container, false)
    }

    override fun onResume() {
        super.onResume()

        articleAdapter.onItemChildClickListener = BaseQuickAdapter.OnItemChildClickListener { adapter, view, position ->
            Log.i("click", position.toString())
            val fragmentTransition = fragmentManager?.beginTransaction()
            val fragment = ArticleDetailFragment()
            fragment.url = articleAdapter.data[position].url
            Log.i("url", fragment.url)
            fragmentTransition?.hide(this)
                    ?.add(R.id.context, ArticleDetailFragment())
                    ?.addToBackStack(null)
            fragmentTransition?.commit()
        }
//        articleAdapter.openLoadAnimation()
        smart_refresh_layout.setOnLoadMoreListener {
            loadData(it)
        }
        smart_refresh_layout.setOnRefreshListener {
            refreshData(it)
        }

        recycler_view.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = articleAdapter
        }

        loadData()
    }

    private fun loadData() {
        HttpMothed.retrofit()
                .create(ApiService::class.java)
                .getData(type, size, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : BaseSubscriber<GankIO>() {
                    override fun success(t: ArrayList<GankIO>) {
                        page++
                        articleAdapter.setNewData(t)
                        recycler_view.scrollToPosition(0)
                    }

                })
    }

    private fun loadData(refreshLayout: RefreshLayout) {
        HttpMothed.retrofit()
                .create(ApiService::class.java)
                .getData(type, size, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : BaseSubscriber<GankIO>() {
                    override fun success(t: ArrayList<GankIO>) {
                        t.removeAll(articleAdapter.data)
                        articleAdapter.addData(t)
                        refreshLayout.finishLoadMore()
                        page++
                    }

                })
    }

    private fun refreshData(refreshLayout: RefreshLayout) {
        HttpMothed.retrofit()
                .create(ApiService::class.java)
                .getData(type, size, 0)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : BaseSubscriber<GankIO>() {
                    override fun success(t: ArrayList<GankIO>) {
                        articleAdapter.refreshData(t)
                        refreshLayout.finishRefresh()
                    }

                })
    }

    override fun onBackPressed(): Boolean {
        return false
    }
}