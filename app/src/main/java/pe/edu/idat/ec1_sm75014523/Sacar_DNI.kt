package pe.edu.idat.ec1_sm75014523

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Calendar

class Sacar_DNI : AppCompatActivity() {
    private lateinit var etAnioNacimiento: EditText
    private lateinit var btnComprobar: Button
    private lateinit var tvResultado: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sacar_dni)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        etAnioNacimiento = findViewById(R.id.et_anio_nacimiento)
        btnComprobar = findViewById(R.id.btn_comprobar)
        tvResultado = findViewById(R.id.tv_resultado)

        btnComprobar.setOnClickListener {
            comprobarDNI()
        }
    }

    private fun comprobarDNI() {
        val anioNacimientoStr = etAnioNacimiento.text.toString()
        val anioNacimiento = anioNacimientoStr.toIntOrNull()

        val edad = anioActual - anioNacimiento!!

        if (anioNacimiento != null) {
            val anioActual = Calendar.getInstance().get(Calendar.YEAR)
            val mensaje = if (edad >= 18) {
                "Debes sacar tu DNI."
            } else {
                "No es necesario sacar tu DNI."
            }
            tvResultado.text = mensaje
        } else {
            tvResultado.text = "Por favor, ingresa un año válido."
        }
    }
}
