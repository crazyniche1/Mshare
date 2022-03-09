package com.crazy.share

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import com.crazy.mshare.SharePd
import com.crazy.share.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {

            val cb = SharePd.ConcreteShareBuilder(this)
                cb.buildShareText("button")
                cb.create().show()
            Toast.makeText(this,"button",Toast.LENGTH_SHORT).show()
        }

    }

}