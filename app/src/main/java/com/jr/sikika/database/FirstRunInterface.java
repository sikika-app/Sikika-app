package com.jr.sikika.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface FirstRunInterface {

    @Query("select * from firstRun")
    List<FirstRun> selectAll();

    @Insert()
    void insert(FirstRun firstRun);
}
