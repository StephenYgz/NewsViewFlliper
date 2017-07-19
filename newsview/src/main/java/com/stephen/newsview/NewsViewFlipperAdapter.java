package com.stephen.newsview;

import android.content.Context;
import android.view.View;

/**
 * Created by stephen on 2017/7/19.
 */
public abstract class NewsViewFlipperAdapter {
    public abstract int getCount();

    public abstract View getView(Context context, int position);
}