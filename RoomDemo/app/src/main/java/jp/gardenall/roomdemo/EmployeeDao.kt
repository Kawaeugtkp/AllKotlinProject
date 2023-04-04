package jp.gardenall.roomdemo

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface EmployeeDao {

    @Insert
    suspend fun insert(employeeEntity: EmployeeEntity)

    @Update
    suspend fun update(employeeEntity: EmployeeEntity)

    @Delete
    suspend fun delete(employeeEntity: EmployeeEntity)

    @Query("SELECT * FROM `employee-table`") // SELECTの隣の*はtableの中のどのcolumnの要素が欲しいのかを示すところで、
    // この場合は全てのcolumnってことになっている
    fun fetchAllEmployees():Flow<List<EmployeeEntity>> // このFlowっていうのはデータベースの更新を自動的に反映してくれる、らしい、、。
    // suspend funをつけるとエラーになってFlowの効果を享受できないらしいです

    @Query("SELECT * FROM `employee-table` where id=:id") // 珍しくここのid=:idの右のidは下の関数のパラメータで
    // 持ってこられたものを使うこととなっている。関数より上に書いてあるのに。
    fun fetchEmployeeById(id:Int):Flow<EmployeeEntity>
}