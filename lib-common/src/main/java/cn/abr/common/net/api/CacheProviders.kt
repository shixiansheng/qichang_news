package cn.abr.common.net.api

import cn.abr.common.entity.Article
import io.reactivex.Flowable
import io.rx_cache2.*
import okhttp3.ResponseBody
import java.util.concurrent.TimeUnit

/**
 *
 * 版权：汽场(北京)信息科技有限公司 版权所有
 *
 * 作者：时志邦
 *
 * 创建日期：2019/4/25/025
 *
 * 描述：
 *
 */
interface CacheProviders {
    /* @LifeCache(duration = 1, timeUnit = TimeUnit.MINUTES)
     @ProviderKey("hotBrands")
     fun hotBrands(
         hotBrands: Flowable<List<HotBrandsEntity>>
         , dynamicKey: DynamicKey
         , evictProvider: EvictProvider
     ): Flowable<List<HotBrandsEntity>>*/

    @LifeCache(duration = 1, timeUnit = TimeUnit.MINUTES)
    @ProviderKey("getArticle")
    fun getArticle(
        article: Flowable<Article>
        , dynamicKey: DynamicKey
        , evictProvider: EvictProvider
    ): Flowable<Article>
}