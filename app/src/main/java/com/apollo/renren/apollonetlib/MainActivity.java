package com.apollo.renren.apollonetlib;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.apollo.renren.network.bean.BaseBean;
import com.apollo.renren.network.http.okhttp.OkHttpManager;
import com.apollo.renren.network.logger.Logger;
import com.apollo.renren.network.response.Callback;
import com.apollo.renren.network.response.Response;
import com.apollo.renren.network.util.ApolloHttpUtil;
import com.apollo.renren.network.util.PermissionUtils;

public class MainActivity extends AppCompatActivity {

    private PermissionUtils.PermissionGrant grant = requestCode -> {
        Logger.i("permission granted");
        ApolloHttpUtil.getINSTANCE().doGet("https://www.sojson.com/open/api/lunar/json.shtml", null, new Callback<BaseBean>() {
            @Override
            public void onSuccess(BaseBean bean, Response<BaseBean> response) {
                Logger.i(response.getCode() + ":" + response.getMsg() + ":" + response.getData() );
            }

            @Override
            public void onFailure(int errorCode, String errorMsg, Response<BaseBean> response) {
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
