package cn.abr.qichang_news.mvp.login

import javax.inject.Inject

class LoginPresenter @Inject constructor() : LoginContract.Presenter {
    lateinit var mView: LoginContract.View

    lateinit var mModel: LoginContract.Model

    override fun loadRepositories() {
    }

    override fun getArticle() {
    }
}
