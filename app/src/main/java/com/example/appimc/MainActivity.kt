package com.example.appimc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {
    //creamos 2 variables de tipo boolena para mas adelante de nuestro codigo
    private var isMaleSelected:Boolean = true
    private var isFemaleSelected:Boolean = false
    //creamos el acceso a los cards
    //con el lateinit le decimos (no te inicies ahora te iniciaras cuando yo te diga)
    private lateinit var viewMale:CardView
    private lateinit var viewFemale:CardView
    //creamos los datos para el range slider
    private lateinit var tvHeigth:TextView
    private lateinit var rsHeigth:RangeSlider
    //iniciamos los compomentes para controlar el peso y la edad
    private lateinit var fabMenosPeso:FloatingActionButton
    private lateinit var fabMasPeso:FloatingActionButton
    private lateinit var tvMasMenosPeso:TextView

    private lateinit var fabMenosEdad:FloatingActionButton
    private lateinit var fabMasEdad:FloatingActionButton
    private lateinit var tvMasMenosEdad:TextView
    var peso:Int = 0
    var edad:Int = 0
    //creando variable para el boton calcular
    private lateinit var botonCalcular:Button
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
        tvHeigth = findViewById(R.id.tvAltura)
        rsHeigth = findViewById(R.id.rsAltura)
        fabMenosPeso =  findViewById(R.id.fabLessWeigth)
        fabMasPeso = findViewById(R.id.fabMoreWeigth)
        fabMenosEdad = findViewById(R.id.fabLessAge)
        fabMasEdad = findViewById(R.id.fabMoreAge)
        tvMasMenosPeso = findViewById(R.id.tvWeigth)
        tvMasMenosEdad = findViewById(R.id.tvAge)
        peso = tvMasMenosPeso.text.toString().toInt()
        edad = tvMasMenosEdad.text.toString().toInt()
        botonCalcular = findViewById(R.id.buttonCalcular)
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
        //Aca colocaremos un escucha apra nuestro range slider
        rsHeigth.addOnChangeListener { _, value, _ ->
            //formatearemos el decimal para que no nos de valores enteros
            val df = DecimalFormat("#.##")
            val result = df.format(value)
            tvHeigth.text = "$result"
        }
        fabMenosPeso.setOnClickListener {
            peso -= 1
            setWeight()
        }
        fabMasPeso.setOnClickListener {
            peso += 1
            setWeight()
        }
        fabMenosEdad.setOnClickListener {
            edad -=1
            tvMasMenosEdad.text = edad.toString()
        }
        fabMasEdad.setOnClickListener {
            edad +=1
            tvMasMenosEdad.text = edad.toString()
        }
        botonCalcular.setOnClickListener {
            val altura:String = tvHeigth.text.toString()
            val peso:String = tvMasMenosPeso.text.toString()
            val ventanaNueva = Intent(this,ResultadoActivity::class.java).apply {
                putExtra("altura",altura)
                putExtra("peso",peso)
            }
            startActivity(ventanaNueva)
        }
    }
    private fun setWeight(){
        tvMasMenosPeso.text = peso.toString()
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