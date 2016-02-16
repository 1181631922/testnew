package com.fanyafeng.testnew.DataBaseTest;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.fanyafeng.testnew.BaseActivity;
import com.fanyafeng.testnew.R;

public class SQLOperateActivity extends BaseActivity {
    private TestSQLiteOpenHelper testSQLiteOpenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqloperate);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        title = "测试数据库";
        isShowEmail = true;
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        initView();
        initData();
    }

    private void initView() {
        findViewById(R.id.btn_new_db).setOnClickListener(this);
        findViewById(R.id.btn_add_db).setOnClickListener(this);
        findViewById(R.id.btn_del_db).setOnClickListener(this);
        findViewById(R.id.btn_change_db).setOnClickListener(this);
        findViewById(R.id.btn_search_db).setOnClickListener(this);
        findViewById(R.id.btn_change_dbversion).setOnClickListener(this);
    }

    private void initData() {

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btn_new_db:
                newDatabase();
                break;
            case R.id.btn_add_db:
                addNewData();
                break;
            case R.id.btn_del_db:
                break;
            case R.id.btn_change_db:
                break;
            case R.id.btn_search_db:
                break;
            case R.id.btn_change_dbversion:
                break;
        }
    }

    private void newDatabase() {
        testSQLiteOpenHelper = new TestSQLiteOpenHelper(this);
        SQLiteDatabase sqLiteDatabase = testSQLiteOpenHelper.getWritableDatabase();

    }

    private void addNewData() {

    }
}
