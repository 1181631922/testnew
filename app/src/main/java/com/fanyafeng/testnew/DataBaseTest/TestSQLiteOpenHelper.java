package com.fanyafeng.testnew.DataBaseTest;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by fanyafeng on 2016/1/28,0028.
 */
public class TestSQLiteOpenHelper extends BaseSQLiteOpenHelper {

    private static final String DATABASENAME = "test.db";
    private static final int DATABASEVERSION = 1;
    public static final String table_first = "t_first";

    public TestSQLiteOpenHelper(Context context) {
        super(context, DATABASENAME, null, DATABASEVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        super.onCreate(db);
        String create_table_first = "create table " + table_first + "("
                + " _id INTEGER PRIMARY KEY ,"
                + " f_name      VARCHAR(50)  NOT NULL,"
                + " f_sex       VARCHAR(50)  NOT NULL,"
                + " f_address   VARCHAR(255) NOT NULL,"
                + " f_phone     VARCHAR(255) NOT NULL,"
                + " f_age       INTEGER      NOT NULL"
                + ")";
        db.execSQL(create_table_first);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onUpgrade(db, oldVersion, newVersion);
        String create_table_first = "DROP TABLE IF EXISTS " + table_first;
        db.execSQL(create_table_first);
    }
}
