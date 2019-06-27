package cn.abr.common.base

import android.app.Application
import cn.abr.common.util.ConstantUtil

/**
 * .
 * Created by Administrator on 2018/4/12/012.
 */

abstract class BaseApp : Application() {


    override fun onCreate() {
        super.onCreate()
        val appInterface = BaseAppDelegate(this)
        appInterface.onCreate()
        init()
    }

    abstract fun init()
}
