package com.example.kalori.data;

import android.content.Context;

import androidx.room.Room;

public class AppDbProvider
{
    private static BMIDatabase instance;
    private static BMIDatabase asynchronousInstance;

    public static BMIDatabase getInstance(Context context)
    {
        if(AppDbProvider.instance == null)
        {
            AppDbProvider.instance = Room.databaseBuilder(
                    context, BMIDatabase.class, "kaloriapp.db").allowMainThreadQueries().build();
        }

        return AppDbProvider.instance;
    }

    public static BMIDatabase getAsynchronousInstance(Context context)
    {
        if(AppDbProvider.asynchronousInstance == null)
        {
            AppDbProvider.asynchronousInstance = Room.databaseBuilder(
                    context, BMIDatabase.class, "kaloriapp.db").build();
        }

        return AppDbProvider.asynchronousInstance;
    }
}
