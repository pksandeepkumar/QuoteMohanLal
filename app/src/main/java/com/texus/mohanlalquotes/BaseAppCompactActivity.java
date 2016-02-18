package com.texus.mohanlalquotes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by sandeep on 11/2/16.
 */
public class BaseAppCompactActivity extends AppCompatActivity {

    public void startPage(Class cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
    }

}
