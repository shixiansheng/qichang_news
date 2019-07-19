package cn.abr.qichang_news.mvp.login

import cn.abr.common.entity.Article
import cn.abr.common.net.api.Repository
import io.reactivex.Flowable
import javax.inject.Inject

class LoginModel @Inject constructor() : LoginContract.Model() {
    override fun getArticle(id: String): Flowable<Article> = Repository.getArticle(id,false)
}
