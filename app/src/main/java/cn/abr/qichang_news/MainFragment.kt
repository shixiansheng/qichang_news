package cn.abr.qichang_news

import android.view.View
import cn.abr.common.base.BasePresenterFragment

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

    override fun onArticleResult(json: String) {

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
        get() = R.layout.activity_main

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
