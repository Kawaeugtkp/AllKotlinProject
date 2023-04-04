package jp.gardenall.myfirstapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) //.activity_mainって書いてあることによって、activity_main.xmlがlayoutに使われていることと繋がる。違う、これは階層構造で、Rはresディレクトリを指していていて、その下のlayoutディレクトリ。。。と繋がる

        val btnClickMe = findViewById<Button>(R.id.mybutton)
        val tvMyTextView = findViewById<TextView>(R.id.textView)
        var timesClicked = 0
        btnClickMe.setOnClickListener {
            timesClicked += 1
            tvMyTextView.text = timesClicked.toString()

            Toast.makeText(this, "Hello Tatsu", Toast.LENGTH_LONG).show()
        }
    }
}