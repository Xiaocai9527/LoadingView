package com.xiaokun.loadview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import com.xiaokun.loadview.toast_tip.ToastLoadUtil;
import com.xiaokun.loadview.toast_tip.ToastTipUtil;

/**
 * <pre>
 *     作者   : 肖坤
 *     时间   : 2017/12/28
 *     描述   :
 *     版本   : 1.0
 * </pre>
 */

public class ToastTipActivity extends AppCompatActivity implements View.OnClickListener
{

    private static final String LOADING = "加载中...";
    public static final String SEND_SUCCESS = "发送成功";

    private Button mBtn1;
    private Button mBtn2;
    private Button mBtn3;
    private Button mBtn4;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast_tip);
        initView();
        initListener();
    }

    private void initView()
    {
        mBtn1 = (Button) findViewById(R.id.btn1);
        mBtn2 = (Button) findViewById(R.id.btn2);
        mBtn3 = (Button) findViewById(R.id.btn3);
        mBtn4 = (Button) findViewById(R.id.btn4);
    }

    private void initListener()
    {
        mBtn1.setOnClickListener(this);
        mBtn2.setOnClickListener(this);
        mBtn3.setOnClickListener(this);
        mBtn4.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.btn1:
                ToastLoadUtil.showLoadTip(LOADING, ToastLoadUtil.HORIZENTAL);
                break;
            case R.id.btn2:
                ToastLoadUtil.showLoadTip(LOADING, ToastLoadUtil.VERTICAL);
                break;
            case R.id.btn3:
                ToastTipUtil.showTip(SEND_SUCCESS, 1500, ToastTipUtil.ICON_TYPE_SUCCESS);
                break;
            case R.id.btn4:

                break;
            default:

                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK)
        {
            if (ToastLoadUtil.isShowing())
            {
                ToastLoadUtil.dismissLoad();
                return true;
            } else
            {
                return super.onKeyDown(keyCode, event);
            }
        }
        return super.onKeyDown(keyCode, event);
    }

}
