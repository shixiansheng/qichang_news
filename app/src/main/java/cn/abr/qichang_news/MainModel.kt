package cn.abr.qichang_news

import cn.abr.common.net.HttpUtil
import cn.abr.common.net.RxHttpUtil
import cn.abr.inabr.net.HttpSubscriber
import io.reactivex.Flowable
import okhttp3.ResponseBody
import javax.inject.Inject

/**
 *
 * 版权：汽场(北京)信息科技有限公司 版权所有
 *
 * 作者：时志邦
 *
 * 创建日期：2019/7/11/011
 *
 * 描述：
 *
 */
class MainModel
@Inject
constructor() : MainContract.Model() {

    override fun getArticle(id: String): Flowable<ResponseBody> {
        return HttpUtil.apis.getArticleContent(id).compose(RxHttpUtil.rxSchedulerHelper())
    }
}