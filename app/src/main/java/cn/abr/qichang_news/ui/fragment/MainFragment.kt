package cn.abr.qichang_news.ui.fragment

import android.view.View
import cn.abr.common.base.BasePresenterFragment
import cn.abr.common.entity.Article
import cn.abr.qichang_news.R
import cn.abr.qichang_news.mvp.main.MainContract
import cn.abr.qichang_news.mvp.main.MainPresenter

/**
 *
 * 版权：汽场(北京)信息科技有限公司 版权所有
 *
 * 作者：时志邦
 *
 * 创建日期：2019/7/12/012
 *
 * 描述：
 **/


class MainFragment : BasePresenterFragment<MainPresenter>(), MainContract.View {

    override fun onArticleResult(article: Article) {

    }

    override fun getArticleId(): String {
        return ""
    }

    override fun showProgress() {
    }

    override fun hideProgress() {
    }

    override fun loadData() {
    }

    override val layoutId: Int
        get() = R.layout.app_activity_main

    override fun initView() {
        println("aaaaaa" + getPresenter())
    }

    override fun initListener() {
    }

    override fun getClickViewArray(): Array<View>? {
        return null
    }

    override fun onClick(v: View?) {
    }
}
