package cn.abr.common.base


import android.os.Bundle
import cn.abr.common.`interface`.InitInterface
import cn.abr.common.`interface`.VisibilityInterface
import cn.abr.common.util.ActivityManager
import com.trello.rxlifecycle3.components.support.RxAppCompatActivity


abstract class BaseActivity : RxAppCompatActivity(), InitInterface, VisibilityInterface {


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