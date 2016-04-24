package com.patrickwshaw.financejournal.model.dao;


import android.app.Activity;

/**
 * Created by minty on 4/10/16.
 */
public final class DaoFactory {
    private static DaoFactory instance;

    private Activity context;
    private EntryDAO entryDAO;

    private DaoFactory() {
        //no one should ever call this
    }

    private DaoFactory(Activity context) {
        this.context = context;
    }

    public static DaoFactory getInstance(Activity context) {
        if (instance == null) {
            instance = new DaoFactory(context);
        }

        return instance;
    }

    public EntryDAO getEntryDAO() {
        if (this.entryDAO == null) {
            this.entryDAO = new EntryDAO(context);
        }

        return this.entryDAO;
    }
}
