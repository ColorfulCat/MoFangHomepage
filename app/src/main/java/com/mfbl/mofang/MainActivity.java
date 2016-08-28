package com.mfbl.mofang;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.view.KeyEvent;
import android.view.View;

import com.mfbl.mofang.base.BaseActivity;
import com.mfbl.mofang.ui.CounterActivity;
import com.mfbl.mofang.util.MyToaster;

public class MainActivity extends BaseActivity {

    private long mExitTime;// 返回键 退出时间间隔

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

        findViewById(R.id.tab_counter).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tab_counter:

                Intent intent = new Intent(MainActivity.this, CounterActivity.class);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    MainActivity.this.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, v, "counter").toBundle());
                }else{
                    MainActivity.this.startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_bottom, R.anim.static_animation);
                }
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {

            if ((System.currentTimeMillis() - mExitTime) > 2000) {// 两次按下的时间间隔如果小于2秒就退出，大于2秒就再次提示
                MyToaster.showColorToast("请再按一次返回键退出", R.color.colorAccent);
                mExitTime = System.currentTimeMillis();// 获取当前系统时间
            } else {
                finish();// 退出应用
                overridePendingTransition(R.anim.no_animation, R.anim.slide_out_bottom);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
