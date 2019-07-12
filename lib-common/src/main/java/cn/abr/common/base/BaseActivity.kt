package cn.abr.common.base


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cn.abr.common.`interface`.BaseActivityInterface
import cn.abr.common.`interface`.VisibilityInterface
import cn.abr.common.util.ActivityManager
import com.trello.rxlifecycle3.components.support.RxAppCompatActivity
import com.trello.rxlifecycle3.components.support.RxFragment
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity


abstract class BaseActivity : RxAppCompatActivity(), BaseActivityInterface, VisibilityInterface {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initOptions()
    }

    private fun initOptions() {
        setContentView(layoutId)
        initView()
        initData()
        initListener()
        ActivityManager.addActivity(this)
        setOnClickListener(*getClickViewArray() ?: return)
    }


    override fun onDestroy() {
        super.onDestroy()
        ActivityManager.finishActivity(this)
        //LeakSentry.refWatcher.watch(this)
    }
}