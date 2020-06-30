package co.lap.todolap.data.task

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TaskDao {

    @Query("SELECT * from task_table")
    fun getAllTasks() : LiveData<List<Task>>

    @Query("SELECT * from task_table WHERE id = :id")
    fun getTask(id: Int) : Task

    @Query("DELETE FROM task_table")
    suspend fun deleteAllTasks()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task : Task)

    @Delete
    suspend fun deleteTask(task : Task)

    @Update
    suspend fun updateTask(task : Task)
}