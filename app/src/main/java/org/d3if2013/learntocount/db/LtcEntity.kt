package org.d3if2013.learntocount.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ltc")
data class LtcEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    var tanggal: Long = System.currentTimeMillis(),
    var mark: String,
    var grade: Int
)