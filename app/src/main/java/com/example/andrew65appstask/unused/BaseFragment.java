//package com.example.andrew65appstask.unused;
//
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.view.View;
//
//import com.example.andrew65appstask.base.SingleFragmentActivity1;
//
//import butterknife.ButterKnife;
//import butterknife.Unbinder;
//import icepick.Icepick;
//import nucleus5.factory.PresenterFactory;
//import nucleus5.presenter.Presenter;
//import nucleus5.view.NucleusFragment;
//
///**
// * Базовый абстрактный класс для Fragment, связанных с Presenter
// */
//public abstract class BaseFragment<P extends Presenter> extends NucleusFragment<P> {
//
//    private Unbinder unbinder;
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        final PresenterFactory<P> superFactory = super.getPresenterFactory();
//        setPresenterFactory(superFactory == null ? null : (PresenterFactory<P>) () -> {
//            P presenter = superFactory.createPresenter();
//            ((SingleFragmentActivity1) getActivity()).inject(presenter);
//            return presenter;
//        });
//        super.onCreate(savedInstanceState);
//        Icepick.restoreInstanceState(this, savedInstanceState);
//    }
//
//    @Override
//    public void onSaveInstanceState(Bundle bundle) {
//        super.onSaveInstanceState(bundle);
//        Icepick.saveInstanceState(this, bundle);
//    }
//
//    @Override
//    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        unbinder = ButterKnife.bind(this, view);
//    }
//
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        unbinder.unbind();
//    }
//}
