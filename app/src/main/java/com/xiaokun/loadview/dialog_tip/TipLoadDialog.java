package com.xiaokun.loadview.dialog_tip;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.xiaokun.loadview.R;

/**
 * @author xiaokun
 * @date 2017/12/14
 */

public class TipLoadDialog
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

    private static Handler sHandler = new Handler(Looper.getMainLooper());
    private Dialog dialog;
    private final GraduallyTextView loadView;
    private final ImageView img;
    private final TextView msg;
    private final ProgressBar progressBar;
    private Context mContext;
    private int currentType;
    private int dismissTime = 1000;


    public TipLoadDialog(Context context, String info, int type)
    {
        this.mContext = context;
        this.currentType = type;
        View view = LayoutInflater.from(context).inflate(R.layout.tip_dialog_view, null);
        LinearLayout layout = (LinearLayout) view.findViewById(R.id.dialog_view);
        img = view.findViewById(R.id.tip_img);
        msg = view.findViewById(R.id.tip_text);
        progressBar = (ProgressBar) view.findViewById(R.id.lv_circularring);
        loadView = (GraduallyTextView) view.findViewById(R.id.loading_text);
        loadView.setText(info);
        msg.setText(info);
        if (type == ICON_TYPE_SUCCESS)
        {
            img.setImageDrawable(ContextCompat.getDrawable(context, R.mipmap.qmui_icon_notify_done));
            img.setVisibility(View.VISIBLE);
            msg.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
            loadView.setVisibility(View.GONE);
        } else if (type == ICON_TYPE_FAIL)
        {
            img.setImageDrawable(ContextCompat.getDrawable(context, R.mipmap.qmui_icon_notify_error));
            img.setVisibility(View.VISIBLE);
            msg.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
            loadView.setVisibility(View.GONE);
        } else if (type == ICON_TYPE_INFO)
        {
            img.setImageDrawable(ContextCompat.getDrawable(context, R.mipmap.qmui_icon_notify_info));
            img.setVisibility(View.VISIBLE);
            msg.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
            loadView.setVisibility(View.GONE);
        } else if (type == ICON_TYPE_LOADING)
        {
            img.setVisibility(View.GONE);
            msg.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
            loadView.setVisibility(View.VISIBLE);
        }
        dialog = new Dialog(context, R.style.loading_dialog);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(layout, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
    }

    /**
     * 设置样式
     *
     * @param info
     * @param type
     */
    public void setMsg(String info, int type)
    {
        this.currentType = type;
        msg.setText(info);
        loadView.setText(info);
        if (type == ICON_TYPE_SUCCESS)
        {
            img.setImageDrawable(ContextCompat.getDrawable(mContext, R.mipmap.qmui_icon_notify_done));
            img.setVisibility(View.VISIBLE);
            msg.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
            loadView.setVisibility(View.GONE);
        } else if (type == ICON_TYPE_FAIL)
        {
            img.setImageDrawable(ContextCompat.getDrawable(mContext, R.mipmap.qmui_icon_notify_error));
            img.setVisibility(View.VISIBLE);
            msg.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
            loadView.setVisibility(View.GONE);
        } else if (type == ICON_TYPE_INFO)
        {
            img.setImageDrawable(ContextCompat.getDrawable(mContext, R.mipmap.qmui_icon_notify_info));
            img.setVisibility(View.VISIBLE);
            msg.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
            loadView.setVisibility(View.GONE);
        } else if (type == ICON_TYPE_LOADING)
        {
            img.setVisibility(View.GONE);
            msg.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
            loadView.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 配置是否能返回键取消加载框
     *
     * @param flag
     */
    public void setCancelable(boolean flag)
    {
        dialog.setCancelable(flag);
    }

    /**
     * 配置是否能点击框外取消加载框
     *
     * @param cancel
     */
    public void setCanceledOnTouchOutside(boolean cancel)
    {
        dialog.setCanceledOnTouchOutside(cancel);
    }

    /**
     * 是否显示
     *
     * @return
     */
    public boolean isShowing()
    {
        return dialog.isShowing();
    }

    /**
     * 显示,默认1s延迟后消失
     */
    public void show()
    {
        dialog.show();
        if (loadView.getVisibility() == View.VISIBLE)
        {
            loadView.startLoading();
        }
        //移除所有的message和callback,
        // 防止返回键dismiss后,callback没移除
        sHandler.removeCallbacksAndMessages(null);
        if (this.currentType != ICON_TYPE_LOADING)
        {
            sHandler.postDelayed(new Runnable()
            {
                @Override
                public void run()
                {
                    dismiss();
                }
            }, dismissTime);
        }
    }

    /**
     * 显示,自定义时间
     */
    public void show(int duration)
    {
        this.dismissTime = duration;
        dialog.show();
        if (loadView.getVisibility() == View.VISIBLE)
        {
            loadView.startLoading();
        }
        if (this.currentType != ICON_TYPE_LOADING)
        {
            sHandler.postDelayed(new Runnable()
            {
                @Override
                public void run()
                {
                    dismiss();
                }
            }, dismissTime);
        }
    }

    /**
     * 消失
     */
    public void dismiss()
    {
        dialog.dismiss();
        if (loadView.getVisibility() == View.VISIBLE)
        {
            loadView.stopLoading();
        }
    }

}
