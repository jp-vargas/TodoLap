package co.lap.todolap.koin

import android.app.Application
import androidx.room.Room
import co.lap.todolap.data.AppDatabase
import co.lap.todolap.data.task.TaskDao
import co.lap.todolap.data.task.TaskRepository
import co.lap.todolap.viewmodel.TaskViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private const val DATABASE_NAME : String = "todo_database"

val viewModelModule = module {
    viewModel { TaskViewModel(get()) }
}

val databaseModule = module {
    fun provideDatabase(application: Application) : AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    fun provideDao(database: AppDatabase) : TaskDao {
        return database.taskDao()
    }

    single { provideDatabase(androidApplication()) }
    single { provideDao(get()) }
}

val repositoryModule = module {
    fun provideTaskRepository(taskDao: TaskDao) : TaskRepository {
        return TaskRepository(taskDao)
    }

    single { provideTaskRepository(get()) }
}