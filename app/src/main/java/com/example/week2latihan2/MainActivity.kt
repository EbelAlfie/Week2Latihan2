package com.example.week2latihan2

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity: AppCompatActivity() {
    private lateinit var buttons: MutableList<Button>
    private lateinit var tvKalkulasi: TextView
    private lateinit var tvHasil: TextView

    private var input: String = ""
    private var operatorOperand: MutableList<String> = mutableListOf()
    private var currentNum: String = ""

    private var operator = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()

    }

    private fun initView() {
        tvKalkulasi = findViewById(R.id.tv_kalkulasi)
        tvHasil = findViewById(R.id.tv_hasil)
        buttons = mutableListOf(
            findViewById(R.id.btn_0),
            findViewById(R.id.btn_1),
            findViewById(R.id.btn_2),
            findViewById(R.id.btn_3),
            findViewById(R.id.btn_4),
            findViewById(R.id.btn_5),
            findViewById(R.id.btn_6),
            findViewById(R.id.btn_7),
            findViewById(R.id.btn_8),
            findViewById(R.id.btn_9),
            findViewById(R.id.btn_5),
            findViewById(R.id.btn_6),
            findViewById(R.id.btn_7),
            findViewById(R.id.btn_8),
            findViewById(R.id.btn_9),
            findViewById(R.id.btn_10),
            findViewById(R.id.btn_11),
            findViewById(R.id.btn_12),
            findViewById(R.id.btn_13),
            findViewById(R.id.btn_14),
            findViewById(R.id.btn_15),
            findViewById(R.id.btn_16)
        )
        for (button in buttons) {
            button.setOnClickListener {
                calculate(button)
            }
        }
    }

    private fun inputNumber(s: String) {
        when(s) {
            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" -> {
                currentNum += s
                input += s
                operator = true
            }
            "+", "-", "/", "*", "%" -> {
                if (!operator) return
                operatorOperand.add(currentNum) //operand ditambahkan
                operatorOperand.add(s) //operator ditambahkan
                currentNum = ""
                input += s
                operator = false
            }
            "c" -> {
                input = ""
                currentNum = ""
                tvHasil.text = ""
                operatorOperand.clear()
            }
            "=" -> {
                if (input.isBlank()) {
                    Toast.makeText(this, "Input tidak boleh kosong!", Toast.LENGTH_LONG).show()
                    return
                }
                try {
                    val result = ExpressionBuilder(input).build()
                    currentNum = result.evaluate().toString()
                    tvHasil.text = currentNum
                    operator = true
                }catch (e:Exception) {
                    Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()
                }
            }
        }
        tvKalkulasi.text = input
    }

    private fun calculate(it: Button) {
        when(it.id) {
            R.id.btn_0 -> inputNumber("0")
            R.id.btn_1 -> inputNumber("1")
            R.id.btn_2 -> inputNumber("2")
            R.id.btn_3 -> inputNumber("3")
            R.id.btn_4 -> inputNumber("4")
            R.id.btn_5 -> inputNumber("5")
            R.id.btn_6 -> inputNumber("6")
            R.id.btn_7 -> inputNumber("7")
            R.id.btn_8 -> inputNumber("8")
            R.id.btn_9 -> inputNumber("9")
            R.id.btn_10 -> inputNumber("c")//clear
            R.id.btn_11 -> inputNumber("/")//divide
            R.id.btn_12 -> inputNumber("+")//plus
            R.id.btn_13 -> inputNumber("-")//minus
            R.id.btn_14 -> inputNumber("*")//times
            R.id.btn_15 -> inputNumber("=")//equals
            R.id.btn_16 -> inputNumber("%")//modulus
        }
    }
}