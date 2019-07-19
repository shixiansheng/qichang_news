package cn.abr.qichang_news.mvp.main

import cn.abr.common.entity.Article
import cn.abr.common.net.RxHttpUtil
import cn.abr.inabr.net.HttpSubscriber
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
class MainPresenter :
    MainContract.Presenter {

    @Inject
    lateinit var mView: MainContract.View
    @Inject
    lateinit var mModel: MainContract.Model

    @Inject
    constructor() {
    }

    override fun getArticle() {
        mModel.getArticle(mView.getArticleId())
            .compose(RxHttpUtil.bindToLifecycle(mView))
            .subscribeWith(object : HttpSubscriber<Article>() {

                override fun onNext(data: Article) {
                    mView.onArticleResult(data)
                }

                override fun onComplete() {

                }

            })
    }

    override fun loadRepositories() {
    }


}