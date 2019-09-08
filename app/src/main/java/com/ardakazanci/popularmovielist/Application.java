package com.ardakazanci.popularmovielist;



import com.orhanobut.hawk.Hawk;

public class Application extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Hawk.init(getApplicationContext()).build();


    }

}
