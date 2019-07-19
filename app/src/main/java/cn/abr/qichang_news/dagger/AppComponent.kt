package cn.abr.qichang_news.dagger


import cn.abr.qichang_news.App
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule

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
@Component(modules = [AndroidInjectionModule::class, AndroidSupportInjectionModule::class, AllActivitysModule::class, AllFragmentsModule::class])
interface AppComponent {
    fun inject(application: App)
}
