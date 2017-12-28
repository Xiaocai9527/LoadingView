package com.xiaokun.loadview.toast_tip;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by 肖坤 on 2017/9/13.
 * company：exsun
 * email：838494268@qq.com
 */

public class Toasts
{
    public static Context sContext;


    private Toasts()
    {
    }


    public static void register(Context context)
    {
        sContext = context.getApplicationContext();
    }


    private static void check()
    {
        if (sContext == null)
        {
            throw new NullPointerException("Must initial call ToastUtils.register(Context context) in your " +
                    "<? extends Application class>");
        }
    }

    public static void showShort(int resId)
    {
        check();
        Toast.makeText(sContext, resId, Toast.LENGTH_SHORT).show();
    }


    public static void showShort(String message)
    {
        check();
        Toast.makeText(sContext, message, Toast.LENGTH_SHORT).show();
    }


    public static void showLong(int resId)
    {
        check();
        Toast.makeText(sContext, resId, Toast.LENGTH_LONG).show();
    }


    public static void showLong(String message)
    {
        check();
        Toast.makeText(sContext, message, Toast.LENGTH_LONG).show();
    }

    private static Toast mToast;

    public static void showSingleShort(String message)
    {
        check();
        if (mToast == null)
        {
            mToast = Toast.makeText(sContext, message, Toast.LENGTH_SHORT);
        } else
        {
            mToast.setText(message);
        }
        mToast.show();
    }

    public static void showSingleShort(int resId)
    {
        check();
        if (mToast == null)
        {
            mToast = Toast.makeText(sContext, resId, Toast.LENGTH_SHORT);
        } else
        {
            mToast.setText(resId);
        }
        mToast.show();
    }

    public static void showSingleLong(String message)
    {
        check();
        if (mToast == null)
        {
            mToast = Toast.makeText(sContext, message, Toast.LENGTH_LONG);
        } else
        {
            mToast.setText(message);
        }
        mToast.show();
    }

    public static void showSingleLong(int resId)
    {
        check();
        if (mToast == null)
        {
            mToast = Toast.makeText(sContext, resId, Toast.LENGTH_LONG);
        } else
        {
            mToast.setText(resId);
        }
        mToast.show();
    }

    public static void showLongX2(String message)
    {
        showLong(message);
        showLong(message);
    }


    public static void showLongX2(int resId)
    {
        showLong(resId);
        showLong(resId);
    }


    public static void showLongX3(int resId)
    {
        showLong(resId);
        showLong(resId);
        showLong(resId);
    }


    public static void showLongX3(String message)
    {
        showLong(message);
        showLong(message);
        showLong(message);
    }
}
