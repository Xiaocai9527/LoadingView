package com.xiaokun.loadview.toast_tip;

import android.content.Context;
import android.os.CountDownTimer;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.xiaokun.dialogtiplib.util.AppUtils;
import com.xiaokun.loadview.R;

import static java.lang.Integer.MAX_VALUE;

/**
 * @author xiaokun
 * @date 2017/12/22
 */

public class ToastTipUtil
{
    /**
     * 不显示任何icon
     */
    public static final int ICON_TYPE_NOTHING = 0;
    /**
     * 显示成功图标
     */
    public static final int ICON_TYPE_SUCCESS = 2;
    /**
     * 显示失败图标
     */
    public static final int ICON_TYPE_FAIL = 3;
    /**
     * 显示信息图标
     */
    public static final int ICON_TYPE_INFO = 4;
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
     * 显示tip,不带button
     *
     * @param msg      提示msg
     * @param duration toast时间 单位毫秒
     * @param type     toast类型
     */
    public static void showTip(String msg, int duration, int type)
    {
        if (isShowing())
        {
            return;
        }
        Context appContext = AppUtils.getAppContext();
        toast = getToast(appContext);
        View contentView = View.inflate(appContext, R.layout.tip_view_vertical, null);
        TextView infoTv = (TextView) contentView.findViewById(R.id.info);
        ImageView img = (ImageView) contentView.findViewById(R.id.img);
        infoTv.setText(msg);
        if (type == ICON_TYPE_SUCCESS)
        {
            img.setVisibility(View.VISIBLE);
            img.setImageResource(R.mipmap.qmui_icon_notify_done);
        } else if (type == ICON_TYPE_FAIL)
        {
            img.setVisibility(View.VISIBLE);
            img.setImageResource(R.mipmap.qmui_icon_notify_error);
        } else if (type == ICON_TYPE_INFO)
        {
            img.setVisibility(View.VISIBLE);
            img.setImageResource(R.mipmap.qmui_icon_notify_info);
        } else
        {
            img.setVisibility(View.GONE);
        }
        toast.setView(contentView);
        toast.setGravity(Gravity.CENTER, 0, 0);
        timer = getTimer(duration);
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

    private static CountDownTimer getTimer(int duration)
    {
        long totalTime;
        if (duration > 0)
        {
            totalTime = duration;
        } else
        {
            totalTime = MAX_VALUE;
        }
        if (timer == null)
        {
            timer = new CountDownTimer(totalTime, totalTime / 2)
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
                    toast = null;
                    isShowing = false;
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
