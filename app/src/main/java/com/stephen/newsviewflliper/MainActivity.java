package com.stephen.newsviewflliper;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.stephen.newsview.NewsViewFlipper;
import com.stephen.newsview.NewsViewFlipperAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stephen on 2017/7/19.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initNewsView();
        initNewsViewFlipper();
    }

    private void initNewsViewFlipper() {
        final NewsViewFlipper newsView = (NewsViewFlipper) findViewById(R.id.viewflipper_main);

        final List<MainNewsBean> datas = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            MainNewsBean mainNewsBean = new MainNewsBean();
            mainNewsBean.setTitle("头条新闻:" + i);
            datas.add(mainNewsBean);
        }

        newsView.setOnItemClickListener(new NewsViewFlipper.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                //Snackbar.make(newsView, (CharSequence) datas.get(position), Snackbar.LENGTH_SHORT).show();
            }
        });
        newsView.setAdapter(new NewsViewFlipperAdapter() {
            @Override
            public int getCount() {
                return datas.size();
            }

            @Override
            public View getView(Context context, int position) {
                View view = View.inflate(context, R.layout.activity_main_item_news, null);
                ((TextView) view.findViewById(R.id.tv_main_new)).setText(datas.get(position).getTitle());
                return view;
            }
        });
    }
}
