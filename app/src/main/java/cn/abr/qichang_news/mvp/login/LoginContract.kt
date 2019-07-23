package cn.abr.qichang_news.mvp.login

import cn.abr.common.entity.Article
import cn.abr.inabr.base.BaseModel
import cn.abr.inabr.base.BasePresenter
import cn.abr.inabr.base.BaseView
import io.reactivex.Flowable

interface LoginContract {
    abstract class Model : BaseModel() {
        abstract fun getArticle(id: String): Flowable<Article>
    }

    interface View : BaseView {
        fun getArticleId(): String

        fun getArticleId(article: Article): String
    }

    interface Presenter : BasePresenter<View, Model> {
        fun getArticle()
    }
}
