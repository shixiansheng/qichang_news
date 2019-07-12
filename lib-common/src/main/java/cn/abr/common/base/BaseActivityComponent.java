package cn.abr.common.base;

import cn.abr.common.base.BaseActivity;
import dagger.Subcomponent;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;

/**
 * 版权：汽场(北京)信息科技有限公司 版权所有
 * <p>
 * 作者：时志邦
 * <p>
 * 创建日期：2019/7/9/009
 * <p>
 * 描述：
 */
@Subcomponent(modules = {
        AndroidInjectionModule.class
})
public interface BaseActivityComponent extends AndroidInjector<BaseActivity> {

    @Subcomponent.Factory
    abstract class Factory implements AndroidInjector.Factory<BaseActivity> {
    }

}
