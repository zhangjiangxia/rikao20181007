package com.example.asus.rikao20181007.app;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.example.asus.rikao20181007.DaoMaster;
import com.example.asus.rikao20181007.DaoSession;
import com.facebook.drawee.backends.pipeline.Fresco;

public class App extends Application {

        private DaoMaster.DevOpenHelper mHelper;
        private SQLiteDatabase db;
        private DaoMaster mDaoMaster;
        private DaoSession mDaoSession;
        public static  App instances;
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        instances = this;
        setDatabase();
    }

     public static App getInstances(){
        return instances;
    }
        private void setDatabase() {
            mHelper = new DaoMaster.DevOpenHelper(this, "notes-db", null);
            db = mHelper.getWritableDatabase();
            mDaoMaster = new DaoMaster(db);
            mDaoSession = mDaoMaster.newSession();
        }
        public DaoSession getDaoSession() {
            return mDaoSession;
        }
        public SQLiteDatabase getDb() {
            return db;
        }





}
