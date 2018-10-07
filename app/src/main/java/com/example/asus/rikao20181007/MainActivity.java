package com.example.asus.rikao20181007;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.asus.rikao20181007.app.App;
import com.example.asus.rikao20181007.data.InfoBean;
import com.example.asus.rikao20181007.di.IContract;
import com.example.asus.rikao20181007.di.presenter.PresenterImpl;
import com.example.asus.rikao20181007.ui.MainAdapter;
import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements IContract.IView {

    @BindView(R.id.m_recycleview)
    RecyclerView mRecycleview;
    private IContract.IPresenter iPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        iPresenter = new PresenterImpl();
        iPresenter.attData(this);
        iPresenter.infoData();


    }

    @Override
    public void showData(final String msg) {
        runOnUiThread(new Runnable() {

            private List<InfoBean.DataBean> data;
            private LinearLayoutManager linearLayoutManager;

            @Override
            public void run() {
                Gson gson = new Gson();
                InfoBean infoBean = gson.fromJson(msg, InfoBean.class);
                data = infoBean.getData();
                linearLayoutManager = new LinearLayoutManager(MainActivity.this);
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                mRecycleview.setLayoutManager(linearLayoutManager);
                final MainAdapter mainAdapter = new MainAdapter(MainActivity.this, data);

                mainAdapter.setOnItemLongClickLisenter(new MainAdapter.OnItemLongClickLisenter() {
                    @Override
                    public void onItemLongClick(int position) {
                       data.remove(position);
                       mainAdapter.notifyDataSetChanged();
                       Toast.makeText(MainActivity.this, data.get(position).toString(), Toast.LENGTH_SHORT).show();
                    }
                });
                mRecycleview.setAdapter(mainAdapter);

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        iPresenter.deleteData(this);
    }
}
