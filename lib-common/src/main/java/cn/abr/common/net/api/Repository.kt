package cn.abr.common.net.api

import cn.abr.common.entity.Article
import cn.abr.common.net.HttpUtil
import cn.abr.common.net.RxHttpUtil
import cn.abr.inabr.net.HttpSubscriber
import io.reactivex.Flowable
import io.reactivex.disposables.Disposable
import io.rx_cache2.*
import okhttp3.ResponseBody

/**
 *
 * 版权：汽场(北京)信息科技有限公司 版权所有
 *
 * 作者：时志邦
 *
 * 创建日期：2019/7/17/025
 *
 * 描述：缓存管理
 *
 */

object Repository {
    private val cacheProviders: CacheProviders = HttpUtil.cacheProviders
    private val restApi = HttpUtil.apis

    fun getArticle(
        newsId: String,
        update: Boolean
    ): Flowable<Article> {
        return cacheProviders.getArticle(
            restApi.getArticleContent(newsId).compose(RxHttpUtil.handleResult()),
            DynamicKey(newsId),
            EvictDynamicKey(update)
        ).compose(RxHttpUtil.rxSchedulerHelper())
    }

}
