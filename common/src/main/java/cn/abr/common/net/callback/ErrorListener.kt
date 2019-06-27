package cn.abr.common.net.callback


interface ErrorListener {
    fun handleError(e: Throwable)
}