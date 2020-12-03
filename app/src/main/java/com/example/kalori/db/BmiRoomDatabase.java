package com.example.kalori.db;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.kalori.db.dao.BmiDao;
import com.example.kalori.db.entity.BmiEntity;

//Todo 7 adda annotation Database
@Database(entities = { BmiEntity.class }, version = 1, exportSchema = false)
public abstract class BmiRoomDatabase extends RoomDatabase {
    private static BmiRoomDatabase Instance;
    private static String Database_Name = "bmi_database";
    public abstract BmiDao bmiDao();

    //Todo 8 add function to create database
    public static BmiRoomDatabase getDatabase(final Context context){
        if (Instance == null){
            synchronized (BmiRoomDatabase.class){
                if (Instance == null){
                    Instance = Room.databaseBuilder(context.getApplicationContext(),
                            BmiRoomDatabase.class, Database_Name)
                            .build();
                }
            }
        }
        return Instance;
    }

    //Todo 18 populate database
    //Todo 19
    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(Instance).execute();
                }
            };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final BmiDao mDao;

        PopulateDbAsync(BmiRoomDatabase db) {
            mDao = db.bmiDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            mDao.deleteAllBmi();
            BmiEntity bmi = new BmiEntity();
            bmi.setBmiResult(10);
            bmi.setBeratBadan(100);
            bmi.setTinggiBadan(180);
            mDao.insert(bmi);
            return null;
        }
    }
}
