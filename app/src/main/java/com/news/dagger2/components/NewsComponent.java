package com.news.dagger2.components;

import com.news.FeedPresenter;
import com.news.dagger2.modules.RssModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by o2genum on 25/08/16.
 */
@Singleton
@Component(modules = { RssModule.class })
public interface NewsComponent {

    void inject(FeedPresenter feedPresenterer);
}
