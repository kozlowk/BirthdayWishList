package com.example.kamil.birthdaywish;

import android.app.Application;
import io.realm.Realm;
import io.realm.RealmConfiguration;


public class BirthdayApp extends Application {

    @Override public void onCreate() {
        super.onCreate();
        Realm.init(this);

        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build();
        //automatyczne usuwanie konfliktów
        Realm.setDefaultConfiguration(realmConfiguration);
    }
}