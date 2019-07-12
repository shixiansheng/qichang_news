package cn.abr.common.base

import android.content.Context
import cn.abr.inabr.base.BasePresenter
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection
import leakcanary.LeakSentry
import javax.inject.Inject

/**
 *
 * 版权：汽场(北京)信息科技有限公司 版权所有
 *
 * 作者：时志邦
 *
 * 创建日期：2019/7/12/012
 *
 * 描述：
 *
 */
abstract class BasePresenterFragment<P : BasePresenter<*, *>> : LazyLoadFragment() ,HasAndroidInjector{

    @Inject
    lateinit var mPresenter: P

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return androidInjector
    }

    override fun initImmersionBar() {

    }

    override fun initData() {
    }

    override fun onDestroy() {
        super.onDestroy()
        LeakSentry.refWatcher.watch(mPresenter)
        LeakSentry.refWatcher.watch(this)
    }
}