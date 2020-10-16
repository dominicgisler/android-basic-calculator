package ch.gislersoftware.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.ceil
import kotlin.math.floor

class MainActivity : AppCompatActivity() {
    var outcome: Double = 0.0
    var input: String = ""
    var operator: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onNumberClick(view: View) {
        input += (view as Button).text.toString()
        result.text = input
    }

    fun onOperatorClick(view: View) {
        if (operator != "") {
            calculate(operator)
        } else {
            outcome = input.toDouble()
            input = ""
        }
        operator = (view as Button).text.toString()
        firstInput.text = "${getDoubleAsString(outcome)} $operator"
    }

    fun onCalculateClick(view: View) {
        calculate(operator)
    }

    fun onClearClick(view: View) {
        operator = ""
        input = ""
        outcome = 0.0
        renderResult()
    }

    fun onDecimalClick(view: View) {
        if (!input.contains(".")) {
            input += "."
        }
        result.text = input
    }

    fun onInvertClick(view: View) {
        if (input == "") {
            return
        }
        if (input.startsWith("-")) {
            input = input.removePrefix("-")
        } else {
            input = "-$input";
        }
        result.text = input
    }

    fun onBackClick(view: View) {
        input = input.dropLast(1)
        result.text = input
    }

    private fun calculate(operator: String) {
        if (input == "" || operator == "") {
            return
        }
        when (operator) {
            "+" -> outcome += input.toDouble()
            "-" -> outcome -= input.toDouble()
            "*" -> outcome *= input.toDouble()
            "/" -> outcome /= input.toDouble()
            "%" -> outcome %= input.toDouble()
        }
        renderResult()
    }

    private fun renderResult() {
        firstInput.text = ""
        result.text = getDoubleAsString(outcome)
        input = ""
    }

    private fun getDoubleAsString(value: Double): String {
        if (ceil(value) == floor(value)) {
            return value.toInt().toString()
        } else {
            return value.toString()
        }
    }
}