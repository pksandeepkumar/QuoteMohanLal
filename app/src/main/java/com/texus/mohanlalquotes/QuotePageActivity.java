package com.texus.mohanlalquotes;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

/**
 * Created by sandeep on 11/2/16.
 */
public class QuotePageActivity extends BaseAppCompactActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote_page);
    }
}
