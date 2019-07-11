package com.jr.sikika.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {FirstRun.class}, version = 1, exportSchema = false)
public abstract class DatabaseAccess extends RoomDatabase {

    public abstract FirstRunInterface getRun();
    public static DatabaseAccess databaseAccess;

    public static synchronized DatabaseAccess getInstance(Context context){
        if(databaseAccess == null){
            databaseAccess = Room.databaseBuilder(context, DatabaseAccess.class, "sikika").allowMainThreadQueries().build();
        }
        return databaseAccess;
    }

}
