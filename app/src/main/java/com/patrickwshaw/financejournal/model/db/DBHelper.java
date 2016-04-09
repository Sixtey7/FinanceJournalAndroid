package com.patrickwshaw.financejournal.model.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.patrickwshaw.financejournal.constants.DBConstants;
import com.patrickwshaw.financejournal.util.LoggingUtil;

/**
 * Created by Patrick on 4/3/2016.
 */
public class DBHelper extends SQLiteOpenHelper {
    private static final LoggingUtil logger = new LoggingUtil(DBHelper.class);

    public DBHelper(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
        logger.logEnter("Constructor(Context)");
        logger.logExit();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        logger.logEnter("onCreate");

        logger.d("In onCreate - Setting up the database");
        String tableCreateSql = DBConstants.createTableCreateText();
        logger.d(tableCreateSql);
        db.execSQL(tableCreateSql);

        logger.logExit();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        logger.logEnter("onUpdate");

        logger.d("On Update - dropping the tables and recreating them");
        //TODO: Eventually we may want to write a real upgrade path

        db.execSQL(DBConstants.createDropTableText());

        onCreate(db);

        logger.logExit();
    }
}
