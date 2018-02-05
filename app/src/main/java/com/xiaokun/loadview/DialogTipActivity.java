package com.xiaokun.loadview;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.xiaokun.dialogtiplib.dialog_tip.TipLoadDialog;


/**
 * <pre>
 *     作者   : 肖坤
 *     时间   : 2017/12/28
 *     描述   :
 *     版本   : 1.0
 * </pre>
 */

public class DialogTipActivity extends AppCompatActivity implements View.OnClickListener
{
    public static final String LOADING_玩命 = "玩命加载中...";
    private final String sucTip = "发送成功";
    private final String failTip = "发送失败";
    private final String infoTip = "字数太多就分段显示，保证textview的宽度";

    private Context mContext;
    public TipLoadDialog tipLoadDialog;
    private Button mLoading;
    private Button mSuccessTip;
    private Button mFailTip;
    private Button mInfoTip;
    private Button mToastTip;
    private Button mThemeTip;
    private Button mCornerTip;
    private Button mIconTip;
    private Button mBgTip;
    private Button mMsgColorTip;
    private Button mLvColorTip;
    private Button mLvTimeTip;
    private Button mDisListenerTip;
    private Button mTipTimeTip;
    private Button mLoading2;
    private Button mLogin1;
    private Button mLogin2;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        tipLoadDialog = new TipLoadDialog(this);
        initView();
        initListener();
    }

    private void initView()
    {
        mLoading = (Button) findViewById(R.id.loading);
        mSuccessTip = (Button) findViewById(R.id.success_tip);
        mFailTip = (Button) findViewById(R.id.fail_tip);
        mInfoTip = (Button) findViewById(R.id.info_tip);
        mToastTip = (Button) findViewById(R.id.toast_tip);
        mThemeTip = (Button) findViewById(R.id.theme_tip);
        mCornerTip = (Button) findViewById(R.id.corner_tip);
        mIconTip = (Button) findViewById(R.id.icon_tip);
        mBgTip = (Button) findViewById(R.id.bg_tip);
        mMsgColorTip = (Button) findViewById(R.id.msg_color_tip);
        mLvColorTip = (Button) findViewById(R.id.lv_color_tip);
        mLvTimeTip = (Button) findViewById(R.id.lv_time_tip);
        mDisListenerTip = (Button) findViewById(R.id.dis_listener_tip);
        mTipTimeTip = (Button) findViewById(R.id.tip_time_tip);
        mLoading2 = (Button) findViewById(R.id.loading2);
        mLogin1 = (Button) findViewById(R.id.login1);
        mLogin2 = (Button) findViewById(R.id.login2);
    }

    private void initListener()
    {
        mLoading.setOnClickListener(this);
        mSuccessTip.setOnClickListener(this);
        mFailTip.setOnClickListener(this);
        mInfoTip.setOnClickListener(this);
        mToastTip.setOnClickListener(this);
        mThemeTip.setOnClickListener(this);
        mCornerTip.setOnClickListener(this);
        mIconTip.setOnClickListener(this);
        mBgTip.setOnClickListener(this);
        mMsgColorTip.setOnClickListener(this);
        mLvColorTip.setOnClickListener(this);
        mLvTimeTip.setOnClickListener(this);
        mDisListenerTip.setOnClickListener(this);
        mTipTimeTip.setOnClickListener(this);
        mLoading2.setOnClickListener(this);
        mLogin1.setOnClickListener(this);
        mLogin2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.loading:
                //默认是无阴影主题
                tipLoadDialog.setMsgAndType(LOADING_玩命, TipLoadDialog.ICON_TYPE_LOADING).show();
                break;
            case R.id.success_tip:
                //设置无阴影主题
                tipLoadDialog.setNoShadowTheme().setMsgAndType(sucTip, TipLoadDialog.ICON_TYPE_SUCCESS).show();
                break;
            case R.id.fail_tip:
                //设置无阴影主题
                tipLoadDialog.setNoShadowTheme().setMsgAndType(failTip, TipLoadDialog.ICON_TYPE_FAIL).show();
                break;
            case R.id.info_tip:
                //设置无阴影主题
                tipLoadDialog.setNoShadowTheme().setMsgAndType(infoTip, TipLoadDialog.ICON_TYPE_INFO).show();
                break;
            case R.id.toast_tip:
                //设置提示框阴影主题
                tipLoadDialog.setShadowTheme().setMsgAndType(sucTip, TipLoadDialog.ICON_TYPE_SUCCESS).show();
                break;
            case R.id.theme_tip:
                //设置加载框阴影主题
                tipLoadDialog.setShadowTheme().setMsgAndType(LOADING_玩命, TipLoadDialog.ICON_TYPE_LOADING).show();
                break;
            case R.id.corner_tip:
                //设置圆角
                tipLoadDialog.setBackground(R.drawable.custom_dialog_bg_corner)
                        .setNoShadowTheme()
                        .setMsgAndType(LOADING_玩命, TipLoadDialog.ICON_TYPE_LOADING)
                        .show();
                break;
            case R.id.icon_tip:
                //设置除了Loading之外的tip图标
                tipLoadDialog.setSuccessIcon(R.mipmap.custom_tip)
                        .setMsgAndType(sucTip, TipLoadDialog.ICON_TYPE_SUCCESS)
                        .show();
                break;
            case R.id.bg_tip:
                //设置背景颜色
                tipLoadDialog.setBackground(R.drawable.custom_dialog_bg_color)
                        .setNoShadowTheme()
                        .setMsgAndType(sucTip, TipLoadDialog.ICON_TYPE_SUCCESS)
                        .show();
                break;
            case R.id.msg_color_tip:
                //设置提示信息的text的颜色和大小
                tipLoadDialog.setNoShadowTheme()
                        .setMsgColor(Color.BLUE)
                        .setMsgSize(20)
                        .setMsgAndType(failTip, TipLoadDialog.ICON_TYPE_FAIL)
                        .show();
                break;
            case R.id.lv_color_tip:
                //设置加载框文字的颜色和大小 以及progressbar的颜色
                tipLoadDialog.setNoShadowTheme()
                        .setProgressbarColor(Color.RED)
                        .setLoadingTextColor(Color.RED)
                        .setLoadingTextSize(20)
                        .setMsgAndType(LOADING_玩命, TipLoadDialog.ICON_TYPE_LOADING)
                        .show();
                break;
            case R.id.lv_time_tip:
                //设置loadingText一次动画的时间
                tipLoadDialog.setNoShadowTheme()
                        .setProgressbarColor(Color.WHITE)
                        .setLoadingTextColor(Color.WHITE)
                        .setLoadingTextSize(15)
                        .setLoadingTime(10000)
                        .setMsgAndType(LOADING_玩命, TipLoadDialog.ICON_TYPE_LOADING)
                        .show();
                break;
            case R.id.dis_listener_tip:
                //弹窗消失事件监听
                tipLoadDialog.setNoShadowTheme()
                        .setMsgAndType("登录成功", TipLoadDialog.ICON_TYPE_SUCCESS)
                        .setDismissListener(new TipLoadDialog.DismissListener()
                        {
                            @Override
                            public void onDimissListener()
                            {
                                startActivity(new Intent(DialogTipActivity.this, HomeActivity.class));
                                //然后可以finish掉当前登录页
                            }
                        })
                        .show();
                break;
            case R.id.tip_time_tip:
                //设置tip提示弹框时间
                tipLoadDialog.setNoShadowTheme()
                        .setMsgAndType("停留2秒消失", TipLoadDialog.ICON_TYPE_SUCCESS)
                        .setTipTime(2000)
                        .show();
                break;
            case R.id.loading2:
                //设置另一种loading文字动画,注意不要加后缀...
                tipLoadDialog.setNoShadowTheme()
                        .setMsgAndType("加载中", TipLoadDialog.ICON_TYPE_LOADING2)
                        .show();
                break;
            case R.id.login1:
                tipLoadDialog.setMsgAndType("登录中...", TipLoadDialog.ICON_TYPE_LOADING).show();
                startThread();
                break;
            case R.id.login2:
                tipLoadDialog.setNoShadowTheme().setMsgAndType("登录中", TipLoadDialog.ICON_TYPE_LOADING2).show();
                startThread();
                break;
            default:

                break;
        }
    }

    private void startThread()
    {
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    Thread.sleep(2000);
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        tipLoadDialog.setMsgAndType("登录成功", TipLoadDialog.ICON_TYPE_SUCCESS)
                                .setDismissListener(new TipLoadDialog.DismissListener()
                                {
                                    @Override
                                    public void onDimissListener()
                                    {
                                        startActivity(new Intent(DialogTipActivity.this, HomeActivity.class));
                                        //然后可以finish掉当前登录页
                                    }
                                }).show();
                    }
                });
            }
        }).start();
    }

    /**
     * 配置，操作dialog
     *
     * @param type
     * @param msg
     */
    public void showDialog(Context context, String msg, int type)
    {
        if (tipLoadDialog == null)
        {
            tipLoadDialog = new TipLoadDialog(context, msg, type);
        }
        if (tipLoadDialog.isShowing())
        {
            tipLoadDialog.dismiss();
        }
        tipLoadDialog.setMsgAndType(msg, type).show();
    }
}
