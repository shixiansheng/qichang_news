package cn.abr.qichang_news

import cn.abr.common.entity.Article
import cn.abr.common.net.RxHttpUtil
import cn.abr.inabr.net.HttpSubscriber
import okhttp3.ResponseBody
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

    override var mView: MainContract.View
    override var model: MainContract.Model

    @Inject
    constructor(mView: MainContract.View, model: MainContract.Model) {
        this.mView = mView
        this.model = model
    }

    override fun getArticle() {
        model.getArticle(mView.getArticleId())
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