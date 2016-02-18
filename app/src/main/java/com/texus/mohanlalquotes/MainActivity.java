package com.texus.mohanlalquotes;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.CircleBitmapDisplayer;

import texus.task.DoOperationTask;
import texus.task.OpenPageTask;

public class MainActivity extends BaseAppCompactActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this) .build();
        ImageLoader.getInstance().init(config);

        ImageView imImage = (ImageView) this.findViewById(R.id.imImage);
        ImageLoader imageLoader = ImageLoader.getInstance();
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .displayer(new CircleBitmapDisplayer(Color.WHITE, 4))
                        .build();
        imageLoader.displayImage("drawable://" + R.drawable.image_avatar, imImage, options);
    }

    public void showQuotePage( View view) {
        OpenPageTask task = new OpenPageTask(this, QuotePageActivity.class);
        task.execute();
    }

    public void showQuoteImagesPage( View view) {
        OpenPageTask task = new OpenPageTask(this, QuoteImagesActivity.class);
        task.execute();
    }

    public void showFilmsPage( View view) {
        OpenPageTask task = new OpenPageTask(this, FilmsPageActivity.class);
        task.execute();
    }

    public void showFanPage( View view) {
        OpenPageTask task = new OpenPageTask(this, FanPageActivity.class);
        task.execute();
    }

    public void doRateApp( View view) {
        DoOperationTask task = new DoOperationTask(this, DoOperationTask.OPERATION_TYPE_RATE_APP);
        task.execute();
    }

    public void doShareApp( View view) {
        DoOperationTask task = new DoOperationTask(this, DoOperationTask.OPERATION_TYPE_SHARE_APP);
        task.execute();
    }

    public void doFeedback( View view) {
        DoOperationTask task = new DoOperationTask(this, DoOperationTask.OPERATION_TYPE_FEEDBACK);
        task.execute();
    }
}
