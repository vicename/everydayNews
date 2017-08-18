package com.guanyue.everydaynews.activity;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.generallibrary.base.DifBaseActivity;

/**
 * Created by Li DaChang on 17/8/13.
 * ..-..---.-.--..---.-...-..-....-.
 */

public abstract class AppBaseActivity extends DifBaseActivity {
    protected AlertDialog mAlertDialog;
    private Snackbar mSnackbar;

    public void showProgress(String msg) {
        if (mAlertDialog == null) {
            mAlertDialog = new AlertDialog.Builder(mContext).setCancelable(false).setMessage(msg).create();
        }
        mAlertDialog.show();
    }

    protected void dismissProgress() {
        if (mAlertDialog != null && mAlertDialog.isShowing()) {
            mAlertDialog.dismiss();
        }
    }

    protected void initSnackBar(int rootRes) {
        mSnackbar = Snackbar.make(findViewById(rootRes), "Snack Bar", Snackbar.LENGTH_LONG);
        mSnackbar.setAction("知道了", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSnackbar.dismiss();
            }
        });
    }

    protected void snackBarGo(String msg) {
        if (mSnackbar != null) {
            mSnackbar.setText(msg);
            mSnackbar.show();
        }
    }

}
