//package com.example.andrew65appstask.unused;
//
//import android.app.Fragment;
//import android.content.Context;
//import android.content.Intent;
//import android.util.Log;
//
//import com.example.andrew65appstask.app.App;
//import com.example.andrew65appstask.base.SingleFragmentActivity1;
//import com.example.andrew65appstask.dagger.SpecialtyComponent;
//import com.example.andrew65appstask.ui.fragment.SpecialtyFragment;
//
//public class SpecialtyActivity extends SingleFragmentActivity1<SpecialtyComponent> {
//
//    private static final String TAG = "SpecialtyActivity";
//
//    public SpecialtyActivity() {
//        super(SpecialtyComponent.class);
//    }
//
//    public static Intent newIntent(Context context) {
//        Intent intent = new Intent(context, SpecialtyActivity.class);
//        return intent;
//    }
//
//    @Override
//    protected Fragment createFragment() {
//        Log.d(TAG, "createFragment");
//        return SpecialtyFragment.newInstance();
//    }
//
//    @Override
//    protected SpecialtyComponent createComponent() {
//        return ((App) getApplication()).getSpecialtyComponent();
//    }
//
//    @Override
//    protected void releaseComponent() {
//        ((App) getApplication()).releaseSpecialtyComponent();
//    }
//}
