package com.xiaokun.loadview;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.xiaokun.loadview.dialog_tip.TipLoadDialog;

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
        showDialog(this, "玩命加载中...", TipLoadDialog.ICON_TYPE_LOADING);

        findViewById(R.id.loading).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                showDialog(DialogTipActivity.this, "玩命加载中...", TipLoadDialog.ICON_TYPE_LOADING);
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
                showDialog(DialogTipActivity.this, sucTip, TipLoadDialog.ICON_TYPE_SUCCESS);
            }
        });

        findViewById(R.id.fail_tip).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                showDialog(DialogTipActivity.this, failTip, TipLoadDialog.ICON_TYPE_FAIL);
            }
        });

        findViewById(R.id.info_tip).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                showDialog(DialogTipActivity.this, infoTip, TipLoadDialog.ICON_TYPE_INFO);
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
        tipLoadDialog.setMsg(msg, type);
        tipLoadDialog.show();
    }
}
