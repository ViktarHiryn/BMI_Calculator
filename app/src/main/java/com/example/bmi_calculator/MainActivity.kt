package com.example.bmi_calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val weightText = findViewById<EditText>(R.id.etWeight)
        val heightText = findViewById<EditText>(R.id.etHeight)
        val calcButton = findViewById<Button>(R.id.btnCalculate)

        calcButton.setOnClickListener {
            val weight = weightText.text.toString()
            val height = heightText.text.toString()
            if (validateInpute(weight, height)) {
                val BMI = weight.toFloat()/((height.toFloat()/100) * (height.toFloat()/100))
                //gives result with two decimal places
                val BMI2digits = String.format("%.2f", BMI).toFloat()
                displayResult(BMI2digits)
            }
        }
    }

    private fun validateInpute(weight:String?, height:String?) : Boolean {
        return when {
            weight.isNullOrEmpty() ->{
                Toast.makeText(this, "Weight is empty", Toast.LENGTH_LONG).show()
                return false
            }
            height.isNullOrEmpty() ->{
                Toast.makeText(this, "Height is empty", Toast.LENGTH_LONG).show()
                return false
            }
            else -> {
                return true
            }
        }
    }
    private fun displayResult(BMI:Float){
        val resultIndex = findViewById<TextView>(R.id.tvIndex)
        val resultDescription = findViewById<TextView>(R.id.tvResult)
        val info = findViewById<TextView>(R.id.tvInfo)

        resultIndex.text = BMI.toString()
        info.text = "Normal range is 18.5 - 24.9"

        var resultText = ""
        var color = 0

        when{
            BMI < 18.50 ->{
                resultText = "Underweight"
                color = R.color.underweight
            }
            BMI in 18.50..24.99 ->{
                resultText = "Healthy"
                color = R.color.normal
            }
            BMI in 25.0..29.99 ->{
                resultText = "Overweight"
                color = R.color.overweight
            }
            BMI >29.99 ->{
                resultText = "Obese"
                color = R.color.obese
            }
        }
        resultDescription.setTextColor(ContextCompat.getColor(this, color))
        resultDescription.text = resultText
    }
}