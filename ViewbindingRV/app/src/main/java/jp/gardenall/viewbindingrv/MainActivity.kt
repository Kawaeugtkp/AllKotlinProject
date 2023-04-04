package jp.gardenall.viewbindingrv

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import jp.gardenall.viewbindingrv.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        val adapter = MainAdapter(TaskList.taskList)
//        binding?.taskRv?.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
//        activity_mainでlayoutManager消してこのコードを書いても同じらしい
        binding?.taskRv?.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()

        binding = null
    }
}