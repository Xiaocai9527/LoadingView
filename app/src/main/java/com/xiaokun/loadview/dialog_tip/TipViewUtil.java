package com.xiaokun.loadview.dialog_tip;

import android.content.Context;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.xiaokun.loadview.R;
import com.xiaokun.loadview.util.AppUtils;

/**
 * @author xiaokun
 * @date 2017/12/15
 */

public class TipViewUtil
{
    /**
     * 不显示任何icon
     */
    public static final int ICON_TYPE_NOTHING = 0;
    /**
     * 显示 Loading 图标
     */
    public static final int ICON_TYPE_LOADING = 1;
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

    public static Context context;
    private static Handler mHandler;
    private static Toast toast;
    private static int duration = 0;
    private static int currDuration = 0;
    private static final int DEFAULT = 2000;
    private static GraduallyTextView loadView;

//    public TipViewUtil()
//    {
//
//    }
//
//    public void setView(String msg, int type)
//    {
////        showView(msg, type);
//    }

    private static void show()
    {
        mHandler.post(mToastThread);
    }

    private static Runnable mToastThread = new Runnable()
    {
        @Override
        public void run()
        {
            loadView.startLoading();
            if (toast == null)
            {
                return;
            }
            toast.show();
            mHandler.postDelayed(mToastThread, 2000);
            if (duration != 0)
            {
                if (currDuration <= duration)
                {
                    currDuration += DEFAULT;
                } else
                {
                    cancel();
                }
            }
        }
    };

    public static void cancel()
    {
        loadView.stopLoading();
        mHandler.removeCallbacks(mToastThread);// 先把显示线程删除
        toast.cancel();// 把最后一个线程的显示效果cancel掉，就一了百了了
        currDuration = DEFAULT;
    }

    public static void showView(String msg, int duration, int type)
    {
        if (TextUtils.isEmpty(msg))
        {
            return;
        }
        TipViewUtil.duration = duration;
        context = AppUtils.getAppContext();
        mHandler = AppUtils.getHandler();
        toast = new Toast(context);
        toast.setDuration(Toast.LENGTH_LONG);
        View view = LayoutInflater.from(AppUtils.getAppContext()).inflate(R.layout.tip_dialog_view, null);
        LinearLayout layout = (LinearLayout) view.findViewById(R.id.dialog_view);
        ImageView img = view.findViewById(R.id.tip_img);
        TextView msgTv = view.findViewById(R.id.tip_text);
        ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.lv_circularring);
        loadView = (GraduallyTextView) view.findViewById(R.id.loading_text);
        loadView.setText(msg);

        msgTv.setText(msg);
        if (type == ICON_TYPE_SUCCESS)
        {
            img.setImageDrawable(ContextCompat.getDrawable(AppUtils.getAppContext(), R.mipmap.qmui_icon_notify_done));
            img.setVisibility(View.VISIBLE);
            msgTv.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
            loadView.setVisibility(View.GONE);
        } else if (type == ICON_TYPE_FAIL)
        {
            img.setImageDrawable(ContextCompat.getDrawable(AppUtils.getAppContext(), R.mipmap.qmui_icon_notify_error));
            img.setVisibility(View.VISIBLE);
            msgTv.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
            loadView.setVisibility(View.GONE);
        } else if (type == ICON_TYPE_INFO)
        {
            img.setImageDrawable(ContextCompat.getDrawable(AppUtils.getAppContext(), R.mipmap.qmui_icon_notify_info));
            img.setVisibility(View.VISIBLE);
            msgTv.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
            loadView.setVisibility(View.GONE);
        } else if (type == ICON_TYPE_LOADING)
        {
            img.setVisibility(View.GONE);
            msgTv.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
            loadView.setVisibility(View.VISIBLE);
        }
        toast.setView(view);
        show();
    }

}
