package com.news;

import android.os.Bundle;

import com.news.api.dto.Feed;
import com.news.api.RssAdapter;
import com.news.base.NewsApp;

import javax.inject.Inject;

import nucleus.presenter.RxPresenter;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action2;
import rx.functions.Func0;

/**
 * Created by o2genum on 24/08/16.
 */
public class FeedPresenter extends RxPresenter<FeedActivity> {
    @Inject
    RssAdapter mRssAdapter;

    public static final int REQUEST_ITEMS_RESTARTABLE_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstance)
    {
        super.onCreate(savedInstance);
        NewsApp.getsInstance().getNewsComponent().inject(this);

        // Restartable task with Nucleus
        restartableLatestCache(REQUEST_ITEMS_RESTARTABLE_ID,
                // The task
                new Func0<Observable<Feed>>() {
                    @Override
                    public Observable<Feed> call() {
                        return mRssAdapter
                                .getFeed("")
                                .observeOn(AndroidSchedulers.mainThread());
                    }
                },
                // On task result
                new Action2<FeedActivity, Feed>() {
                    @Override
                    public void call(FeedActivity feedActivity, Feed feed) {
                        feedActivity.onItems(feed.getChannel().getFeedItems());
                    }
                },
                // On task exception
                new Action2<FeedActivity, Throwable>() {
                    @Override
                    public void call(FeedActivity feedActivity, Throwable throwable) {
                        feedActivity.onNetworkError(throwable);
                        throwable.printStackTrace();
                    }
                });
    }

    public void request() {
        start(REQUEST_ITEMS_RESTARTABLE_ID);
    }
}
