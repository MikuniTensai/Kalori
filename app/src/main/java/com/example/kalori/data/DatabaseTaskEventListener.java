package com.example.kalori.data;

import androidx.room.RoomDatabase;

public interface DatabaseTaskEventListener {
    Object runDatabaseOperation(RoomDatabase database, Object... params);
    void onDatabaseOperationFinished(Object... results);
}
