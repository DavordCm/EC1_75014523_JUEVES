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

class Descuento_en_Almacen : AppCompatActivity() {

    private lateinit var etCantidad: EditText
    private lateinit var etPrecio: EditText
    private lateinit var btnCalcularDescuento: Button
    private lateinit var tvResultado: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_descuento_en_almacen)

        // Referencias a los elementos de la interfaz
        etCantidad = findViewById(R.id.etCantidad)
        etPrecio = findViewById(R.id.etPrecio)
        btnCalcularDescuento = findViewById(R.id.btnCalcularDescuento)
        tvResultado = findViewById(R.id.tvResultado)

        // Configurar el listener del botón
        btnCalcularDescuento.setOnClickListener {
            calcularDescuento()
        }

        // Ajustes para el padding
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun calcularDescuento() {
        val cantidad = etCantidad.text.toString().toIntOrNull()
        val precio = etPrecio.text.toString().toDoubleOrNull()

        if (cantidad != null && precio != null) {
            val total = cantidad * precio
            val descuento = if (total > 200) total * 0.20 else 0.0
            val totalConDescuento = total - descuento

            tvResultado.text = "Total a pagar: S/. ${"%.2f".format(totalConDescuento)}"
        } else {
            tvResultado.text = "Por favor, ingrese valores válidos."
        }
    }
}
