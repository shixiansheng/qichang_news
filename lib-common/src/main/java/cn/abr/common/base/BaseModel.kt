package cn.abr.inabr.base


import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import okhttp3.MediaType
import okhttp3.RequestBody


/**
 * @author 时志邦
 * @Description: ${TODO}(用一句话描述该文件做什么)
 */

open class BaseModel {


    fun createBody(json: String) =
        RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json)

}
