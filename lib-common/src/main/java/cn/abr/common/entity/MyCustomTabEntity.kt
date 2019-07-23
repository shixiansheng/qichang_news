package cn.abr.common.entity

import com.flyco.tablayout.listener.CustomTabEntity

/**
 * .
 * Created by Administrator on 2018/4/18/018.
 */
    class MyCustomTabEntity(var tabUnselectedIconId: Int?, var tabSelectedIconId: Int?, var tabTitleId: String?) : CustomTabEntity {


        override fun getTabUnselectedIcon(): Int {
            return this.tabUnselectedIconId!!
        }

        override fun getTabSelectedIcon(): Int {
            return this.tabSelectedIconId!!
        }

        override fun getTabTitle(): String {
            return this.tabTitleId!!

        }
}