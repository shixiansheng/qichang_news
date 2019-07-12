package cn.abr.common.net.callback


import android.widget.Toast
import cn.abr.common.util.ConstantUtil

class ErrorHandler private constructor() : ErrorListener {

    override fun handleError(e: Throwable) {

        Toast.makeText(ConstantUtil.getAPPContext(), e.message, Toast.LENGTH_LONG).show()
    }

    companion object {
        val Instance by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
            ErrorHandler()
        }
    }
}