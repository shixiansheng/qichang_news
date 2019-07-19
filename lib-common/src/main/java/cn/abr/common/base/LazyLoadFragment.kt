package cn.abr.common.base

/**
 *
 * 版权：汽场(北京)信息科技有限公司 版权所有
 *
 * 作者：时志邦
 *
 * 创建日期：2019/7/12/012
 *
 * 描述：
 *
 */

import android.os.Bundle
import androidx.annotation.Nullable

abstract class LazyLoadFragment : BaseFragment() {
    private var isViewCreated: Boolean = false // 界面是否已创建完成
    private var isVisibleToUser: Boolean = false // 是否对用户可见
    private var isDataLoaded: Boolean = false // 数据是否已请求, isNeedReload()返回false的时起作用
    private var isHided = true // 记录当前fragment的是否隐藏

    /**
     * ViewPager场景下，判断父fragment是否可见
     *
     * @return
     */
    private val isParentVisible: Boolean
        get() {
            val fragment = getParentFragment()
            return fragment == null || fragment is LazyLoadFragment && (fragment as LazyLoadFragment).isVisibleToUser
        }

    /**
     * fragment再次可见时，是否重新请求数据，默认为flase则只请求一次数据
     *
     * @return
     */
    protected val isNeedReload: Boolean
        get() = false

    /**
     * show()、hide()场景下，父fragment是否隐藏
     *
     * @return
     */
    private val isParentHidden: Boolean
        get() {
            val fragment = getParentFragment()
            if (fragment == null) {
                return false
            } else if (fragment is LazyLoadFragment && !(fragment as LazyLoadFragment).isHided) {
                return false
            }
            return true
        }

    // 实现具体的数据请求逻辑
    protected abstract fun loadData()


    /**
     * 使用ViewPager嵌套fragment时，切换ViewPager回调该方法
     *
     * @param isVisibleToUser
     */
    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)

        this.isVisibleToUser = isVisibleToUser

        tryLoadData()
    }


    override fun onActivityCreated(@Nullable savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        isViewCreated = true
        tryLoadData()
    }

    /**
     * 使用show()、hide()控制fragment显示、隐藏时回调该方法
     *
     * @param hidden
     */
    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        isHided = hidden
        if (!hidden) {
            tryLoadData1()
        }
    }

    /**
     * ViewPager场景下，当前fragment可见，如果其子fragment也可见，则尝试让子fragment加载请求
     */
    private fun dispatchParentVisibleState() {
        val fragmentManager = getChildFragmentManager()
        val fragments = fragmentManager.getFragments()
        if (fragments.isEmpty()) {
            return
        }
        for (child in fragments) {
            if (child is LazyLoadFragment && (child as LazyLoadFragment).isVisibleToUser) {
                (child as LazyLoadFragment).tryLoadData()
            }
        }
    }

    /**
     * ViewPager场景下，尝试请求数据
     */
    private fun tryLoadData() {
        if (isViewCreated && isVisibleToUser && isParentVisible && (isNeedReload || !isDataLoaded)) {
            loadData()
            isDataLoaded = true
            dispatchParentVisibleState()
        }
    }

    /**
     * show()、hide()场景下，当前fragment没隐藏，如果其子fragment也没隐藏，则尝试让子fragment请求数据
     */
    private fun dispatchParentHiddenState() {
        val fragmentManager = getChildFragmentManager()
        val fragments = fragmentManager.getFragments()
        if (fragments.isEmpty()) {
            return
        }
        for (child in fragments) {
            if (child is LazyLoadFragment && !child.isHided) {
                child.tryLoadData1()
            }
        }
    }

    /**
     * show()、hide()场景下，尝试请求数据
     */
    private fun tryLoadData1() {
        if (!isParentHidden && (isNeedReload || !isDataLoaded)) {
            loadData()
            isDataLoaded = true
            dispatchParentHiddenState()
        }
    }

    override fun onDestroy() {
        isViewCreated = false
        isVisibleToUser = false
        isDataLoaded = false
        isHided = true
        super.onDestroy()
    }
}