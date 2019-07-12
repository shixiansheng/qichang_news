package cn.abr.lib.status_layout

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity


class MainActivity_Status : AppCompatActivity(), View.OnClickListener {

    private var multipleStatusLayout: MultipleStatusLayout? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<View>(R.id.empty).setOnClickListener(this)
        findViewById<View>(R.id.error).setOnClickListener(this)
        findViewById<View>(R.id.loading).setOnClickListener(this)
        findViewById<View>(R.id.content).setOnClickListener(this)
        findViewById<View>(R.id.fragment).setOnClickListener(this)

        multipleStatusLayout = MultipleStatusLayout.Builder(this)
            //.include()  整体布局使用
            .include(R.id.refreshLayout)//单个控件使用
            .setEmptyView(R.layout.empty)
            .setLoadingView(R.layout.loading)
            .setErrorView(R.layout.error)
            .setOnStatusRetryListener(object : OnStatusRetryListener {
                override fun onRetry() {
                    multipleStatusLayout?.showLoading()
                    Handler().postDelayed({
                        multipleStatusLayout?.showContent()
                    }, 1000)
                }

            })
            .build()

        multipleStatusLayout!!.showLoading()
        Handler().postDelayed({
            multipleStatusLayout?.showContent()
        }, 2000)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.empty -> multipleStatusLayout!!.showEmpty()
            R.id.content -> multipleStatusLayout!!.showContent()
            R.id.error -> multipleStatusLayout!!.showError()
            R.id.loading -> multipleStatusLayout!!.showLoading()
            R.id.fragment -> startActivity(Intent(this@MainActivity_Status, Main2Activity_Status::class.java))
        }
    }
}
