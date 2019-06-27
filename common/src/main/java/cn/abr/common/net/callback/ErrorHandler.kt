package cn.abr.common.net.callback


import cn.abr.common.util.ConstantUtil

class ErrorHandler private constructor() : ErrorListener {

    override fun handleError(e: Throwable) {
        ConstantUtil.getAPPContext()
    }

    companion object {
        val Instance by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
            ErrorHandler()
        }
    }
}