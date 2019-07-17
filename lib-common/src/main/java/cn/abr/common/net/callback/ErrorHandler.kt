package cn.abr.common.net.callback


import cn.abr.common.net.exception.ApiException
import cn.abr.common.net.exception.ExceptionHandle

class ErrorHandler private constructor() : ErrorListener {

    override fun handleError(e: Throwable) {
        ExceptionHandle.handleExceptions(e,this)
    }

     fun onError(apiException: ApiException){
        //处理错误逻辑
         println(apiException)
    }


    companion object {
        val Instance by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
            ErrorHandler()
        }
    }
}