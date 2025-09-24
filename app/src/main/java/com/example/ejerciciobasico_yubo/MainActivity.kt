package com.example.ejerciciobasico_yubo

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ejerciciobasico_yubo.databinding.ActivityMainBinding
import kotlin.random.Random
class MainActivity : AppCompatActivity() {
    private lateinit var  mibinding: ActivityMainBinding

    val numInt = 5


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mibinding = ActivityMainBinding.inflate(layoutInflater)


        enableEdgeToEdge()
        setContentView(mibinding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        main()
        botonConfiguracion()
    }


    private fun botonConfiguracion(){
         mibinding.button.setOnClickListener {

        }

    }



    fun main() {

        var numInt = 5
        var numMax = 10
        var numOculto = 0




        while (true) {
            println("===== Menú del Juego =====")
            println("1. Configurar")
            println("2. Jugar")
            println("3. Salir")
            print("Selecciona una opción: ")


            val opcion = readLine()?.toIntOrNull()
            if (opcion == null) {
                println("Entrada inválida, introduce un número (1, 2 o 3)")
                continue
            }


            when (opcion) {
                1 -> {

                    println("--- Configuración ---")

                    println("Introduce el número de intentos permitidos )=:")
                    val nuevoNumInt = readLine()?.toIntOrNull()
                    if (nuevoNumInt != null && nuevoNumInt > 0){
                        numInt = nuevoNumInt
                        println("Has cambiado número de intntos permididos a: $numInt")
                    } else{
                        println("Entrada inválida")
                    }


                    print("Introduce el número máximo generado : ")
                    val nuevoNumMax = readLine()?.toIntOrNull()
                    if (nuevoNumMax != null && nuevoNumMax > 0) {
                        numMax = nuevoNumMax
                        println("Has cambiado generado a: $numMax")
                    } else {
                        println("Entrada inválida")
                    }
                }

                2 -> {

                    numOculto = Random.nextInt(numMax + 1)
                    var intentos = 0
                    var haGanado = false

                    println("\n--- Juego Iniciado ---")
                    println("Adivina el número oculto entre 0 y $numMax. Tienes $numInt intentos.")


                    while (intentos < numInt && !haGanado) {
                        intentos++
                        print("Intento $intentos: Introduce tu número: ")
                        val adivinanza = readLine()?.toIntOrNull()

                        if (adivinanza == null) {
                            println("Entrada inválida. Por favor, introduce un número entero.")
                            intentos--
                            continue
                        }


                        when {
                            adivinanza == numOculto -> {
                                println("Has ganado!. Has necesitado $intentos intentos")
                                haGanado = true
                            }
                            adivinanza < numOculto -> {
                                println("El número oculto es menor")
                            }
                            else -> {
                                println("El número oculto es mayor")
                            }
                        }
                    }


                    if (!haGanado) {
                        println("Perdiste!. Intentos consumidos")
                    }

                    println("")
                    println("")
                }

                3 -> {
                    println("Programa finalizado.")
                    return
                }

                else -> {
                    println("Opción no válida. Por favor, selecciona 1, 2 o 3.")
                }
            }
        }
    }
}