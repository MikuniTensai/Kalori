package com.example.kalori.data;

import android.content.Context;
import android.os.AsyncTask;

import androidx.room.RoomDatabase;

import java.lang.ref.WeakReference;

public class DatabaseTask extends AsyncTask<Object, Void, Void>
{
    private WeakReference<Context> databaseContext;
    private DatabaseTaskEventListener eventListener;
    private Object operationResult;

    public DatabaseTask(Context databaseContext, DatabaseTaskEventListener eventListener)
    {
        this.databaseContext = new WeakReference<>(databaseContext);
        this.eventListener = eventListener;
    }

    @Override
    protected Void doInBackground(Object... objects)
    {
        RoomDatabase database = AppDbProvider.getAsynchronousInstance(this.databaseContext.get());
        this.operationResult = this.eventListener.runDatabaseOperation(database, objects);

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid)
    {
        super.onPostExecute(aVoid);
        this.eventListener.onDatabaseOperationFinished(this.operationResult);
    }
}