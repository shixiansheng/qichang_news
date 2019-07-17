package cn.abr.qichang_news

import cn.abr.common.entity.Article
import cn.abr.inabr.base.BaseModel
import cn.abr.inabr.base.BasePresenter
import cn.abr.inabr.base.BaseView
import io.reactivex.Flowable
import okhttp3.ResponseBody

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
interface MainContract {


    abstract class Model : BaseModel() {
        abstract fun getArticle(id: String): Flowable<Article>
    }

    interface View : BaseView {
        fun onArticleResult(article: Article)
        fun getArticleId(): String
    }

    interface Presenter : BasePresenter<View, Model> {
        fun getArticle()
    }
}