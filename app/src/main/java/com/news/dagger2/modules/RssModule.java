package com.news.dagger2.modules;

import android.app.Application;
import android.util.Log;

import com.news.api.RssAdapter;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by o2genum on 24/08/16.
 */
@Module
public class RssModule {

    String url;

    public RssModule(String url)
    {
        this.url = url;
    }

    @Provides
    @Singleton
    RssAdapter providesRssAdapter()
    {
        OkHttpClient client = new OkHttpClient();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(client)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build();

        RssAdapter rssAdapter = retrofit.create(RssAdapter.class);
        return rssAdapter;
    }
}
