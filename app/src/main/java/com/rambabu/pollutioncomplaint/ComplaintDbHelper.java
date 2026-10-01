package com.rambabu.pollutioncomplaint;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * SQLite helper for the Pollution Complaint App.
 *
 * Stores complaints locally so citizens can file reports even without
 * an internet connection (offline-first).
 */
public class ComplaintDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "pollution_complaints.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_COMPLAINTS = "complaints";
    private static final String COL_ID = "id";
    private static final String COL_TYPE = "type";
    private static final String COL_DESCRIPTION = "description";
    private static final String COL_LATITUDE = "latitude";
    private static final String COL_LONGITUDE = "longitude";
    private static final String COL_PHOTO_PATH = "photo_path";
    private static final String COL_STATUS = "status";
    private static final String COL_CREATED_AT = "created_at";

    public ComplaintDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_COMPLAINTS + " ("
                + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_TYPE + " TEXT NOT NULL, "
                + COL_DESCRIPTION + " TEXT, "
                + COL_LATITUDE + " REAL, "
                + COL_LONGITUDE + " REAL, "
                + COL_PHOTO_PATH + " TEXT, "
                + COL_STATUS + " TEXT NOT NULL, "
                + COL_CREATED_AT + " INTEGER NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
            int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMPLAINTS);
        onCreate(db);
    }

    /** Inserts a new complaint; returns the row id. */
    public long insertComplaint(Complaint complaint) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_TYPE, complaint.getType().name());
        values.put(COL_DESCRIPTION, complaint.getDescription());
        values.put(COL_LATITUDE, complaint.getLatitude());
        values.put(COL_LONGITUDE, complaint.getLongitude());
        values.put(COL_PHOTO_PATH, complaint.getPhotoPath());
        values.put(COL_STATUS, complaint.getStatus().name());
        values.put(COL_CREATED_AT, complaint.getCreatedAt());
        return db.insert(TABLE_COMPLAINTS, null, values);
    }

    /** Returns all complaints, newest first. */
    public List<Complaint> getAllComplaints() {
        List<Complaint> complaints = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_COMPLAINTS, null, null, null,
                null, null, COL_CREATED_AT + " DESC");
        while (cursor.moveToNext()) {
            Complaint c = new Complaint();
            c.setId(cursor.getLong(cursor.getColumnIndexOrThrow(COL_ID)));
            c.setType(Complaint.Type.valueOf(
                    cursor.getString(cursor.getColumnIndexOrThrow(COL_TYPE))));
            c.setDescription(cursor.getString(
                    cursor.getColumnIndexOrThrow(COL_DESCRIPTION)));
            c.setLatitude(cursor.getDouble(
                    cursor.getColumnIndexOrThrow(COL_LATITUDE)));
            c.setLongitude(cursor.getDouble(
                    cursor.getColumnIndexOrThrow(COL_LONGITUDE)));
            c.setPhotoPath(cursor.getString(
                    cursor.getColumnIndexOrThrow(COL_PHOTO_PATH)));
            c.setStatus(Complaint.Status.valueOf(
                    cursor.getString(cursor.getColumnIndexOrThrow(COL_STATUS))));
            c.setCreatedAt(cursor.getLong(
                    cursor.getColumnIndexOrThrow(COL_CREATED_AT)));
            complaints.add(c);
        }
        cursor.close();
        return complaints;
    }

    /** Updates the status of a complaint (e.g. when it is resolved). */
    public int updateStatus(long id, Complaint.Status status) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_STATUS, status.name());
        return db.update(TABLE_COMPLAINTS, values,
                COL_ID + " = ?", new String[]{String.valueOf(id)});
    }
}
