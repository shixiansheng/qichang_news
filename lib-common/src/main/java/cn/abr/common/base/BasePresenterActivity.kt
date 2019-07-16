package cn.abr.inabr.base


import android.os.Bundle
import cn.abr.common.base.BaseActivity
import dagger.Lazy
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import leakcanary.LeakSentry
import javax.inject.Inject
import javax.inject.Named


abstract class BasePresenterActivity<P : BasePresenter<*, *>> : BaseActivity(), HasAndroidInjector {

    @Inject
    internal lateinit var mPresenter: Lazy<P>

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>


    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return androidInjector
    }

    override fun onDestroy() {
        super.onDestroy()
        // mPresenter.value.detach()
        LeakSentry.refWatcher.watch(mPresenter)
        LeakSentry.refWatcher.watch(this)
    }

    protected fun getPresenter(): P {
        return mPresenter.get()
    }
}
/*
//记录用户首次点击返回键的时间
private var firstTime: Long = 0

//双击退出
override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
    if (keyCode == KeyEvent.KEYCODE_BACK && event.action === KeyEvent.ACTION_DOWN) {
        if (System.currentTimeMillis() - firstTime > 2000) {
            Toast.makeText(this@MainActivity, "再按一次退出程序!", Toast.LENGTH_SHORT).show()
            firstTime = System.currentTimeMillis()
        } else {
            finish()
            System.exit(0)
        }
        return true
    }
    return super.onKeyDown(keyCode, event)
}*/
/*inline fun AppCompatActivity.showDialog(settings: DialogFragment.() -> Unit) : DialogFragment {
    val dialog = DialogFragment()
    dialog.apply(settings)
    val ft = this.supportFragmentManager.beginTransaction()
    val prev = this.supportFragmentManager.findFragmentByTag("dialog")
    if (prev != null) {
        ft.remove(prev)
    }
    ft.addToBackStack(null)
    dialog.show(ft, "dialog")
    return dialog
}*/
