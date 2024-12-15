package com.example.love_putin

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var resultTextView: TextView
    private var currentInput = ""
    private var previousInput = ""
    private var operator: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resultTextView = findViewById(R.id.resultTextView)

        val buttons = mapOf(
            R.id.button0 to "0", R.id.button1 to "1", R.id.button2 to "2",
            R.id.button3 to "3", R.id.button4 to "4", R.id.button5 to "5",
            R.id.button6 to "6", R.id.button7 to "7", R.id.button8 to "8",
            R.id.button9 to "9", R.id.buttonPlus to "+", R.id.buttonMinus to "-",
            R.id.buttonMultiply to "*", R.id.buttonDivide to "/"
        )

        for ((id, value) in buttons) {
            findViewById<Button>(id).setOnClickListener { onButtonClick(value) }
        }

        findViewById<Button>(R.id.buttonClear).setOnClickListener { onClear() }
        findViewById<Button>(R.id.buttonEquals).setOnClickListener { onEquals() }
    }

    private fun onButtonClick(value: String) {
        if (value in listOf("+", "-", "*", "/")) {
            if (currentInput.isNotEmpty()) {
                previousInput = currentInput
                currentInput = ""
            }
            operator = value
        } else {
            currentInput += value
        }
        resultTextView.text = currentInput
    }

    private fun onClear() {
        currentInput = ""
        previousInput = ""
        operator = null
        resultTextView.text = "0"
    }

    private fun onEquals() {
        if (operator != null && previousInput.isNotEmpty() && currentInput.isNotEmpty()) {
            val num1 = previousInput.toDouble()
            val num2 = currentInput.toDouble()
            val result = when (operator) {
                "+" -> num1 + num2
                "-" -> num1 - num2
                "*" -> num1 * num2
                "/" -> if (num2 != 0.0) num1 / num2 else {
                    Toast.makeText(this, "нельзя делить на ноль", Toast.LENGTH_SHORT).show()
                    return
                }
                else -> 0.0
            }
            resultTextView.text = result.toString()
            currentInput = result.toString()
            operator = null
            previousInput = ""
        }
    }
}
