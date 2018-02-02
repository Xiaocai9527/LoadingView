package com.xiaokun.dialogtiplib.dialog_tip;

import android.app.Dialog;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.xiaokun.dialogtiplib.R;
import com.xiaokun.dialogtiplib.util.DimenUtils;

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
     * Loading状态
     */
    public static final int ICON_TYPE_LOADING = 1;

    /**
     * loading状态2
     */
    public static final int ICON_TYPE_LOADING2 = 5;

    /**
     * 显示成功状态
     */
    public static final int ICON_TYPE_SUCCESS = 2;
    /**
     * 显示失败状态
     */
    public static final int ICON_TYPE_FAIL = 3;
    /**
     * 显示信息状态
     */
    public static final int ICON_TYPE_INFO = 4;

    /**
     * 默认成功图标
     */
    public int SUCCESS_ICON = R.mipmap.qmui_icon_notify_done;
    /**
     * 默认失败图标
     */
    public int ERROR_ICON = R.mipmap.qmui_icon_notify_error;
    /**
     * 默认提示信息图标
     */
    public int INFO_ICON = R.mipmap.qmui_icon_notify_done;

    private static Handler sHandler = new Handler(Looper.getMainLooper());
    private MyDialog dialog;
    private final GraduallyTextView loadText;
    private final ImageView img;
    private final TextView msg;
    private final ProgressBar progressBar;
    private final LinearLayout layout;
    private FadeInTextView loadText2;
    private Context mContext;
    private int currentType;
    private int dismissTime = 1000;
    private View view;
    /**
     * 消失监听
     */
    private DismissListener listener;


    public interface DismissListener
    {
        void onDimissListener();
    }

    public TipLoadDialog setDismissListener(DismissListener dismissListener)
    {
        this.listener = dismissListener;
        return this;
    }

    public TipLoadDialog(Context context)
    {
        this(context, "", ICON_TYPE_LOADING);
    }

    public TipLoadDialog(Context context, String info, int type)
    {
        this.mContext = context;
        this.currentType = type;
        view = LayoutInflater.from(context).inflate(R.layout.tip_dialog_view, null);
        layout = (LinearLayout) view.findViewById(R.id.dialog_view);
        img = (ImageView) view.findViewById(R.id.tip_img);
        msg = (TextView) view.findViewById(R.id.tip_text);
        progressBar = (ProgressBar) view.findViewById(R.id.lv_circularring);
        loadText = (GraduallyTextView) view.findViewById(R.id.loading_text);
        loadText2 = (FadeInTextView) view.findViewById(R.id.loading_text2);
        changeUi(info, type);
        setNoShadowTheme();
    }

    private void changeUi(String info, int type)
    {
        loadText.setText(info);
        loadText2.setTextString(info, "....");
        msg.setText(info);

        if (type == ICON_TYPE_SUCCESS)
        {
            img.setImageDrawable(ContextCompat.getDrawable(mContext, SUCCESS_ICON));
            img.setVisibility(View.VISIBLE);
            msg.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
            loadText.setVisibility(View.GONE);
            loadText2.setVisibility(View.GONE);
        } else if (type == ICON_TYPE_FAIL)
        {
            img.setImageDrawable(ContextCompat.getDrawable(mContext, ERROR_ICON));
            img.setVisibility(View.VISIBLE);
            msg.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
            loadText.setVisibility(View.GONE);
            loadText2.setVisibility(View.GONE);
        } else if (type == ICON_TYPE_INFO)
        {
            img.setImageDrawable(ContextCompat.getDrawable(mContext, INFO_ICON));
            img.setVisibility(View.VISIBLE);
            msg.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
            loadText.setVisibility(View.GONE);
            loadText2.setVisibility(View.GONE);
        } else if (type == ICON_TYPE_LOADING)
        {
            img.setVisibility(View.GONE);
            msg.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
            loadText.setVisibility(View.VISIBLE);
            loadText2.setVisibility(View.GONE);
        } else if (type == ICON_TYPE_LOADING2)
        {
            img.setVisibility(View.GONE);
            msg.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
            loadText.setVisibility(View.GONE);
            loadText2.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 设置成功图标
     * 注意：如果要更改icon需要在setMsg方法之前调用此方法,否则无效
     *
     * @param resId
     * @return
     */
    public TipLoadDialog setSuccessIcon(int resId)
    {
        SUCCESS_ICON = resId;
        return this;
    }

    /**
     * 设置错误图标
     * 注意：如果要更改icon需要在setMsg方法之前调用此方法
     *
     * @param resId
     * @return
     */
    public TipLoadDialog setErrorIcon(int resId)
    {
        ERROR_ICON = resId;
        return this;
    }

    /**
     * 设置提示图标
     * 注意：如果要更改icon需要在setMsg方法之前调用此方法
     *
     * @param resId icon的资源路径
     * @return
     */
    public TipLoadDialog setInfoIcon(int resId)
    {
        INFO_ICON = resId;
        return this;
    }

    /**
     * 设置消息和样式
     *
     * @param info
     * @param type
     */
    public TipLoadDialog setMsgAndType(String info, int type)
    {
        this.currentType = type;
        changeUi(info, type);

        if (type != ICON_TYPE_LOADING)
        {
            img.post(new Runnable()
            {
                @Override
                public void run()
                {
                    /**
                     * 88dp = width - paddingLeft -paddingRight ;
                     * 1.icon的宽度<88dp,则设置msg的最大宽度为88dp
                     * 2.icon的宽度>88dp,则设置msg的最大宽度为icon宽度
                     *
                     * 88太小  设置为200
                     */
                    int maxWidth = DimenUtils.dpToPxInt(200);
                    int width = img.getWidth();
                    if (maxWidth > width)
                    {
                        msg.setMaxWidth(maxWidth);
                    } else
                    {
                        msg.setMaxWidth(width);
                    }
                }
            });
        }
        return this;
    }

    /**
     * 设置背景,包括圆角和背景颜色
     *
     * @param drawableId
     * @return
     */
    public TipLoadDialog setBackground(int drawableId)
    {
        layout.setBackgroundResource(drawableId);
        return this;
    }

    /**
     * 设置默认主题,无阴影
     *
     * @return
     */
    public TipLoadDialog setNoShadowTheme()
    {
        if (dialog != null)
        {
            FrameLayout parent = (FrameLayout) layout.getParent();
            parent.removeAllViews();
        }
        dialog = new MyDialog(mContext, R.style.loading_dialog_no_shadow);
        initDialog();
        return this;
    }

    /**
     * 设置浮动层有阴影主题
     *
     * @return
     */
    public TipLoadDialog setShadowTheme()
    {
        if (dialog != null)
        {
            FrameLayout parent = (FrameLayout) layout.getParent();
            parent.removeAllViews();
        }
        dialog = new MyDialog(mContext, R.style.loading_dialog_with_shadow);
        initDialog();
        return this;
    }

    /**
     * 自定义dialog主题
     *
     * @param themeId
     * @return
     */
    public TipLoadDialog setDialogTheme(int themeId)
    {
        if (dialog != null)
        {
            FrameLayout parent = (FrameLayout) layout.getParent();
            parent.removeAllViews();
        }
        dialog = new MyDialog(mContext, themeId);
        initDialog();
        return this;
    }

    private void initDialog()
    {
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(layout, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
    }

    /**
     * 设置msg字体颜色
     *
     * @param resColorId
     * @return
     */
    public TipLoadDialog setMsgColor(int resColorId)
    {
        msg.setTextColor(resColorId);
        return this;
    }

    /**
     * 设置msg字体大小
     *
     * @param sp
     * @return
     */
    public TipLoadDialog setMsgSize(int sp)
    {
        msg.setTextSize(TypedValue.COMPLEX_UNIT_SP, sp);
        return this;
    }

    /**
     * 设置progressbar color
     * 注意:只是在api 21以上才有用
     *
     * @param color
     * @return
     */
    public TipLoadDialog setProgressbarColor(int color)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            progressBar.setIndeterminateTintMode(PorterDuff.Mode.SRC_ATOP);
            progressBar.setIndeterminateTintList(ColorStateList.valueOf(color));
        }
        return this;
    }

    /**
     * 设置加载文字颜色
     *
     * @param color
     * @return
     */
    public TipLoadDialog setLoadingTextColor(int color)
    {
        loadText.setTextColor(color);
        return this;
    }

    /**
     * 设置加载文字大小
     *
     * @param sp
     * @return
     */
    public TipLoadDialog setLoadingTextSize(int sp)
    {
        loadText.setTextSize(TypedValue.COMPLEX_UNIT_SP, sp);
        return this;
    }

    /**
     * 设置加载一次文字的动画时间
     *
     * @param duration
     * @return
     */
    public TipLoadDialog setLoadingTime(int duration)
    {
        loadText.setDuration(duration);
        return this;
    }

    /**
     * 设置tip提示框时间
     *
     * @param duration 毫秒
     * @return
     */
    public TipLoadDialog setTipTime(int duration)
    {
        this.dismissTime = duration;
        return this;
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
     * 显示,默认提示框(非加载框)1s延迟后消失
     */
    public void show()
    {
        dialog.show();
        if (loadText.getVisibility() == View.VISIBLE)
        {
            loadText.startLoading();
        }
        if (loadText2.getVisibility() == View.VISIBLE)
        {
            loadText2.startFadeInAnimation();
        }
        //移除所有的message和callback,
        // 防止返回键dismiss后,callback没移除
        sHandler.removeCallbacksAndMessages(null);
        if (this.currentType != ICON_TYPE_LOADING && this.currentType != ICON_TYPE_LOADING2)
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
        if (loadText.getVisibility() == View.VISIBLE)
        {
            loadText.startLoading();
        }
        if (loadText2.getVisibility() == View.VISIBLE)
        {
            loadText2.startFadeInAnimation();
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
     * 消失
     */
    public void dismiss()
    {
        dialog.dismiss();
        if (loadText.getVisibility() == View.VISIBLE)
        {
            loadText.stopLoading();
        }
        if (loadText2.getVisibility() == View.VISIBLE)
        {
            loadText2.stopFadeInAnimation();
        }
        if (listener != null)
        {
            listener.onDimissListener();
        }
    }

    class MyDialog extends Dialog
    {

        public MyDialog(@NonNull Context context, @StyleRes int themeResId)
        {
            super(context, themeResId);
        }

        @Override
        public boolean onKeyDown(int keyCode, @NonNull KeyEvent event)
        {
            if (keyCode == KeyEvent.KEYCODE_BACK)
            {
                //拦截back键,防止loadview的内存泄漏
                dialog.dismiss();
                if (loadText.getVisibility() == View.VISIBLE)
                {
                    loadText.stopLoading();
                }
                if (loadText2.getVisibility() == View.VISIBLE)
                {
                    loadText2.stopFadeInAnimation();
                }
            }
            return super.onKeyDown(keyCode, event);
        }
    }

}
