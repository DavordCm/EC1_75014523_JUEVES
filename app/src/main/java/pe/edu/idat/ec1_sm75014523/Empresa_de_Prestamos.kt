package pe.edu.idat.ec1_sm75014523

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import pe.edu.idat.ec1_sm75014523.databinding.ActivityEmpresaDePrestamosBinding

class Empresa_de_Prestamos : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityEmpresaDePrestamosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmpresaDePrestamosBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnCalcularCuotas.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val prestamo = binding.etPrestamo.text.toString().toDoubleOrNull()

        if (prestamo != null) {
            val (cuotas, valorCuota) = calcularCuotas(prestamo)
            binding.txtCuotas.text = "Número de cuotas: $cuotas"
            binding.txtValorCuota.text = "Valor de cada cuota: %.2f".format(valorCuota)
        } else {
            binding.txtCuotas.text = "Por favor, ingrese un monto válido."
            binding.txtValorCuota.text = ""
        }
    }

    // Función para calcular cuotas e interés
    private fun calcularCuotas(prestamo: Double): Pair<Int, Double> {
        val cuotas = when {
            prestamo > 5000 -> 3
            prestamo < 1000 -> 1
            prestamo in 2000.0..3000.0 -> 2
            else -> 5
        }

        val interes = if (prestamo < 4000) 0.12 else 0.10
        val totalConInteres = prestamo * (1 + interes)
        val valorCuota = totalConInteres / cuotas

        return Pair(cuotas, valorCuota)
    }
}
