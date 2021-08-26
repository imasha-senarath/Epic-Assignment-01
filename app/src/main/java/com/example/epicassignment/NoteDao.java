package com.example.epicassignment;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface NoteDao {
    @Insert(onConflict = REPLACE)
    void insert(NoteData noteData);

    @Query("SELECT * FROM notes")
    List<NoteData> getAll();
}
