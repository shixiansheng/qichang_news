package cn.abr.qichang_news


import javax.inject.Inject

import cn.abr.common.base.BaseApp
import cn.abr.qichang_news.dagger.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector

/**
 * 版权：汽场(北京)信息科技有限公司 版权所有
 *
 *
 * 作者：时志邦
 *
 *
 * 创建日期：2019/7/9/009
 *
 *
 * 描述：
 */
class App : BaseApp(), HasAndroidInjector {
    @Inject
    @Volatile
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> {
        return androidInjector
    }

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.create().inject(this)
    }

    override fun init() {

    }
}
