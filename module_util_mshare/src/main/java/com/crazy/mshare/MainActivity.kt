package com.crazy.mshare

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.crazy.mshare.concreteBuilder.ConcreteShareBuilder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main2)

//        findViewById<TextView>(R.id.tv).setOnClickListener {
//            ShareDirector(ConcreteShareBuilder(this)).construct("你好呀")

//            ConcreteShareBuilder(this).create().shareText("").show()
//        }
    }
}