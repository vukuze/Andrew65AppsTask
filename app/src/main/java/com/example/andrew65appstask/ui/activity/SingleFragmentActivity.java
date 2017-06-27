//package com.example.andrew65appstask.ui.activity;
//
//import android.os.Bundle;
//import android.support.annotation.LayoutRes;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentManager;
//import android.support.v7.app.AppCompatActivity;
//import android.util.Log;
//
//import com.example.andrew65appstask.R;
//import com.example.andrew65appstask.ui.BackButtonListener;
//
///**
// * Базовый абстрактный класс для Activity с одним Fragment
// */
//public abstract class SingleFragmentActivity extends AppCompatActivity {
//
//    protected abstract Fragment createFragment();
//
//    @LayoutRes
//    protected int getLayoutResId() {
//        return R.layout.activity_fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        Log.d(this.getClass().getSimpleName(), "onCreate");
//        setContentView(getLayoutResId());
//        FragmentManager manager = getSupportFragmentManager();
//        Fragment fragment = manager.findFragmentById(R.id.fragmentContainer);
//
//        if (fragment == null) {
//            fragment = createFragment();
//            manager.beginTransaction()
//                    .add(R.id.fragmentContainer, fragment)
//                    .commit();
//        }
//    }
//
//    @Override
//    public void onBackPressed() {
//        Log.d(this.getClass().getSimpleName(), "onBackPressed");
//        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);
//        if (fragment != null &&
//                fragment instanceof BackButtonListener &&
//                ((BackButtonListener) fragment).onBackPressed()) {
//            return;
//        } else {
//            super.onBackPressed();
//        }
//    }
//}
