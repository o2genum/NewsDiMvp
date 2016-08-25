package com.news.api;

import com.news.api.dto.Feed;

import retrofit2.http.GET;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by o2genum on 24/08/16.
 */
public interface RssAdapter {

    // Works only this way for Retrofit 2
    @GET
    Observable<Feed> getFeed(@Url String unused);
}