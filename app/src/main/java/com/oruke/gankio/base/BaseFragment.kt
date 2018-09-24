package com.oruke.gankio.base

import android.support.v4.app.Fragment

abstract class BaseFragment : Fragment() {
    override fun onResume() {
        super.onResume()
        (activity as BaseActivity).fragment = this
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden)
            (activity as BaseActivity).fragment = this
    }

    abstract fun onBackPressed(): Boolean
}