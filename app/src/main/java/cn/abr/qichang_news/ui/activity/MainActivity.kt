package cn.abr.qichang_news.ui.activity

import android.view.View
import androidx.fragment.app.Fragment
import cn.abr.common.entity.Article
import cn.abr.inabr.base.BasePresenterActivity
import cn.abr.qichang_news.R
import cn.abr.qichang_news.mvp.main.MainContract
import cn.abr.qichang_news.mvp.main.MainPresenter
import com.flyco.tablayout.listener.CustomTabEntity
import kotlinx.android.synthetic.main.app_activity_main.*
import javax.inject.Inject
class MainActivity : BasePresenterActivity<MainPresenter>(), MainContract.View {



    @Inject
    lateinit var fragments: ArrayList<Fragment>

    @Inject
    lateinit var tabs: ArrayList<CustomTabEntity>

    override val layoutId: Int
        get() = R.layout.app_activity_main

    override fun initView() {
    }

    override fun initData() {
        getPresenter().getArticle()
    }

    override fun initListener() {
        main_tab.setTabData(tabs,this@MainActivity,R.id.main_fl,fragments)
    }



    override fun getClickViewArray(): Array<View>? {
        return null
    }

    override fun onClick(v: View?) {
    }

    override fun onArticleResult(article: Article) {
        println(article)
    }

    override fun getArticleId(): String {
        return "A582397487372"
    }

    override fun showProgress() {
    }

    override fun hideProgress() {
    }
}
