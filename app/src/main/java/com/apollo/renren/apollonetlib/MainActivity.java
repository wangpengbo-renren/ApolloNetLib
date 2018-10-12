package com.apollo.renren.apollonetlib;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.apollo.renren.network.bean.BaseBean;
import com.apollo.renren.network.http.okhttp.OkHttpManager;
import com.apollo.renren.network.logger.Logger;
import com.apollo.renren.network.response.Callback;
import com.apollo.renren.network.response.Response;
import com.apollo.renren.network.util.ApolloHttpUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ApolloHttpUtil.getINSTANCE().doGet("http://api.map.baidu.com/telematics/v3/weather?location=嘉兴&output=json&ak=5slgyqGDENN7Sy7pw29IUvrZ", null, new Callback<TaobaoBean>() {
            @Override
            public void onSuccess(TaobaoBean bean, Response<TaobaoBean> response) {
                Logger.i(bean != null ? bean.toString() : "" + response);
            }

            @Override
            public void onFailure(int errorCode, String errorMsg, Response<TaobaoBean> response) {

            }
        });
    }
}
