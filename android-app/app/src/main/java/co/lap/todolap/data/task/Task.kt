package co.lap.todolap.data.task

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.*

@Entity(tableName = "task_table")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,

    @ColumnInfo(name="task_title")
    var taskTitle : String,

    @ColumnInfo(name="task_description")
    var taskDescription : String,

    @ColumnInfo(name="category")
    var category : String? = "Default",

    @ColumnInfo(name="date_creation")
    var dateCreation : Long? = Date().time,

    @ColumnInfo(name="date_end")
    var dateEnd : Long? = Date().time,

    @ColumnInfo(name="completed")
    var completed : Boolean? = false,

    @ColumnInfo(name="priority")
    var priority : String? = ""
) : Serializable {}
