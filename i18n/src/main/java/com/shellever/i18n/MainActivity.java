package com.shellever.i18n;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button mShowBtn;
    private TextView mContentTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mShowBtn = (Button) findViewById(R.id.btn_show);
        mContentTv = (TextView) findViewById(R.id.tv_content);

        mShowBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_show:
                mContentTv.setVisibility(View.VISIBLE);
                break;
        }
    }
}
