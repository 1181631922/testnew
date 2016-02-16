package com.fanyafeng.testnew.okhttp;

import android.os.AsyncTask;

/**
 * Created by fanyafeng on 2015/12/29,0029.
 */
public class BaseAsyncTask extends AsyncTask<Object, Object, Object> {
    //    ui
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    //接收返回值
    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
    }

    //执行同步方法
    @Override
    protected Object doInBackground(Object... params) {
        return null;
    }
}
