package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.text.isDigitsOnly

//private const val TAG = "MainActivity"
//private const val TEXT_CONTENTS = "TEXTCONTENTS"
class MainActivity : AppCompatActivity() {

    private lateinit var result: EditText
    private lateinit var newNumber: EditText
    private val displayOperations by lazy(LazyThreadSafetyMode.NONE) { findViewById<TextView>(R.id.operation) }
//    private var button1 : Button? = null

    //operands

    private var operand1: Double? = null
    private var operand2: Double = 0.0
    private var pendingOperation = "="

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        result = findViewById(R.id.result)
        newNumber = findViewById(R.id.newNumber)

        //create input buttons
        val button0: Button = findViewById(R.id.button0)
        val button1: Button = findViewById(R.id.button1)
        val button2: Button = findViewById(R.id.button2)
        val button3: Button = findViewById(R.id.button3)
        val button4: Button = findViewById(R.id.button4)
        val button5: Button = findViewById(R.id.button5)
        val button6: Button = findViewById(R.id.button6)
        val button7: Button = findViewById(R.id.button7)
        val button8: Button = findViewById(R.id.button8)
        val button9: Button = findViewById(R.id.button9)
        val buttondot : Button = findViewById(R.id.buttondot)

        //operations
        var buttonadd: Button = findViewById(R.id.buttonadd)
        var buttonminus: Button = findViewById(R.id.buttonminus)
        var buttondivide: Button = findViewById(R.id.buttondivide)
        var buttonmultiply: Button = findViewById(R.id.buttonmultiply)
        var buttonequals: Button = findViewById(R.id.buttonequals)

//        var g = newNumber.text

        var listener = View.OnClickListener { v ->
            val tap = v as Button
            newNumber.append(tap.text)
        }
//        var decimal = View.OnClickListener { v->
//            val check = v as Button
//
//        }
//        fun dec(b:String ,a:String = ".") {
//
//            if (a !in b) {
//                 View.OnClickListener { v ->
//                    val tap = v as Button
//                    newNumber.append(tap.text)
//                }
//            }
//            else {
//                View.OnClickListener { v ->
//                    val tap = v as Button
//                    newNumber.append(tap.text)
//            }
//        }}
        var function = View.OnClickListener { v ->
            val b= (v as Button).text.toString()
            val value = newNumber.text
            if (value.isDigitsOnly()){
                newNumber.append(buttondot.text)
            }
        }
        //into newnumber
        button0.setOnClickListener(listener)
        button1.setOnClickListener(listener)
        button2.setOnClickListener(listener)
        button3.setOnClickListener(listener)
        button4.setOnClickListener(listener)
        button5.setOnClickListener(listener)
        button6.setOnClickListener(listener)
        button7.setOnClickListener(listener)
        button8.setOnClickListener(listener)
        button9.setOnClickListener(listener)
        buttondot.setOnClickListener(function)

        fun performoperation(value:String,operation:String){

            if (operand1 == null){
                operand1 = value.toDouble()
            }
            else{
                operand2 = value.toDouble()
            }
                if (pendingOperation == "="){
                    pendingOperation = operation
                }
                when (pendingOperation){
                    "=" -> operand1 = operand2
                    "/" -> operand1= operand1!! /operand2
                    "-" -> operand1= operand1!!-operand2
                    "+" -> operand1= operand1!!+operand2
                    "x" -> operand1= operand1!!*operand2
                    else ->operand1 = operand2
                }

            result.setText(operand1.toString())
            newNumber.text.clear()
        }


        //into textview
        var op = View.OnClickListener { b ->
            var press = (b as Button).text.toString()
            var value = newNumber.text.toString()
            if (value.isNotEmpty()){
                performoperation(value, press)
            }
            pendingOperation = press
            displayOperations.text = pendingOperation
//            else{
//                displayOperations.text = pendingOperation
//            }
        }
        buttonadd.setOnClickListener(op)
        buttonminus.setOnClickListener(op)
        buttonmultiply.setOnClickListener(op)
        buttondivide.setOnClickListener(op)
        buttonequals.setOnClickListener(op)

    }


}