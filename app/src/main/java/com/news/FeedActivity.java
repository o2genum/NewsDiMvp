package com.news;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.news.api.dto.FeedItem;
import com.news.news.R;

import java.net.URL;
import java.util.List;

import nucleus.factory.RequiresPresenter;
import nucleus.view.NucleusActivity;

@RequiresPresenter(FeedPresenter.class)
public class FeedActivity extends NucleusActivity<FeedPresenter> {
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private NewsAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    List<URL> feedUrls = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        mRecyclerView = (RecyclerView) findViewById(R.id.news_recycler_view);

        mLayoutManager = new LinearLayoutManager(this) {
            @Override
            protected int getExtraLayoutSpace(RecyclerView.State state) {
                return getHeight();
            }
        };
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new NewsAdapter(this);
        mRecyclerView.setAdapter(mAdapter);

        mSwipeRefreshLayout.setEnabled(true);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPresenter().request();
                // A workaround for a bug in SwipeRefreshLayout
                // http://stackoverflow.com/questions/26858692/
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mSwipeRefreshLayout.setRefreshing(true);
                    }
                }, 100);
            }
        });

        getPresenter().request();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_feed, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    public void onItems(List<FeedItem> items)
    {
        final boolean shouldScrollToTop = mAdapter.setItems(items);
        // Smooth scrolling to top, not on UI thread
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                if (shouldScrollToTop)
                    mRecyclerView.smoothScrollToPosition(0);
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    public void onNetworkError(Throwable throwable) {
        Snackbar.make(mSwipeRefreshLayout, throwable.getMessage(), Snackbar.LENGTH_LONG).show();
    }
}
