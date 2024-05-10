package com.example.av1fase2

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var nomeEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var telefoneEditText: EditText
    private lateinit var campusSpinner: Spinner
    private lateinit var generoM: RadioButton
    private lateinit var generoF: RadioButton

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

        val listViewAlunos = findViewById<ListView>(R.id.listViewAlunos)
        val databaseHelper = DatabaseHelper(this)
        val alunosList = databaseHelper.getAllAlunos()
        val adapterAluno = AlunoAdapter(this, alunosList)
        listViewAlunos.adapter = adapterAluno

        nomeEditText = findViewById(R.id.nome)
        emailEditText = findViewById(R.id.email)
        telefoneEditText = findViewById(R.id.telefone)
        campusSpinner = spinner
        generoM = findViewById(R.id.generoM)
        generoF = findViewById(R.id.generoF)

        val btnInserir = findViewById<Button>(R.id.btnInserir)
        btnInserir.setOnClickListener {
            val nome = nomeEditText.text.toString()
            val email = emailEditText.text.toString()
            val telefone = telefoneEditText.text.toString()
            val campus = campusSpinner.selectedItem.toString()
            val genero = if (generoM.isChecked) "Masculino" else "Feminino"

            val aluno = Aluno(nome, email, telefone, campus, genero)

            val alunoId = databaseHelper.addAluno(aluno)
            if (alunoId != -1) {
                Toast.makeText(this, "Aluno inserido com sucesso!", Toast.LENGTH_SHORT).show()

                alunosList.add(aluno)

                nomeEditText.text.clear()
                emailEditText.text.clear()
                telefoneEditText.text.clear()
                campusSpinner.setSelection(0)
                generoM.isChecked = false
                generoF.isChecked = false
            } else {
                Toast.makeText(this, "Erro ao inserir aluno!", Toast.LENGTH_SHORT).show()
            }

        }
    }
}
