package com.oruke.gankio.base

import android.support.v7.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {
    var fragment: BaseFragment? = null
}