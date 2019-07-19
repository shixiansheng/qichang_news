package cn.abr.qichang_news.dagger.module

import dagger.Module
import dagger.Provides

/**
 *
 * 版权：汽场(北京)信息科技有限公司 版权所有
 *
 * 作者：时志邦
 *
 * 创建日期：2019/7/16/016
 *
 * 描述：
 *
 */
@Module
class NameModule {

    @Provides
    fun provideName(): String {

        return "cc"
    }
}