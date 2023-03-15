package com.example.climbinglog.data;

import android.app.Application;

import androidx.room.Room;

public class ClimbingLogApplication extends Application {
    private ClimbDatabase climbDatabase;
    private ClimbRepository climbRepository;
    private static ClimbingLogApplication instance;

    @Override
    public void onCreate() {
        instance = this;

        super.onCreate();
        climbDatabase = Room.databaseBuilder(this, ClimbDatabase.class, "climb_database")
                .build();
        climbRepository = new ClimbRepository(climbDatabase.climbDao());
    }
    public static ClimbingLogApplication getInstance() {
        return instance;
    }

    public ClimbRepository getClimbRepository() {
        return climbRepository;
    }

    public ClimbDatabase getClimbDatabase() {
        return climbDatabase;
    }
}
