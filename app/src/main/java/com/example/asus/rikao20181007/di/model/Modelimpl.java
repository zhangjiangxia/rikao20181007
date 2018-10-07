package com.example.asus.rikao20181007.di.model;

import com.example.asus.rikao20181007.UserDao;
import com.example.asus.rikao20181007.app.App;
import com.example.asus.rikao20181007.data.OkHttpUtil;
import com.example.asus.rikao20181007.data.User;
import com.example.asus.rikao20181007.di.IContract;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class Modelimpl implements IContract.IModel {

    private static  final String URLGET="https://www.zhaoapi.cn/product/getCatagory";
    private UserDao userDao;

    @Override
    public void requestData(final onCallBack onCallBack) {
        userDao = App.getInstances().getDaoSession().getUserDao();
        List<User> users = userDao.loadAll();
        if (users.size()>0){
            for (int i = 0; i < users.size(); i++) {
                String name = users.get(i).getName();
                onCallBack.stringMsg(name);
            }
            return;
        }
        OkHttpUtil.getInstance().get(URLGET, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                String s = e.getMessage().toString();
                onCallBack.stringMsg(s);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                User user = new User(null, string);
                userDao.insertInTx(user);
                onCallBack.stringMsg(string);
            }
        });


    }
}
