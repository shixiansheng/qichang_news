package cn.abr.common.module

import dagger.Module
import dagger.Provides
import me.drakeet.multitype.MultiTypeAdapter

/**
 *
 * 版权：汽场(北京)信息科技有限公司 版权所有
 *
 * 作者：时志邦
 *
 * 创建日期：2019/7/22/022
 *
 * 描述：
 *
 */
@Module
class MultiTypeModule {

    @Provides
    fun provideMultiTypeAdapter(): MultiTypeAdapter {
        return MultiTypeAdapter()
    }


}