package com.oruke.gankio

import android.nfc.Tag
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.FragmentManager
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.oruke.gankio.adapter.ArticleAdapter
import com.oruke.gankio.base.BaseActivity
import com.oruke.gankio.base.BaseFragment
import com.oruke.gankio.base.BaseSubscriber
import com.oruke.gankio.model.GankIO
import com.oruke.gankio.service.ApiService
import com.oruke.gankio.ui.ArticleListFragment
import com.oruke.gankio.utils.HttpMothed
import com.scwang.smartrefresh.layout.api.RefreshLayout
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        replaceFragment(ArticleListFragment())

    }

    override fun onBackPressed() {
        Log.i("onBackPressed", fragment!!.onBackPressed().toString())
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else if (fragment!!.onBackPressed()) {
            supportFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun replaceFragment(fragment: BaseFragment) {
        val fragmentTransition = supportFragmentManager.beginTransaction()
        fragmentTransition.replace(R.id.context, fragment)
        fragmentTransition.commit()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_gallery -> {
//                type = "福利"
            }
            R.id.nav_android -> {
                val fragment = ArticleListFragment()
                fragment.type = "Android"
                replaceFragment(fragment)
            }
            R.id.nav_ios -> {
                val fragment = ArticleListFragment()
                fragment.type = "iOS"
                replaceFragment(fragment)
            }
            R.id.nav_other -> {
                val fragment = ArticleListFragment()
                fragment.type = "拓展资源"
                replaceFragment(fragment)
            }
            R.id.nav_web -> {
                val fragment = ArticleListFragment()
                fragment.type = "前端"
                replaceFragment(fragment)
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
