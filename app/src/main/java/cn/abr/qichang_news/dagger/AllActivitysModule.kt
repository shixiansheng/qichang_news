package cn.abr.qichang_news.dagger


import cn.abr.common.base.BaseActivityComponent
import cn.abr.qichang_news.ui.activity.MainActivity
import cn.abr.qichang_news.dagger.module.MainModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

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
@Module(subcomponents = [BaseActivityComponent::class])
abstract class AllActivitysModule {

    @ContributesAndroidInjector(modules = [MainModule::class])
     abstract fun contributeMainActivityInjector(): MainActivity
}
