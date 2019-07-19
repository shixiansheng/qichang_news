package cn.abr.qichang_news.dagger

import cn.abr.common.base.BaseFragmentComponent
import cn.abr.qichang_news.dagger.module.FragmentModule
import cn.abr.qichang_news.ui.fragment.MainFragment
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
@Module(subcomponents = [BaseFragmentComponent::class])
abstract class AllFragmentsModule {

    @ContributesAndroidInjector(modules = [FragmentModule::class])
     abstract fun contributeMainFragmentInjector(): MainFragment
}
