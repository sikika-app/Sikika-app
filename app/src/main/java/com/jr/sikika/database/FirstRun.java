package com.jr.sikika.database;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "firstRun")
public class FirstRun {
    @PrimaryKey(autoGenerate = true)
    int id;
    Boolean firstRun;

    public void setFirstRun(Boolean firstRun) {
        this.firstRun = firstRun;
    }

    public Boolean getFirstRun() {
        return firstRun;
    }
}
