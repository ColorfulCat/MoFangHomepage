package com.mfbl.mofang.util;

import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * Created by Devis on 15/12/16.
 */
public class MySnackBar {

//    public static Snackbar snackbar;

    public static void show(View view, String content){
        Snackbar.make(view, content, Snackbar.LENGTH_SHORT).show();
    }

}
