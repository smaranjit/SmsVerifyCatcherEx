package com.smaranjit.sample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.smaranjit.smsverifycatcherex.SmsVerifyCatcher;

public class MainActivity extends AppCompatActivity {

    SmsVerifyCatcher mSmsVerifyCatcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
