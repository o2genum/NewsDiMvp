package com.news;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.news.api.dto.FeedItem;
import com.news.news.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by o2genum on 25/06/15.
 */
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private FeedItem[] mItems;

    private Context context;

    public NewsAdapter(Context context) {
        this.context = context;

        this.mItems = new FeedItem[]{};
    }

    public boolean setItems(List<FeedItem> items) {
        // Computing difference between the new dataset and the old one
        int indexOfOldFirstInNew = -1;
        if ((mItems != null && mItems.length >= 1) && (items != null && items.size() >= 1)) {
            indexOfOldFirstInNew = items.indexOf(mItems[0]);
        }
        mItems = items.toArray(new FeedItem[]{});
        if (indexOfOldFirstInNew > 0) {
            notifyItemRangeInserted(0, indexOfOldFirstInNew);
            return true;
        } else {
            notifyDataSetChanged();
        }
        return false;
    }

    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mItem = mItems[position];
        holder.mTitleTextView.setText(holder.mItem.getTitle());
        if (holder.mItem.getEnclosure() != null)
            Picasso.with(context).load(holder.mItem.getEnclosure().getUrl()).into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mItems.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private View mCardView;
        private TextView mTitleTextView;
        private ImageView mImageView;

        private FeedItem mItem;

        public ViewHolder(View v) {
            super(v);
            mCardView = v.findViewById(R.id.news_card_view);
            mTitleTextView = (TextView) v.findViewById(R.id.news_item_title);
            mImageView = (ImageView) v.findViewById(R.id.news_item_photo);

            mCardView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, DescriptionActivity.class);
            intent.putExtra(DescriptionActivity.DESCRIPTION, mItem.getDescription().trim());
            context.startActivity(intent);
        }
    }
}
