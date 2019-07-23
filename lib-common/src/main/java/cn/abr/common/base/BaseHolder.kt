package cn.abr.common.base

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Administrator on 2017/10/20.
 */

abstract class BaseHolder<M>(parent: ViewGroup, @LayoutRes resId: Int) : RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(resId, parent, false)) {

    /**
     * 获取context实例
     * @return
     */
    protected val context: Context
        get() = itemView.context

    /**
     * 绑定数据
     */
    abstract fun bindData(module: M, position: Int)
    /**
     * 绑定数据
     */
}
