package cn.abr.common.base

/**
 * 版权：汽场(北京)信息科技有限公司 版权所有
 *
 *
 * 作者：时志邦
 *
 *
 * 创建日期：2019/7/12/012
 *
 *
 * 描述：
 */
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.abr.common.`interface`.InitInterface
import com.gyf.immersionbar.components.SimpleImmersionFragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment
import javax.inject.Inject


abstract class BaseFragment : SimpleImmersionFragment(), InitInterface {
    protected lateinit var mRootView: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mRootView = inflater.inflate(layoutId, container, false)
        return mRootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
        initData()
        initListener()
        setOnClickListener(*getClickViewArray() ?: return)
    }

}
