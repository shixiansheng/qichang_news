package cn.abr.inabr.base


/**
 * @author 时志邦
 * @Description: ${TODO}(用一句话描述该文件做什么)
 */

interface BaseView {
    fun showProgress()
    fun hideProgress()

    fun onFailure(t: Throwable){

    }
}
