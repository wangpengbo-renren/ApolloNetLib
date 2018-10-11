package com.apollo.renren.apollonetlib;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.apollo.renren.network.http.okhttp.OkHttpManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        OkHttpManager okHttpManager = new OkHttpManager();
    }
}
