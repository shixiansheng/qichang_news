package cn.abr.inabr.net.api

import cn.abr.common.base.BaseEntity
import cn.abr.common.entity.Article
import io.reactivex.Flowable
import io.reactivex.Observable
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.http.*

/**
 * @author 时志邦
 * @Description: ${TODO}(用一句话描述该文件做什么)
 */

interface APIService {

    @GET("https://api.qichangv.com/v2/article/{newsId}")
    fun getArticleContent(@Path("newsId") id: String): Flowable<BaseEntity<Article>>
}
