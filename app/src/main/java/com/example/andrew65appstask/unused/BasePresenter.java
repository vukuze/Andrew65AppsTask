//package com.example.andrew65appstask.unused;
//
//import android.os.Bundle;
//
//import icepick.Icepick;
//import nucleus5.presenter.RxPresenter;
//
///**
// * Базовый Presenter с шаблонным кодом
// */
//
//public class BasePresenter<T> extends RxPresenter<T> {
//
//    @Override
//    protected void onCreate(Bundle savedState) {
//        super.onCreate(savedState);
//        Icepick.restoreInstanceState(this, savedState);
//    }
//
//    @Override
//    protected void onSave(Bundle state) {
//        super.onSave(state);
//        Icepick.saveInstanceState(this, state);
//    }
//}
