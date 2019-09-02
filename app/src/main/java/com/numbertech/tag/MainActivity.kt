package com.numbertech.tag

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var i: Int = 1
        var k = i++ + i
        // 測試區
//        var value1
    }

    fun isOdd(input: Int): Boolean = (input and 1) == 1


}
