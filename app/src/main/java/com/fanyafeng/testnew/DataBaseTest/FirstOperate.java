package com.fanyafeng.testnew.DataBaseTest;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by fanyafeng on 2016/1/28,0028.
 */
public class FirstOperate {

    private SQLiteDatabase sqLiteDatabase;

    public FirstOperate(SQLiteDatabase sqLiteDatabase) {
        this.sqLiteDatabase = sqLiteDatabase;
    }

    public void insert(FirstBean firstBean) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("f_name", firstBean.getName());
        contentValues.put("f_sex", firstBean.getSex());
        contentValues.put("f_address", firstBean.getAddress());
        contentValues.put("f_phone", firstBean.getPhone());
        contentValues.put("f_age", firstBean.getAge());
        this.sqLiteDatabase.insert(TestSQLiteOpenHelper.table_first, null, contentValues);
        this.sqLiteDatabase.close();
    }

    public void update(FirstBean firstBean) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("f_sex", firstBean.getSex());
        contentValues.put("f_address", firstBean.getAddress());
        contentValues.put("f_phone", firstBean.getPhone());
        contentValues.put("f_age", firstBean.getAge());
//        String whereClause=""

    }

}
