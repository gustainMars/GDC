package com.gustainmars.gdcapplication.feature.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gustainmars.gdcapplication.feature.data.entity.TaskDto

@Dao
interface TaskDao {

    @Query("SELECT * FROM task_table ORDER BY name ASC")
    fun getAllTasks(): LiveData<List<TaskDto>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(taskDto: TaskDto)
}