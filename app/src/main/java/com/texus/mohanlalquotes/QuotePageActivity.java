package com.texus.mohanlalquotes;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import texus.task.LoadQuoteTextTask;

/**
 * Created by sandeep on 11/2/16.
 */
public class QuotePageActivity extends BaseAppCompactActivity {

    private RecyclerView                recyclerView;
    private RecyclerView.LayoutManager  mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote_page);

        recyclerView = (RecyclerView) this.findViewById(R.id.rcList);
        recyclerView.setHasFixedSize(false);
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);


        LoadQuoteTextTask task = new LoadQuoteTextTask(this, recyclerView);
        task.execute();
    }
}
