package com.patrickwshaw.financejournal.model.dao;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;

import com.patrickwshaw.financejournal.constants.DBConstants;
import com.patrickwshaw.financejournal.model.Entry;
import com.patrickwshaw.financejournal.model.db.DBHelper;
import com.patrickwshaw.financejournal.util.LoggingUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Patrick on 4/3/2016.
 */
public class EntryDAO {
    private static final LoggingUtil logger = new LoggingUtil("EntryDAO", "EntryDAO");

    private static DBHelper dbh;

    public EntryDAO(Activity context) {
        logger.logEnter("Constructer(Activity)");
        if (dbh == null) {
            logger.d("Creating a new database helper");
            dbh = new DBHelper(context);
        }

        logger.logExit();
    }

    public int getNextId() {
        logger.logEnter("getNextId");

        String GET_MAX_ID_SQL = "SELECT MAX(" + DBConstants.ENTRY_ID_COLUMN_NAME + ") FROM " + DBConstants.ENTRY_TABLE_NAME + ";";

        logger.d(GET_MAX_ID_SQL);

        Cursor cursor = dbh.getReadableDatabase().rawQuery(GET_MAX_ID_SQL, null);

        //default the value to 1
        int valueToReturn = 1;
        if ((cursor != null) && (cursor.getCount() > 0)) {
            logger.d("Results were returned from the database.  Total count: " + cursor.getCount());
            cursor.moveToFirst();
            int cursorValue = cursor.getInt(0);
            logger.d("The current biggest id in the database is: " + cursorValue);
            valueToReturn = cursorValue + 1;
        }
        else {
            logger.d ("No results from database, returning the default: " + valueToReturn);
        }

        cursor.close();
        logger.logExit();
        return valueToReturn;
    }


    public Entry get(int idToGet) {
        logger.logEnter("get(int)");
        Entry valueToReturn = null;

        String GET_ENTRY_SQL = "SELECT * FROM " + DBConstants.ENTRY_TABLE_NAME +
                " WHERE " + DBConstants.ENTRY_ID_COLUMN_NAME + " = " + idToGet + ";";

        logger.d(GET_ENTRY_SQL);

        Cursor cursor = dbh.getReadableDatabase().rawQuery(GET_ENTRY_SQL, null);
        if ((cursor != null) && (cursor.getCount() > 0)) {
            if (cursor.getCount() > 1) {
                logger.w("More than Entry was found for id: " + idToGet);
            }
            cursor.moveToFirst();
            valueToReturn = new Entry(cursor);
        }

        if (valueToReturn == null) {
            logger.w("Returning a NULL Entry!");
        }

        cursor.close();
        logger.logExit();
        return valueToReturn;
    }

    public List<Entry> getAll() {
        logger.logEnter("getAll");
        List<Entry> listToReturn = new ArrayList<>();

        String GET_ALL_SQL = "SELECT * FROM " + DBConstants.ENTRY_TABLE_NAME + ";";

        Cursor cursor = dbh.getReadableDatabase().rawQuery(GET_ALL_SQL, null);

        if (cursor != null) {
            logger.d("There were " + cursor.getCount() + " entries returned!");
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    listToReturn.add(new Entry(cursor));
                    cursor.moveToNext();
                }
            }
        }

        cursor.close();
        logger.logExit();
        return listToReturn;
    }

    public boolean keyExists(int id) {
        logger.logEnter("keyExists(id)");

        Entry entry = this.get(id);
        logger.logExit();
        return (entry == null);
    }

    public boolean save(Entry entryToSave) {
        logger.logEnter("save(Entry)");

        if (entryToSave.getId() == null) {
            logger.d("Id was null, setting it!");
            entryToSave.setId(this.getNextId());
        }

        ContentValues contentValues = entryToSave.buildContentValues();

        long returnValue = -5;
        if (this.keyExists(entryToSave.getId())) {
            //we're updating
            logger.d("Creating a new Entry!");
            returnValue = dbh.getWritableDatabase().insert(DBConstants.ENTRY_TABLE_NAME, null, contentValues);
        }
        else {
            logger.d("Updating Entry with id: " + entryToSave.getId());
            returnValue = dbh.getWritableDatabase().update(DBConstants.ENTRY_TABLE_NAME, contentValues, DBConstants.ENTRY_ID_COLUMN_NAME + " = ?", new String[]{String.valueOf(entryToSave.getId())});
        }

        logger.logExit();
        return (returnValue > 0);
    }

    public boolean delete(int idToDelete) {
        logger.logEnter("delete(idToDelete)");

        String DELETE_ENTRY_SQL = "DELETE FROM " + DBConstants.ENTRY_TABLE_NAME + " WHERE " + DBConstants.ENTRY_ID_COLUMN_NAME + " = " + idToDelete + ";";

        logger.d(DELETE_ENTRY_SQL);

        dbh.getWritableDatabase().rawQuery(DELETE_ENTRY_SQL, null);

        logger.logExit();
        return (!keyExists(idToDelete));
    }

    public void deleteAll() {
        logger.logEnter("deleteAll");

        String DELETE_ALL_SQL = "DELETE FROM " + DBConstants.ENTRY_TABLE_NAME + ";";

        logger.d(DELETE_ALL_SQL);

        dbh.getWritableDatabase().rawQuery(DELETE_ALL_SQL, null);

        logger.logExit();
    }
}
