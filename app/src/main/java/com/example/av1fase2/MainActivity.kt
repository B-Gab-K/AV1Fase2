package com.example.av1fase2

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.DatePicker
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val spinner = findViewById<Spinner>(R.id.campus)
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.spinner_options, android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.setAdapter(adapter)

        val datePicker = findViewById<DatePicker>(R.id.pegarData)
        val buttonDatePicker = findViewById<Button>(R.id.btnData)
        buttonDatePicker.setOnClickListener {
            showDatePickerDialog()
        }

    }

    @SuppressLint("SetTextI18n")
    private fun showDatePickerDialog() {
        val calendario = Calendar.getInstance()
        val ano = calendario.get(Calendar.YEAR)
        val mes = calendario.get(Calendar.MONTH)
        val dia = calendario.get(Calendar.DAY_OF_MONTH)
        val dataSelecionada = findViewById<TextView>(R.id.dataSelecionada)

        val datePickerDialog = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDayOfMonth ->
                val data = "${selectedDayOfMonth}/${selectedMonth + 1}/${selectedYear}"
                dataSelecionada.text = "Data Selecionada: ${data}"
            },
            ano,
            mes,
            dia
        )

        datePickerDialog.show()
    }

}
