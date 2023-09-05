package com.example.appimc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import java.math.RoundingMode
import java.text.DecimalFormat
import kotlin.math.roundToInt

class ResultadoActivity : AppCompatActivity() {
    private lateinit var botonReCalcula:Button
    private lateinit var estado:TextView
    private lateinit var imc:TextView
    private lateinit var composicionCorporal:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultado)
        iniciandoComponentes()
        iniciandoListener()
    }
    private fun iniciandoComponentes(){
        botonReCalcula = findViewById(R.id.buttonRecalcular)
        estado = findViewById(R.id.tvResultadoFinalEstado)
        imc = findViewById(R.id.tvResultadoFinalIMC)
        composicionCorporal =  findViewById(R.id.tvResultadoFinalComposicionCorporal)
        //aca agarramos los datos de la anterior activity
        val pesoS= intent.getStringExtra("peso")
        val alturaS = intent.getStringExtra("altura")
        val peso:Int = (pesoS.toString()).toInt()
        val altura:Int = alturaS.toString().toInt()
        val imcD:Double = calcularIMC(peso,altura)
        val compoCorpo:String = composicionCorpo(imcD)
        val compoNor:String = composicionNormal(imcD)

        //Le damos valores a la variables
        estado.text = compoNor
        imc.text = redondeo(imcD).toString()
        composicionCorporal.text = compoCorpo
    }
    private fun iniciandoListener(){
        botonReCalcula.setOnClickListener {
            val ventanaAtras = Intent(this,MainActivity::class.java)
            startActivity(ventanaAtras)
        }
    }
    private fun calcularIMC(peso:Int, altura:Int):Double {
        var alturaCm:Double = altura/100.0
        return (peso/(Math.pow(alturaCm,2.0)))
    }
    private fun composicionCorpo(imc:Double):String{
        when(imc){
            in 0.0..18.4999999999999 -> return "Peso inferior al normal"
            in 18.5..24.999999999999 -> return "Peso Normal"
            in 25.0..29.999999999999 -> return "Peso superior al normal"
            in 30.0..99.9-> return "Peligro Obesidad"
            else -> return " "
        }
    }
    private fun composicionNormal(imc:Double):String{
        when(imc){
            in 0.0..18.4999999999999 -> return "Delgado"
            in 18.5..24.999999999999 -> return "Normal"
            in 25.0..29.999999999999 -> return "Pre-Obeso "
            in 30.0..99.9-> return "Obesidad"
            else -> return " "
        }
    }
    private fun redondeo(a:Double):Double {
        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.DOWN
        val roundoff = df.format(a)
        return roundoff.toDouble()
    }
}