package com.xiaokun.loadview;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.xiaokun.loadview.dialog_tip.TipLoadDialog;
import com.xiaokun.loadview.util.AppUtils;

/**
 * <pre>
 *     作者   : 肖坤
 *     时间   : 2017/12/28
 *     描述   :
 *     版本   : 1.0
 * </pre>
 */

public class DialogTipActivity extends AppCompatActivity
{
    public TipLoadDialog tipLoadDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tipDialogOperation(this, "玩命加载中...", TipLoadDialog.ICON_TYPE_LOADING);

        findViewById(R.id.loading).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                tipLoadDialog.show();
            }
        });

        final String sucTip = "发送成功";
        final String failTip = "发送失败";
        final String infoTip = "请勿重复操作";

        findViewById(R.id.success_tip).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                tipLoadDialog.setMsg(sucTip, TipLoadDialog.ICON_TYPE_SUCCESS);
                tipLoadDialog.show();
                dismissDialogDelay(tipLoadDialog);
            }
        });

        findViewById(R.id.fail_tip).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                tipLoadDialog.setMsg(failTip, TipLoadDialog.ICON_TYPE_FAIL);
                tipLoadDialog.show();
                dismissDialogDelay(tipLoadDialog);
            }
        });

        findViewById(R.id.info_tip).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                tipLoadDialog.setMsg(infoTip, TipLoadDialog.ICON_TYPE_INFO);
                tipLoadDialog.show();
                dismissDialogDelay(tipLoadDialog);
            }
        });

        findViewById(R.id.toast_tip).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
//                TipViewUtil.showView("加载中...", 6000, TipViewUtil.ICON_TYPE_LOADING);
            }
        });
    }


    /**
     * 基类创建dialog
     *
     * @param context
     * @param msg
     * @param type
     * @return
     */
    private TipLoadDialog createTipDialog(Context context, String msg, int type)
    {
        tipLoadDialog = new TipLoadDialog(context, msg, type);
        return tipLoadDialog;
    }

    /**
     * 配置，操作dialog
     *
     * @param type
     * @param msg
     */
    public void tipDialogOperation(Context context, String msg, int type)
    {
        if (tipLoadDialog == null)
        {
            //需要调用createTipDialog方法
            createTipDialog(context, msg, type);
        }
        if (tipLoadDialog.isShowing())
        {
            tipLoadDialog.dismiss();
        }
        tipLoadDialog.setMsg(msg, type);
        tipLoadDialog.show();
        if (type != TipLoadDialog.ICON_TYPE_LOADING)
        {
            dismissDialogDelay(tipLoadDialog);
        }
    }

    /**
     * 隐藏dialog 固定1s
     *
     * @param dialog
     */
    public void dismissDialogDelay(final TipLoadDialog dialog)
    {
        AppUtils.runOnUIDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                dialog.dismiss();
            }
        }, 1000);
    }

    /**
     * 取消dialog
     */
    public void dismissDialog()
    {
        if (tipLoadDialog != null && tipLoadDialog.isShowing())
        {
            tipLoadDialog.dismiss();
        }
    }

}
