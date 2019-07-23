package cn.abr.qichang_news

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import cn.abr.common.base.BaseHolder
import me.drakeet.multitype.ItemViewBinder

/**
 * 版权：汽场(北京)信息科技有限公司 版权所有
 *
 *
 * 作者：时志邦
 *
 *
 * 创建日期：2019/7/22/022
 *
 *
 * 描述：
 */
class AViewBinder : ItemViewBinder<A, AViewBinder.ViewHolder>() {

    override fun onCreateViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): ViewHolder {
        return ViewHolder(parent, TODO())
    }

    override fun onBindViewHolder(holder: ViewHolder, a: A) {

    }

    inner class ViewHolder(parent: ViewGroup, resId: Int) : BaseHolder<A>(parent, resId) {

        override fun bindData(module: A, position: Int) {

        }
    }
}
