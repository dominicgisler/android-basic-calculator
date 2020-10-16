package ch.gislersoftware.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var outcome : Double = 0.0
    var input : String = ""
    var operator : String = ""

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

    private fun calculate(operator: String) {
        if (input == "" || operator == "") {
            return
        }
        when (operator) {
            "+" -> outcome += input.toDouble()
            "-" -> outcome -= input.toDouble()
            "*" -> outcome *= input.toDouble()
            "/" -> outcome /= input.toDouble()
        }
        input = ""
        renderResult()
    }

    private fun renderResult() {
        result.text = outcome.toString()
    }
}