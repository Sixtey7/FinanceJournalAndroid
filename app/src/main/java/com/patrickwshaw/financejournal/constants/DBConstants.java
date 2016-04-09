package com.patrickwshaw.financejournal.constants;

/**
 * Created by patri on 4/3/2016.
 */
public final class DBConstants {
    public static final String DATABASE_NAME = "com_patrickwshaw_finance_journal";

    public static final int DATABASE_VERSION = 1;

    /**
     * Entity Table
     */
    public static final String ENTITY_TABLE_NAME = "Entity";

    public static final String ENTITY_ID_COLUMN_NAME = "ID";
    public static final String SOURCE_COLUMN_NAME = "SOURCE";
    public static final String AMOUNT_COLUMN_NAME = "AMOUNT";
    public static final String DATE_COLUMN_NAME = "DATE";
    public static final String NOTES_COLUMN_NAME = "NOTES";
    public static final String ESTIMATE_COLUMN_NAME = "ESTIMATE";

    public static String createTableCreateText() {
        return "CREATE TABLE " + ENTITY_TABLE_NAME + " (" +
            ENTITY_ID_COLUMN_NAME + " INTEGER PRIMARY KEY, " +
                SOURCE_COLUMN_NAME + " TEXT, " +
                AMOUNT_COLUMN_NAME + " REAL, " +
                DATE_COLUMN_NAME + " REAL, " +
                NOTES_COLUMN_NAME + " TEXT);";
    }

    public static String createDropTableText() {
        return "DROP TABLE IF EXISTS " + ENTITY_TABLE_NAME + ";";
    }
}
