package com.xiaokun.loadview;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.xiaokun.loadview.util.DimenUtils;

/**
 * @author xiaokun
 * @date 2017/12/5
 */

public class LoadingDialog
{
    private Dialog dialog;
    private final GraduallyTextView loadView;

    /**
     * @param context activity,创建dialog需要依赖Activity
     * @param msg     展示的文字
     */
    public LoadingDialog(Context context, String msg)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.loading_dialog_view, null);
        LinearLayout layout = (LinearLayout) view.findViewById(R.id.dialog_view);
        layout.setMinimumWidth(DimenUtils.dpToPxInt(100));
        ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.lv_circularring);
        loadView = (GraduallyTextView) view.findViewById(R.id.loading_text);
        loadView.setText(msg);
        dialog = new Dialog(context, R.style.loading_dialog);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(layout, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
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
     * 显示
     */
    public void show()
    {
        loadView.startLoading();
        dialog.show();
        loadView.post(new Runnable()
        {
            @Override
            public void run()
            {
                Log.e("LoadingDialog", "run(LoadingDialog.java:39)" + loadView.getHeight());
            }
        });
    }

    /**
     * 消失
     */
    public void dismiss()
    {
        loadView.stopLoading();
        dialog.dismiss();
    }
}
