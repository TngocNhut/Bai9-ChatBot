package com.tngocnhat.chatbot;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class ChatDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "chatbot.db";
    private static final int DATABASE_VERSION = 1;

    // Table and columns
    private static final String TABLE_MESSAGES = "messages";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_SESSION_USER = "session_user";
    private static final String COLUMN_SENDER_ROLE = "sender_role";
    private static final String COLUMN_CONTENT = "content";
    private static final String COLUMN_TIMESTAMP = "timestamp";

    public ChatDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_MESSAGES + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_SESSION_USER + " TEXT, " +
                COLUMN_SENDER_ROLE + " TEXT, " +
                COLUMN_CONTENT + " TEXT, " +
                COLUMN_TIMESTAMP + " INTEGER)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MESSAGES);
        onCreate(db);
    }

    // Add a new message
    public long addMessage(String sessionUser, String senderRole, String content) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_SESSION_USER, sessionUser);
        values.put(COLUMN_SENDER_ROLE, senderRole);
        values.put(COLUMN_CONTENT, content);
        values.put(COLUMN_TIMESTAMP, System.currentTimeMillis());

        long id = db.insert(TABLE_MESSAGES, null, values);
        db.close();
        return id;
    }

    // Get all messages for a specific session
    public List<Message> getMessagesForSession(String sessionUser) {
        List<Message> messages = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_MESSAGES,
                null,
                COLUMN_SESSION_USER + "=?",
                new String[] { sessionUser },
                null, null,
                COLUMN_TIMESTAMP + " ASC");

        if (cursor.moveToFirst()) {
            do {
                Message message = new Message();
                message.setId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)));
                message.setSessionUser(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SESSION_USER)));
                message.setSenderRole(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SENDER_ROLE)));
                message.setContent(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CONTENT)));
                message.setTimestamp(cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_TIMESTAMP)));
                messages.add(message);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return messages;
    }

    // Get all unique session users (for manager view)
    public List<String> getAllSessions() {
        List<String> sessions = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(true, TABLE_MESSAGES,
                new String[] { COLUMN_SESSION_USER },
                null, null, null, null,
                COLUMN_TIMESTAMP + " DESC", null);

        if (cursor.moveToFirst()) {
            do {
                sessions.add(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SESSION_USER)));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return sessions;
    }
}
