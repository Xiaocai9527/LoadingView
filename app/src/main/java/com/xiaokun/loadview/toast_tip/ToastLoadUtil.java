package com.xiaokun.loadview.toast_tip;

import android.content.Context;
import android.os.CountDownTimer;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.xiaokun.loadview.R;
import com.xiaokun.loadview.util.AppUtils;

import static java.lang.Integer.MAX_VALUE;

/**
 * @author xiaokun
 * @date 2017/12/22
 */

public class ToastLoadUtil
{
    /**
     * 横向
     */
    public static final int HORIZENTAL = 0;
    /**
     * 纵向
     */
    public static final int VERTICAL = 1;
    /**
     * 1s,取1s小于Toast.LENGTH_LONG=3.5s.
     * 这样就不会有闪烁现象
     */
    public static final long ONE_SECOND = 1000;
    /**
     * toast状态
     */
    private static boolean isShowing = false;

    private static CountDownTimer timer;
    private static Toast toast;

    /**
     * 无限弹窗
     *
     * @param msg 需要弹出的消息
     */
    public static void showLoadTip(String msg, int direction)
    {
        if (isShowing())
        {
            return;
        }
        Context appContext = AppUtils.getAppContext();
        toast = getToast(appContext);
        View contentView = null;
        if (direction == HORIZENTAL)
        {
            contentView = View.inflate(appContext, R.layout.load_view_horizental, null);
        } else
        {
            contentView = View.inflate(appContext, R.layout.load_view_vertical, null);
        }
        TextView infoTv = (TextView) contentView.findViewById(R.id.info);
        infoTv.setText(msg);
        toast.setView(contentView);
        toast.setGravity(Gravity.CENTER, 0, 0);
        timer = getTimer();
        timer.start();
        isShowing = true;
    }

    private static Toast getToast(Context context)
    {
        if (toast == null)
        {
            toast = new Toast(context);
            toast.setDuration(Toast.LENGTH_LONG);
        }
        return toast;
    }

    private static CountDownTimer getTimer()
    {
        if (timer == null)
        {
            timer = new CountDownTimer(MAX_VALUE * ONE_SECOND, ONE_SECOND)
            {
                @Override
                public void onTick(long l)
                {
                    toast.show();
                }

                @Override
                public void onFinish()
                {
                    toast.cancel();
                }
            };
        }
        return timer;
    }

    public static boolean isShowing()
    {
        return isShowing;
    }

    /**
     * 取消toast
     */
    public static void dismissLoad()
    {
        if (timer != null && toast != null)
        {
            timer.onFinish();
            timer.cancel();
            //不置空，会有闪烁问题
            //但是置空会每次弹窗都创建toast实例，造成资源浪费
            toast = null;
            isShowing = false;
        }
    }

}
