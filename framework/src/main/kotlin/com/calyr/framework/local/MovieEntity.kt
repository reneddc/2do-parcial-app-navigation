package com.calyr.framework.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
class MovieEntity(
    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "posterPath")
    val posterPath: String) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0
}