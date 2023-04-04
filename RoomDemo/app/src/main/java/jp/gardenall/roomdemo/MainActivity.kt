package jp.gardenall.roomdemo

import android.app.AlertDialog
import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import jp.gardenall.roomdemo.databinding.ActivityMainBinding
import jp.gardenall.roomdemo.databinding.DialogUpdateBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        val employeeDao = (application as EmployeeApp).db.employeeDao()
        binding?.btnAdd?.setOnClickListener {
            addRecord(employeeDao)
        }

        lifecycleScope.launch { // バックエンドで起きることを記述するみたいな趣旨の発言があった
            employeeDao.fetchAllEmployees().collect{
                val list = ArrayList(it)
                setUpListOfDataIntoRecyclerView(list, employeeDao)
            }
        }
    }

    private fun addRecord(employeeDao: EmployeeDao){
        val name = binding?.etName?.text.toString()
        val email = binding?.etEmailId?.text.toString()

        if (email.isNotEmpty() && name.isNotEmpty()){
            lifecycleScope.launch{
                employeeDao.insert(EmployeeEntity(name=name, email=email))
                Toast.makeText(applicationContext, "Record saved", Toast.LENGTH_LONG).show() // lifecyclescope内なので
            // 一つ目の引数はthisだと機能しないらしいです
                binding?.etName?.text?.clear()
                binding?.etEmailId?.text?.clear()
            }
        }else{
            Toast.makeText(applicationContext, "Name or Email cannot be blank", Toast.LENGTH_LONG).show()
        }
    }

    private fun setUpListOfDataIntoRecyclerView(employeesList: ArrayList<EmployeeEntity>, employeeDao: EmployeeDao){
        if (employeesList.isNotEmpty()){
            val itemAdapter = ItemAdapter(employeesList,
                {
                    updateId -> updateRecordDialog(updateId, employeeDao) // 多分だけど、updateRecordDialogの処理内容を
                // このactivity内で書いているあたり、delegate的な扱いなのかなと思っている。ちなみにidは
                // holder.ivEdit.setOnClickListenerでinvokeを使うことで渡されている
                },
                {
                    deleteId -> deleteRecordAlertDialog(deleteId, employeeDao)
                })
            binding?.rvItemsList?.layoutManager = LinearLayoutManager(this)
            binding?.rvItemsList?.adapter = itemAdapter
            binding?.rvItemsList?.visibility = View.VISIBLE
            binding?.tvNoRecordsAvailable?.visibility = View.GONE
        }else{
            binding?.rvItemsList?.visibility = View.GONE
            binding?.tvNoRecordsAvailable?.visibility = View.VISIBLE
        }
    }

    private fun updateRecordDialog(id:Int, employeeDao: EmployeeDao){
        val updateDialog = Dialog(this, R.style.Theme_Dialog)
        updateDialog.setCancelable(false)
        val binding = DialogUpdateBinding.inflate(layoutInflater)
        updateDialog.setContentView(binding.root)

        lifecycleScope.launch {
            employeeDao.fetchEmployeeById(id).collect{
                if (it != null){
                    binding.etUpdateName.setText(it.name)
                    binding.etUpdateEmailId.setText(it.email)
                }
            }
        }

        binding.tvUpdate.setOnClickListener {
            val name = binding.etUpdateName.text.toString()
            val email = binding.etUpdateEmailId.text.toString()
            if (name.isNotEmpty() && email.isNotEmpty()){
                lifecycleScope.launch {
                    employeeDao.update(EmployeeEntity(id, name, email)) // 今回は自動的にアカウントにIDが付与される場面ではないので
                // idをパラメータとして当てはめなければいけない
                    Toast.makeText(applicationContext, "Record updated", Toast.LENGTH_LONG).show()
                    updateDialog.dismiss()
                }
            }else{
                Toast.makeText(applicationContext, "Name or Email cannot be blank", Toast.LENGTH_LONG).show()
            }
        }

        binding.tvCancel.setOnClickListener {
            updateDialog.dismiss()
        }

        updateDialog.show()
    }

    private fun deleteRecordAlertDialog(id:Int, employeeDao: EmployeeDao){
        val builder =AlertDialog.Builder(this)
        builder.setTitle("Delete Record")

        builder.setPositiveButton("Yes"){dialogInterface, _ ->
            lifecycleScope.launch {
                employeeDao.delete(EmployeeEntity(id))
                Toast.makeText(applicationContext, "Record deleted successfully.", Toast.LENGTH_LONG).show()
            }
            dialogInterface.dismiss()
        }

        builder.setNegativeButton("No"){dialogInterface, _ ->
            dialogInterface.dismiss() // Dialog will be dismissed
        }

        val alertDialog: AlertDialog = builder.create()
        alertDialog.setCancelable(false)
        alertDialog.show()
    }
}