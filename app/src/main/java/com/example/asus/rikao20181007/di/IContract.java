package com.example.asus.rikao20181007.di;

public interface IContract {
    interface IView{
      void showData(String msg);

    }
    interface IModel{
        interface  onCallBack{
            void stringMsg(String Msg);
        }
        void  requestData(onCallBack onCallBack);

    }
    interface IPresenter<IView>{

        void  attData(IView iView);
        void deleteData(IView iView );
        void  infoData();
    }
}
