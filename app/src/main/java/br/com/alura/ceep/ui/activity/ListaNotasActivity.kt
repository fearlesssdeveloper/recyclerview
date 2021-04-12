package br.com.alura.ceep.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.alura.ceep.R
import br.com.alura.ceep.dao.NotaDAO
import br.com.alura.ceep.model.Nota
import br.com.alura.ceep.ui.recyclerview.adapter.ListaNotasAdapter
import kotlinx.android.synthetic.main.activity_lista_notas.*

class ListaNotasActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_notas)


        val dao = NotaDAO()
        for (i in 1..10000) {
            dao.insere(Nota("Título $i", "Descrição $i"))
        }
        val todasNotas = dao.notas
        lista_notas_recyclerview.adapter = ListaNotasAdapter(this, todasNotas)
        val layoutManager = LinearLayoutManager(this)
        lista_notas_recyclerview.layoutManager = layoutManager
    }
}