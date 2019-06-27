package cn.abr.common.base

/**
 *
 * 版权：汽场(北京)信息科技有限公司 版权所有
 *
 * 作者：时志邦
 *
 * 创建日期：2019/6/11/011
 *
 * 描述：
 *
 */
internal interface AppInterface {

    fun initLocalOptions()
    fun initSDK()

    fun onCreate() {
        initLocalOptions()
        initSDK()
    }
}