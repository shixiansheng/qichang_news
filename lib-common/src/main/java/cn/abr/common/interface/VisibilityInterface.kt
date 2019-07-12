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
 * 描述：多view  状态管理
 *
 */
interface VisibilityInterface {

    fun GONE(vararg v: View): Unit {
        v.forEach {
            it.visibility = View.GONE
        }
    }

    fun VISIBLE(vararg v: View): Unit {
        v.forEach {
            it.visibility = View.VISIBLE
        }
    }

    fun INVISIBLE(vararg v: View): Unit {
        v.forEach {
            it.visibility = View.INVISIBLE
        }
    }
}