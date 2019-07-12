package cn.abr.common.base

import android.app.Application
import android.content.Context

import cn.abr.common.util.ConstantUtil
import cn.abr.common.util.DensityUtil
import com.alibaba.android.arouter.launcher.ARouter


internal class BaseAppDelegate(private val mApplication: Application) : AppInterface {

    override fun initLocalOptions() {
        ConstantUtil.init(mApplication)     //全局Utils
        DensityUtil.setDensity(mApplication,375f)
        InitializeService.start(mApplication)   //初始化服务Service
    }

    override fun initSDK() {
        initARouter()
    }

    private fun initARouter() {
        if (ConstantUtil.isAppDebug()) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(mApplication); // 尽可能早，推荐在Application中初始化
    }

}
