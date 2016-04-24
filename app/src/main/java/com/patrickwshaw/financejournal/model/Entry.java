package com.patrickwshaw.financejournal.model;

import android.content.ContentValues;
import android.database.Cursor;

import com.patrickwshaw.financejournal.constants.DBConstants;
import com.patrickwshaw.financejournal.util.LoggingUtil;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Patrick on 4/3/2016.
 */
public class Entry {
    private Integer id;
    private String source;
    private Float amount;
    private Float total;
    private Calendar date;
    private Boolean estimate;
    private String notes;

    private static final LoggingUtil logger = new LoggingUtil("Entry", "Entry");

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public Float getTotal() { return total; }

    public void setTotal(Float total) { this.total = total; }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Boolean getEstimate() {
        return estimate;
    }

    public void setEstimate(Boolean estimate) {
        this.estimate = estimate;
    }

    /**
     * Default Constructor
     */
    public Entry() {
        //default constructor
    }

    /**
     * Cursor Constructor
     */
    public Entry(Cursor cursor) {
        logger.logEnter("constructor(cursor)");

        this.id = cursor.getInt(cursor.getColumnIndex(DBConstants.ENTRY_ID_COLUMN_NAME));
        this.source = cursor.getString(cursor.getColumnIndex(DBConstants.SOURCE_COLUMN_NAME));
        this.amount = cursor.getFloat(cursor.getColumnIndex(DBConstants.AMOUNT_COLUMN_NAME));
        this.notes = cursor.getString(cursor.getColumnIndex(DBConstants.NOTES_COLUMN_NAME));

        //set estimate
        int booleanValue = cursor.getInt(cursor.getColumnIndex(DBConstants.ESTIMATE_COLUMN_NAME));
        this.estimate = (booleanValue == 1);

        //set the date
        int columnIndex = cursor.getColumnIndex(DBConstants.DATE_COLUMN_NAME);
        if (!(cursor.isNull(columnIndex))) {
            this.date = Calendar.getInstance();
            Date dateDate = new Date();
            dateDate.setTime(cursor.getLong(columnIndex));
            this.date.setTime(dateDate);
        }

        logger.logExit();
    }

    public ContentValues buildContentValues() {
        logger.logEnter("buildContentValues");
        ContentValues returnValues = new ContentValues();

        returnValues.put(DBConstants.ENTRY_ID_COLUMN_NAME, this.getId());
        returnValues.put(DBConstants.DATE_COLUMN_NAME, this.getDate().getTime().getTime());

        if (this.getSource() != null) {
            returnValues.put(DBConstants.SOURCE_COLUMN_NAME, this.getSource());
        }

        if (this.getAmount() != null) {
            returnValues.put(DBConstants.AMOUNT_COLUMN_NAME, this.getAmount());
        }

        if (this.getNotes() != null) {
            returnValues.put(DBConstants.NOTES_COLUMN_NAME, this.getNotes());
        }

        if (this.getEstimate() != null)
        {
            returnValues.put(DBConstants.ESTIMATE_COLUMN_NAME, (this.getEstimate() ? 1 : 0));
        }

        logger.logExit();
        return returnValues;
    }
}
