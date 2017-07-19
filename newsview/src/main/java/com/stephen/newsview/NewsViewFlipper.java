package com.stephen.newsview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ViewFlipper;

/**
 * Created by stephen on 2017/7/19.
 */
public class NewsViewFlipper extends ViewFlipper {
    /**
     * 切换的时间间隔
     */
    private int mFlipIntervalMillis = 2000;
    /**
     * 切换的动画的时间
     */
    private int mDurationMillis = 500;
    private NewsViewFlipperAdapter mAdapter;
    private OnItemClickListener mOnItemClicklistener;

    public NewsViewFlipper(Context context) {
        super(context);
        init();
    }

    public NewsViewFlipper(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setFlipInterval(mFlipIntervalMillis);
        setInAnimation(inFromBottomAnimation());
        setOutAnimation(outToTopAnimation());
        setAutoStart(false);
    }

    private void start() {
        if (this.mAdapter == null || this.mAdapter.getCount() <= 0) {
            return;
        }
        removeAllViews();
        //只有数据源大于2条的时候才会开启自动切换
        if (this.mAdapter.getCount() > 1)
            setAutoStart(true);
        else
            setAutoStart(false);
        for (int i = 0; i < this.mAdapter.getCount(); i++) {
            View view = this.mAdapter.getView(getContext(), i);
            addView(view);
            if (mOnItemClicklistener != null) {
                view.setTag(i);
                view.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mOnItemClicklistener.onClick((Integer) view.getTag());
                    }
                });
            }
        }
    }

    @Override
    public void setOnTouchListener(OnTouchListener l) {
    }

    /**
     * 定义从下侧进入的动画效果
     *
     * @return
     */
    protected Animation inFromBottomAnimation() {
        Animation inFromBottomAnimation = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 1.0f, Animation.RELATIVE_TO_PARENT, 0.0f);
        inFromBottomAnimation.setDuration(mDurationMillis);
        inFromBottomAnimation.setInterpolator(new AccelerateInterpolator());
        return inFromBottomAnimation;
    }

    /**
     * 定义从上侧退出的动画效果
     *
     * @return
     */
    protected Animation outToTopAnimation() {
        Animation outToTopAnimation = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, -1.0f);
        outToTopAnimation.setDuration(mDurationMillis);
        outToTopAnimation.setInterpolator(new AccelerateInterpolator());
        return outToTopAnimation;
    }

    /**
     * 设置滚动的时间间隔
     */
    public void setFlipIntervalTime(int mFlipIntervalMillis) {
        this.mFlipIntervalMillis = mFlipIntervalMillis;
    }

    /**
     * 设置滚动的动画持续时间
     */
    public void setDurationTime(int mDurationMillis) {
        this.mDurationMillis = mDurationMillis;
    }

    /**
     * 设置适配器
     */
    public void setAdapter(NewsViewFlipperAdapter mAdapter) {
        this.mAdapter = mAdapter;
        start();
    }

    /**
     * 设置Item点击事件
     */
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClicklistener = listener;
        start();
    }

    public interface OnItemClickListener {
        void onClick(int position);
    }
}