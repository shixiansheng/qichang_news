package cn.abr.qichang_news

import dagger.Module
import dagger.Provides
import javax.inject.Named

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
@Module
class FragmentModule {


    @Provides
    fun provideFragmentMainView(mainFragment: MainFragment): MainContract.View {
        return mainFragment
    }



    @Provides
    fun provideMainModel(mainModel: MainModel): MainContract.Model {
        return mainModel
    }
}
