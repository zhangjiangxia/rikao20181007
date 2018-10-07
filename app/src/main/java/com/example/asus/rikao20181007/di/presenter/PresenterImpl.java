package com.example.asus.rikao20181007.di.presenter;

import com.example.asus.rikao20181007.di.IContract;
import com.example.asus.rikao20181007.di.model.Modelimpl;

import java.lang.ref.WeakReference;

public class PresenterImpl implements IContract.IPresenter<IContract.IView> {
    IContract.IView iView;
    private IContract.IModel iModel;
    private WeakReference<IContract.IModel> iModelWeakReference;
    private WeakReference<IContract.IView> iViewWeakReference;

    @Override
    public void attData(IContract.IView iView) {
       this.iView=iView;
        iModel = new Modelimpl();
        iModelWeakReference = new WeakReference<>(iModel);
        iViewWeakReference = new WeakReference<>(iView);
    }

    @Override
    public void deleteData(IContract.IView iView) {
        iModelWeakReference.clear();
        iViewWeakReference.clear();
    }

    @Override
    public void infoData() {
      iModel.requestData(new IContract.IModel.onCallBack() {
          @Override
          public void stringMsg(String Msg) {
              iView.showData(Msg);
          }
      });
    }
}
