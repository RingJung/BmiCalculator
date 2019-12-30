@file:Suppress("DEPRECATION")

package com.example.bmicalculator

import android.os.Bundle
import android.preference.PreferenceManager.getDefaultSharedPreferences
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        resultButton.setOnClickListener { //버튼 리스너
            saveData(heightEditText.text.toString().toInt(),
                weightEditText.text.toString().toInt())

            startActivity<ResultActivity>(             //액티비티 전환 코드
                "weight" to weightEditText.text.toString(),
                "height" to heightEditText.text.toString()
            )
        }
    }
    private fun saveData(height : Int, weight : Int){
        val pref = getDefaultSharedPreferences(this)
        val editor = pref.edit()

        editor.putInt("KEY_HEIGHT", height)
            .putInt("KEY_WEIGHT", weight)
            .apply()
    }

    private fun loadData(){
        val pref = getDefaultSharedPreferences(this)
        val height = pref.getInt("KEY_HEIGHT", 0)
        val weight = pref.getInt("KEY_WEIGHT", 0)

        if (height != 0 && weight != 0){
            heightEditText.setText(height.toString())
            weightEditText.setText(weight.toString())
        }
    }
}
