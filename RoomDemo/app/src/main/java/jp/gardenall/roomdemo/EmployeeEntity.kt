package jp.gardenall.roomdemo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "employee-table")
data class EmployeeEntity(
    @PrimaryKey(autoGenerate = true) // ここで設定したものをデータベースにそれぞれのデータとして格納されるわけだが、
    // ここで設定したものについては自動的に関連づけられたものになるということ
    var id: Int = 0,
    var name: String = "",
    @ColumnInfo(name = "email-id") // データベースに保存される際のcolumnの名前がデフォルトなら下のemailのままだが、
    // これを付け足すことで好きに変更できる
    var email: String = ""
)
