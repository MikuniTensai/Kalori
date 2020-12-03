package com.example.kalori;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.kalori.db.BmiRoomDatabase;
import com.example.kalori.db.dao.BmiDao;
import com.example.kalori.db.entity.BmiEntity;

import java.util.List;

public class BmiRepository {
    //Todo 10
    private BmiDao bmiDao;
    private LiveData<List<BmiEntity>> allBmi;

    public BmiRepository(Application application) {
        BmiRoomDatabase db = BmiRoomDatabase.getDatabase(application);
        bmiDao = db.bmiDao();
        allBmi = bmiDao.getAllBmiData();
    }

    public LiveData<List<BmiEntity>> getAllBmi(){
        return allBmi;
    }

    public void insert(BmiEntity bmiEntity) {
        new insertBmiAsyncTask(bmiDao).execute(bmiEntity);
    }

    private static class insertBmiAsyncTask extends AsyncTask<BmiEntity,Void,Void > {

        private BmiDao asyncTaskDao;

        public insertBmiAsyncTask(BmiDao asyncTaskDao) {
            this.asyncTaskDao = asyncTaskDao;
        }

        @Override
        protected Void doInBackground(BmiEntity... bmiEntities) {
            this.asyncTaskDao.insert(bmiEntities[0]);
            return null;
        }
    }
}
