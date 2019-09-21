package com.example.resource.sqlliteteste.model;

import android.app.Application;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.example.resource.sqlliteteste.infra.DataBase;

public class App extends Application {
    private static DataBase db;

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    public void onCreate() {
        super.onCreate();
        db = new DataBase(this, "base", null, 1);
    }

    public static DataBase getDb() {
        return db;
    }
}
