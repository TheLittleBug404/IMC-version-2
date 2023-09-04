package com.example.appimc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class ResultadoActivity : AppCompatActivity() {
    private lateinit var botonReCalcula:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultado)
        iniciandoComponentes()
        iniciandoListener()
    }
    private fun iniciandoComponentes(){
        botonReCalcula = findViewById(R.id.buttonRecalcular)
    }
    private fun iniciandoListener(){
        botonReCalcula.setOnClickListener {
            val ventanaAtras = Intent(this,MainActivity::class.java)
            startActivity(ventanaAtras)
        }
    }
}