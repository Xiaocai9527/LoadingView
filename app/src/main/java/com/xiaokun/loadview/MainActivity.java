package com.xiaokun.loadview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final LoadingDialog loadingDialog = new LoadingDialog(this, "玩命加载中...");
        findViewById(R.id.loading).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                loadingDialog.show();
            }
        });
    }
}
