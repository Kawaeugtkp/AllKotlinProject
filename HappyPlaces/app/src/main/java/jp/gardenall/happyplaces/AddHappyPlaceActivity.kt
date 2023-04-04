package jp.gardenall.happyplaces

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import jp.gardenall.happyplaces.databinding.ActivityAddHappyPlaceBinding
import jp.gardenall.happyplaces.databinding.ActivityMainBinding

class AddHappyPlaceActivity : AppCompatActivity() {
    private var binding: ActivityAddHappyPlaceBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddHappyPlaceBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setSupportActionBar(binding?.toolbarAddPlace)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding?.toolbarAddPlace?.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }
}