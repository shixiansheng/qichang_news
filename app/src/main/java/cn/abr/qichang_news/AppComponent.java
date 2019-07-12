package cn.abr.qichang_news;

import dagger.Component;
import dagger.Subcomponent;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * 版权：汽场(北京)信息科技有限公司 版权所有
 * <p>
 * 作者：时志邦
 * <p>
 * 创建日期：2019/7/9/009
 * <p>
 * 描述：
 */
@Component(modules = {AndroidInjectionModule.class,
        AndroidSupportInjectionModule.class, AllActivitysModule.class, AllFragmentsModule.class})
public interface AppComponent {
    void inject(App application);
}
