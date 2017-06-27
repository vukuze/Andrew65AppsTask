//package com.example.andrew65appstask.unused;
//
//import android.os.Bundle;
//
//import com.example.andrew65appstask.app.ComponentReflectionInjector;
//import com.example.andrew65appstask.app.Injector;
//import com.example.andrew65appstask.base.BaseComponent;
//import com.example.andrew65appstask.ui.activity.SingleFragmentActivity;
//
///**
// * Базовый абстрактный класс для Activity, реализующий DaggerComponent
// */
//public abstract class SingleFragmentActivity1<C extends BaseComponent> extends SingleFragmentActivity implements Injector {
//
//    protected C activityComponent;
//    protected ComponentReflectionInjector<C> injector;
//    private Class<C> componentClass;
//
//    protected abstract C createComponent();
//
//    protected abstract void releaseComponent();
//
//    public SingleFragmentActivity1(Class<C> componentClass) {
//        this.componentClass = componentClass;
//    }
//
//    public C getActivityComponent() {
//        return activityComponent;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        activityComponent = createComponent();
//        injector = new ComponentReflectionInjector<>(componentClass, activityComponent);
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        releaseComponent();
//    }
//
//    @Override
//    public void inject(Object target) {
//        injector.inject(target);
//    }
//}
