package com.example.myapplication2

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.isDigitsOnly
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        var btnResult = findViewById<TextView>(R.id.txtResultId)
     var btn1 = findViewById<Button>(R.id.btnNum1Id)
        btn1.setOnClickListener(){
            btnResult.append("1")
        }
        var btn2 = findViewById<Button>(R.id.btnNum2Id)
        btn2.setOnClickListener(){
            btnResult.append("2")
        }
        var btn3 = findViewById<Button>(R.id.btnNum3Id)
        btn3.setOnClickListener(){
            btnResult.append("3")
        }
        var btn4 = findViewById<Button>(R.id.btnNum4Id)
        btn4.setOnClickListener(){
            btnResult.append("4")
        }
        var btn5 = findViewById<Button>(R.id.btnNum5Id)
        btn5.setOnClickListener(){
            btnResult.append("5")
        }
        var btn6 = findViewById<Button>(R.id.btnNum6Id)
        btn6.setOnClickListener(){
            btnResult.append("6")
        }
        var btn7 = findViewById<Button>(R.id.btnNum7Id)
        btn7.setOnClickListener(){
            btnResult.append("7")
        }
        var btn8 = findViewById<Button>(R.id.btnNum8Id)
        btn8.setOnClickListener(){
            btnResult.append("8")
        }
        var btn9 = findViewById<Button>(R.id.btnNum9Id)
        btn9.setOnClickListener(){
            btnResult.append("9")
        }
        var btn0 = findViewById<Button>(R.id.btnNum0Id)
        btn0.setOnClickListener(){
            btnResult.append("0")
        }
        var btnclear = findViewById<Button>(R.id.btnClearId)
        btnclear.setOnClickListener(){
            btnResult.text = "0"
        }
        var btnAdd = findViewById<Button>(R.id.btnAddId)
        btnAdd.setOnClickListener(){
            btnResult.append(" + ")
        }
        var btnMinus = findViewById<Button>(R.id.btnSubId)
        btnMinus.setOnClickListener(){
            btnResult.append(" - ")
        }
        var btnMult = findViewById<Button>(R.id.btnMulId)
        btnMult.setOnClickListener(){
            btnResult.append(" * ")
        }
        var btnDivid = findViewById<Button>(R.id.btnDivId)
        btnDivid.setOnClickListener(){
            btnResult.append(" / ")
        }
        var btnEqual = findViewById<Button>(R.id.btnEqualId)
        btnEqual.setOnClickListener(){
            btnResult.text = result(btnResult.text.toString()).toString()
        }



    }
}
fun result(data:String):Double{
    var myData = data.split(" ")
    var myans = myData[0].toDouble()
    var num = 0
    for (i in myData){
        if(i == "+"){
            myans += myData[num+1].toDouble()
        }
        if(i == "-"){
            myans -= myData[num+1].toDouble()
        }
        if(i == "*"){
            myans *= myData[num+1].toDouble()
        }
        if(i == "/"){
            myans /= myData[num+1].toDouble()
        }

        num++
    }

    return myans
}