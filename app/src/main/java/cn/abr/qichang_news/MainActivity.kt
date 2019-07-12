package cn.abr.qichang_news

import android.view.View
import android.widget.Toast
import cn.abr.inabr.base.BasePresenterActivity
import javax.inject.Inject

class MainActivity : BasePresenterActivity<MainPresenter>(), MainContract.View {


    @Inject
   lateinit var name: String


    override val layoutId: Int
        get() = R.layout.activity_main

    override fun initView() {
        supportFragmentManager.beginTransaction().add(R.id.root_layout,MainFragment()).commit()
    }

    override fun initData() {
        mPresenter.getArticle()
    }

    override fun initListener() {
    }



    override fun getClickViewArray(): Array<View>? {
        return null
    }

    override fun onClick(v: View?) {
    }

    override fun onArticleResult(json: String) {
        println(json)
    }

    override fun getArticleId(): String {
        return "A582397487372"
    }

    override fun showProgress() {
    }

    override fun hideProgress() {
    }
}
