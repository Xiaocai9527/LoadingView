package com.xiaokun.loadview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{

    private Button mDialog;
    private Button mToast;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initView();
        initListener();
    }

    private void initView()
    {
        mDialog = (Button) findViewById(R.id.dialog);
        mToast = (Button) findViewById(R.id.toast);
    }

    private void initListener()
    {
        mDialog.setOnClickListener(this);
        mToast.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.dialog:
                startActivity(new Intent(MainActivity.this, DialogTipActivity.class));
                break;
            case R.id.toast:
                startActivity(new Intent(MainActivity.this, ToastTipActivity.class));
                break;
            default:

                break;
        }
    }
}
