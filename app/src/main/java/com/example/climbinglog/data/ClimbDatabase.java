package com.example.climbinglog.data;

import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Climb.class}, version = 3, exportSchema = false)
public abstract class ClimbDatabase extends RoomDatabase {

    private static ClimbDatabase sInstance;

    public static ClimbDatabase getInstance(Context context) {
        if (sInstance == null) {
            sInstance = Room.databaseBuilder(context.getApplicationContext(),
                            ClimbDatabase.class, "climb_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return sInstance;
    }

    public abstract ClimbDao climbDao();

}