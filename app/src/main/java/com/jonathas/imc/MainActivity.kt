package com.jonathas.imc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.jonathas.imc.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()


        binding.btncalcular.setOnClickListener{
            val height = binding.height.text.toString()
            val weight = binding.weight.text.toString()
            val result = findViewById<TextView>(R.id.result)

            if(height.isEmpty() || weight.isEmpty()){
                Toast.makeText(this,"Preencha todos os campos", Toast.LENGTH_SHORT).show()
            }else {
                val h = height.toFloat()
                val w = weight.toFloat()
                var res = calculate(h, w)
                result.setText("$res")
                classificationimc(res)
            }
        }

        binding.btnlimpar.setOnClickListener{
            val height = binding.height.text.toString()
            val weight = binding.weight.text.toString()

            if(height.isEmpty() && weight.isEmpty()){
                Toast.makeText(this,"Campo vazio", Toast.LENGTH_SHORT).show()
            }else
                clear()
        }
    }

    fun calculate (h : Float, w : Float) : Float{
        var x = h*h
        return (w/x)
    }

    fun clear (){
        val height = findViewById<EditText>(R.id.height)
        val weight = findViewById<EditText>(R.id.weight)
        val result = findViewById<TextView>(R.id.result)

        height.setText("")
        weight.setText("")
        result.setText("")
    }

    fun classificationimc(res: Float) {
        val result = findViewById<TextView>(R.id.result)
        if(res >= 40){
            result.setText("Obesidade Grau III")
        }else if (res >= 35 && res < 40){
            result.setText("Obesidade Grau II")
        }else if (res >= 30 && res < 35) {
            result.setText("Obesidade Grau I")
        }else if(res >= 25 && res < 30){
            result.setText("Pre-obeso")
        }else if(res >= 18.5 && res < 25) {
            result.setText("Adequado")
        }else if(res >= 17 && res < 18.5){
            result.setText("Magreza Grau I")
        }else if(res >= 16 && res < 17) {
            result.setText("Magreza Grau II")
        }else{
            result.setText("Magreza Grau III")
        }
    }
}