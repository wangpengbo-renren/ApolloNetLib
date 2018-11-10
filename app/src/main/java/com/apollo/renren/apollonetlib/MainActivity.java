package com.apollo.renren.apollonetlib;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.apollo.renren.network.bean.BaseBean;
import com.apollo.renren.network.logger.Logger;
import com.apollo.renren.network.response.Callback;
import com.apollo.renren.network.response.Response;
import com.apollo.renren.network.util.ApolloHttpUtil;
import com.apollo.renren.network.util.PermissionUtils;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private PermissionUtils.PermissionGrant grant = requestCode -> {
        Logger.i("permission granted");
        ApolloHttpUtil.getInstance().doGet("https://api.truckerpath.com/info?version=1.45.11&platform=android&app=mc", null, new Callback<BaseBean<JSONObject>>() {
            @Override
            public void onSuccess(BaseBean<JSONObject> bean, Response<BaseBean<JSONObject>> response) {
                Logger.i(response.getCode() + ":" + response.getMsg() + ":" + response.getData() );
            }

            @Override
            public void onFailure(int errorCode, String errorMsg, Response<BaseBean<JSONObject>> response) {
                Logger.e(errorCode + ":" + errorMsg + ":" + response.getData());
            }

        });
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PermissionUtils.requestPermission(this, PermissionUtils.CODE_INTERNET,grant);

    }

}
