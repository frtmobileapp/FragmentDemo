package com.nd.frt.fragmentdemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.nd.frt.fragmentdemo.R;
import com.nd.frt.fragmentdemo.model.UserInfo;

public class DeputyActivity extends AppCompatActivity {

    public static final String PARAM_USER_INFO = "user_info";
    public static final String PARAM_USER_INDEX = "user_index";
    private UserInfo mUserInfo;
    private int mIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deputy);
        ActionBar supportActionBar = getSupportActionBar();
        Intent intent = getIntent();
        mUserInfo = (UserInfo) intent.getSerializableExtra(PARAM_USER_INFO);
        mIndex = intent.getIntExtra(PARAM_USER_INDEX, 0);
        assert supportActionBar != null;
        supportActionBar.setDisplayHomeAsUpEnabled(true);
        supportActionBar.setTitle(mUserInfo.userName);
        supportActionBar.setSubtitle(mUserInfo.content);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
