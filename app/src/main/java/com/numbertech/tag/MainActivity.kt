package com.numbertech.tag

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tableDistributionLayout.addView(TextView(this).apply {
            text =
                "sadlkjflkasjdf;lkjasd;lkvja;lkosdhvjlkasjhdgflk;ahsdg;lkfhjaslk;dgfhjlka;shdgf;lkhasd;lkfghal;kshdgflhasgf"
        })
        for (i in 0..30) {
            tableDistributionLayout.addView(TextView(this).apply {
                text = i.toString()
            })
        }
        visBtn.setOnClickListener {
            if (tableDistributionLayout.visibility == View.GONE) {
                tableDistributionLayout.visibility = View.VISIBLE
            } else {
                tableDistributionLayout.visibility = View.GONE
            }
        }
        button.setOnClickListener {
            if (count.text.isNullOrEmpty().not()) {
                tableDistributionLayout.columnCount = count.text.toString().toInt()
            }

            if (widthSpace.text.isNullOrEmpty().not()) {
                tableDistributionLayout.itemSpaceWidth = widthSpace.text.toString().toInt()
            }

            if (heightSpace.text.isNullOrEmpty().not()) {
                tableDistributionLayout.itemSpaceHeight = heightSpace.text.toString().toInt()
            }
            tableDistributionLayout.requestLayout()
        }
    }
}
