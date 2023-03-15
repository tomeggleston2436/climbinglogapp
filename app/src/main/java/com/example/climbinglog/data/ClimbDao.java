package com.example.climbinglog.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import androidx.room.Delete;


import java.util.List;


@Dao
public interface ClimbDao {
    @Query("SELECT * FROM climbs_table")
    List<Climb> getAll();

    @Insert
    void insertClimb(Climb climb);

    @Query("SELECT * FROM climbs_table ORDER BY id DESC")
    LiveData<List<Climb>> getAllClimbs();

    @Update
    void updateClimb(Climb climb);

    @Delete
    void deleteClimb(Climb climb);

    @Query("DELETE FROM climbs_table")
    void deleteAllClimbs();

}
