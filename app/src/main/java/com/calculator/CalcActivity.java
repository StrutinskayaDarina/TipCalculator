package com.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.security.PrivateKey;
import java.text.NumberFormat;

public class CalcActivity<Calc> extends AppCompatActivity {
    private static final NumberFormat currencyFormat= NumberFormat.getCurrencyInstance();
    private static final NumberFormat percentFormat = NumberFormat.getPercentInstance();
    private double amount= 0.0;
    private double percent= 0.15;
    private EditText et_amount;
    private SeekBar sb_percent;
    private TextView tv_percent;
    private TextView tv_tip;
    private TextView tv_total;
    private com.calculator.Calc tipCalc = new com.calculator.Calc();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calc_activity);
        et_amount = findViewById(R.id.et_amount);
        sb_percent = findViewById(R.id.sb_percent);
        tv_percent = findViewById(R.id.tv_percent);
        tv_tip = findViewById(R.id.tv_tip);
        tv_total = findViewById(R.id.tv_total);
        tv_tip.setText("0.0");
        tv_total.setText("0.0");
        et_amount.addTextChangedListener(amountTextWatcher);
        sb_percent.setOnSeekBarChangeListener(sbListener);
    }
    private final TextWatcher amountTextWatcher = new TextWatcher() {
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            amount = Double.parseDouble(s.toString());
            tv_tip.setText(currencyFormat.format(tipCalc.calculateTip(amount,percent)));
            tv_total.setText(currencyFormat.format(tipCalc.calculateTotal(amount, percent)));
        }
        @Override
        public void afterTextChanged(Editable s) { }
        @Override
        public void beforeTextChanged(
                CharSequence s, int start, int count, int after) { }
    };

    private final SeekBar.OnSeekBarChangeListener sbListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar SeekBar, int progress, boolean fromUser) {
            percent= progress / 100.0;
            tv_percent.setText(percentFormat.format(percent));
            tv_tip.setText(currencyFormat.format(tipCalc.calculateTip(amount,percent)));
            tv_total.setText(currencyFormat.format(tipCalc.calculateTotal(amount, percent)));
        }
        @Override
        public void onStartTrackingTouch(SeekBar SeekBar) { }
        @Override
        public void onStopTrackingTouch(SeekBar SeekBar) { }
    };

}