package cn.abr.qichang_news;

import cn.abr.common.base.BaseFragmentComponent;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * 版权：汽场(北京)信息科技有限公司 版权所有
 * <p>
 * 作者：时志邦
 * <p>
 * 创建日期：2019/7/9/009
 * <p>
 * 描述：
 */
@Module(subcomponents = {
        BaseFragmentComponent.class
})
public abstract class AllFragmentsModule {

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract MainFragment contributeMainFragmentInjector();
}
