package com.example.appimc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    //creamos 2 variables de tipo boolena para mas adelante de nuestro codigo
    private var isMaleSelected:Boolean = true
    private var isFemaleSelected:Boolean = false
    //creamos el acceso a los cards
    //con el lateinit le decimos (no te inicies ahora te iniciaras cuando yo te diga)
    private lateinit var viewMale:CardView
    private lateinit var viewFemale:CardView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //iniciaremos los compomentes
        initComponents()
        //crearemos un metodo para cuando pulsemos el boton de hombre o mujer estos son considerados metodos de escucha
        initListeners()
        //creamos la interfaz
        initUI()
    }

    private fun initUI() {
        //le metemos el metodo setGenderColor por que
        setGenderColor()
    }

    private fun initComponents(){
        viewMale = findViewById(R.id.viewMale)
        viewFemale = findViewById(R.id.viewFemale)
    }
    private fun initListeners(){
        //este metodo solo les colocara un escucha de click ya sea en hombre o mujer depende de cual se cliceee
        viewMale.setOnClickListener{
            changeGender()
            setGenderColor()
        }
        viewFemale.setOnClickListener {
            changeGender()
            setGenderColor()
        }
    }
    private fun changeGender(){
        isMaleSelected = !isMaleSelected //false
        isFemaleSelected = !isFemaleSelected // true
    }
    private fun setGenderColor(){
        viewMale.setCardBackgroundColor(getBackgroundColor(isMaleSelected))
        viewFemale.setBackgroundColor(getBackgroundColor(isFemaleSelected))
    }
    //esta funcion devolvera un int por que el contextCompat es un int
    private fun getBackgroundColor(isSelectedComponent:Boolean):Int{
        val colorReferences = if(isSelectedComponent){
            R.color.background_component_selected
        }else{
            R.color.background_component
        }
        //para agarrar un color debemos hacerlo en el Context Compat Color
        return  ContextCompat.getColor(this,colorReferences)
    }
}