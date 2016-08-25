package com.news.base;

import android.app.Application;

import com.news.dagger2.components.DaggerNewsComponent;
import com.news.dagger2.components.NewsComponent;
import com.news.dagger2.modules.RssModule;

/**
 * Created by o2genum on 25/08/16.
 */
public class NewsApp extends Application {

    private static NewsApp sInstance;

    private NewsComponent mNewsComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        sInstance.initializeInstance();
    }

    protected void initializeInstance()
    {
        mNewsComponent = DaggerNewsComponent.builder()
                .rssModule(new RssModule("https://lenta.ru/rss/"))
                .build();
    }

    public static NewsApp getsInstance()
    {
        return sInstance;
    }

    public NewsComponent getNewsComponent() {
        return mNewsComponent;
    }
}
