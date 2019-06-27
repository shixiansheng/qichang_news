package cn.abr.common.base

import android.app.Application
import android.content.Context

import cn.abr.common.util.ConstantUtil


internal class BaseAppDelegate(private val mApplication: Application) : AppInterface {

    override fun initLocalOptions() {
        ConstantUtil.init(mApplication)     //全局Utils
        InitializeService.start(mApplication)   //初始化服务Service
    }

    override fun initSDK() {

    }

}
