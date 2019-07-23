package cn.abr.qichang_news.dagger.module

import androidx.fragment.app.Fragment
import cn.abr.common.entity.MyCustomTabEntity
import cn.abr.main.event.fragment.EventFragment
import cn.abr.main.home.debug.HomeFragment
import cn.abr.main_mine.MineFragment
import cn.abr.main_news.NewsFragment
import cn.abr.qichang_news.R
import cn.abr.qichang_news.ui.activity.MainActivity
import cn.abr.qichang_news.mvp.main.MainContract
import cn.abr.qichang_news.mvp.main.MainModel
import com.flyco.tablayout.listener.CustomTabEntity
import dagger.Module
import dagger.Provides

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
@Module(includes = [NameModule::class])
class MainModule {

    @Provides
    fun provideMainView(activity: MainActivity): MainContract.View {
        return activity
    }


    @Provides
    fun provideMainModel(mainModel: MainModel): MainContract.Model {
        return mainModel
    }

    @Provides
    fun provideFragment(): ArrayList<Fragment> {
        return arrayListOf(
            HomeFragment.newInstance("", ""),
            NewsFragment.newInstance("", ""),
            EventFragment.newInstance("", ""),
            MineFragment.newInstance("", "")
        )
    }

    @Provides
    fun provideTab(): ArrayList<CustomTabEntity> {
        return arrayListOf(
            MyCustomTabEntity(
                R.mipmap.ic_launcher,
                R.mipmap.ic_launcher,
                "找车"
            ),
            MyCustomTabEntity(
                R.mipmap.ic_launcher,
                R.mipmap.ic_launcher,
                "活动"
            ),
            MyCustomTabEntity(
                R.mipmap.ic_launcher,
                R.mipmap.ic_launcher,
                "资讯"
            ),
            MyCustomTabEntity(
                R.mipmap.ic_launcher,
                R.mipmap.ic_launcher,
                "我的"
            )
        )
    }

}
