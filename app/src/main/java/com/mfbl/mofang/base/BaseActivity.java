package com.mfbl.mofang.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.mfbl.mofang.R;

/**
 * Created by devislee on 16/8/27.
 */
public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener{

    protected abstract int getContentViewId();//设置view的id
    protected abstract void initView();//初始化界面

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());

        initView();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.static_animation, R.anim.base_slide_right_out);
    }
}
