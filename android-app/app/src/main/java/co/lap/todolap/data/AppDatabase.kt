package co.lap.todolap.data

import androidx.room.Database
import androidx.room.RoomDatabase
import co.lap.todolap.data.task.Task
import co.lap.todolap.data.task.TaskDao

@Database(
    entities = [Task::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}