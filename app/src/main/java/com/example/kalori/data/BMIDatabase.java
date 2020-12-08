package com.example.kalori.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class BMIDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}
