package cn.abr.inabr.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.lang.ref.WeakReference


/**
 * @author 时志邦
 * @Description: ${TODO}(用一句话描述该文件做什么)
 */

interface BasePresenter<V : BaseView, M : BaseModel> {


    fun loadRepositories()


}
