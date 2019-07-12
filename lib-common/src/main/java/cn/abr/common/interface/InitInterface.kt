package cn.abr.common.`interface`

import android.view.View

/**
 *
 * 版权：汽场(北京)信息科技有限公司 版权所有
 *
 * 作者：时志邦
 *
 * 创建日期：2019/7/10/010
 *
 * 描述：
 *
 */
interface InitInterface : View.OnClickListener {

    val layoutId: Int
    fun initView()
    fun initData()
    fun initListener()
    fun getClickViewArray(): Array<View>?

    fun setOnClickListener(vararg v: View) {
        v.forEach {
            it.setOnClickListener(this)
        }
    }
}