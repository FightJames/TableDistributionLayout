package com.numbertech.tag

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        for (i in 0..30) {
            tableDistributionLayout.addView(TextView(this).apply {
                text = i.toString()
            })
        }
        button.setOnClickListener {
            if (editText.text.isNullOrEmpty().not()) {
                tableDistributionLayout.columnCount = editText.text.toString().toInt()
                tableDistributionLayout.requestLayout()
            }
        }
    }
}
