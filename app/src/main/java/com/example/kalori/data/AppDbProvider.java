package com.example.kalori.data;

import android.content.Context;

import androidx.room.Room;

public class AppDbProvider
{
    private static KaloriDatabase instance;
    private static KaloriDatabase asynchronousInstance;

    public static KaloriDatabase getInstance(Context context)
    {
        if(AppDbProvider.instance == null)
        {
            AppDbProvider.instance = Room.databaseBuilder(
                    context, KaloriDatabase.class, "kaloriapp.db").allowMainThreadQueries().build();
        }

        return AppDbProvider.instance;
    }

    public static KaloriDatabase getAsynchronousInstance(Context context)
    {
        if(AppDbProvider.asynchronousInstance == null)
        {
            AppDbProvider.asynchronousInstance = Room.databaseBuilder(
                    context, KaloriDatabase.class, "kaloriapp.db").build();
        }

        return AppDbProvider.asynchronousInstance;
    }
}
