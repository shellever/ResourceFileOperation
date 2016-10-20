package com.shellever.string;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView mTempTv;
    private TextView mTempNumberTv;
    private TextView mTempUnitTv;
    private TextView mPriceTv;
    private TextView mPriceNumberTv;
    private TextView mPriceUnitTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        loadData();
    }

    private void initView() {
        mTempTv = (TextView) findViewById(R.id.tv_temp);
        mTempNumberTv = (TextView) findViewById(R.id.tv_temp_number);
        mTempUnitTv = (TextView) findViewById(R.id.tv_temp_unit);

        mPriceTv = (TextView) findViewById(R.id.tv_price);
        mPriceNumberTv = (TextView) findViewById(R.id.tv_price_number);
        mPriceUnitTv = (TextView) findViewById(R.id.tv_price_unit);
    }


    private void loadData() {
        mTempTv.setText(R.string.text_temp);
        float tempNumber = 26.8f;
        String tempNumberString = String.format(Locale.getDefault(), "%.1f", tempNumber);
        mTempNumberTv.setText(tempNumberString);
        String char_deg = getResources().getString(R.string.html_char_deg) + "C";
        mTempUnitTv.setText(char_deg);

        mPriceTv.setText(R.string.text_price);
        float priceNumber = 99.88f;
        String priceNumberString = String.format(Locale.getDefault(), "%.2f", priceNumber);
        mPriceNumberTv.setText(priceNumberString);
        String char_yen = getResources().getString(R.string.html_char_yen);
        mPriceUnitTv.setText(char_yen);
    }

}
