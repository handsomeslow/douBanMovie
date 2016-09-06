package com.jx.RxMovie;

import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;

/**
 * Created by junxu on 2016/7/19.
 */
public class BaseActivity extends AppCompatActivity {
/*
    @Override
    protected void onStop() {
        Log.v("XXXXXXXXXXXXX", MethodsForMovie.getInstance().subscriptions.isUnsubscribed()+"");
        MethodsForMovie.getInstance().unSubscribe();
        Log.v("XXXXXXXXXXXXX2",MethodsForMovie.getInstance().subscriptions.isUnsubscribed()+"");
        super.onStop();
    }
*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.actionbar_setting,menu);
        return super.onCreateOptionsMenu(menu);
    }
}
