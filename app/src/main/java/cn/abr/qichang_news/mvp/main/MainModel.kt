package cn.abr.qichang_news.mvp.main

import cn.abr.common.entity.Article
import cn.abr.common.net.api.Repository
import io.reactivex.Flowable
import javax.inject.Inject

/**
 *
 * 版权：汽场(北京)信息科技有限公司 版权所有
 *
 * 作者：时志邦
 *
 * 创建日期：2019/7/11/011
 *
 * 描述：
 *
 */
class MainModel
@Inject
constructor() : MainContract.Model() {

    override fun getArticle(id: String): Flowable<Article> {
        return Repository.getArticle(id,false)
    }
}