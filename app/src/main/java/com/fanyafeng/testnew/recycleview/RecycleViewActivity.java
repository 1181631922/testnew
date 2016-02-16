package com.fanyafeng.testnew.recycleview;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.fanyafeng.testnew.ABaseActivity;
import com.fanyafeng.testnew.R;

public class RecycleViewActivity extends ABaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_recycle_view);
        super.initView();
        title="TestRecyclerView";
    }



    @Override
    protected void initData() {
        super.initData();
    }
}
