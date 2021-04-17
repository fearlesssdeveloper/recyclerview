package br.com.alura.ceep.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.alura.ceep.R
import br.com.alura.ceep.dao.NotaDAO
import br.com.alura.ceep.model.Nota
import br.com.alura.ceep.ui.recyclerview.adapter.ListaNotasAdapter
import kotlinx.android.synthetic.main.activity_lista_notas.*

class ListaNotasActivity : AppCompatActivity() {

    private val dao = NotaDAO()
    private lateinit var todasNotas: List<Nota>
    private lateinit var adapter: ListaNotasAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_notas)
        todasNotas = notasDeExemplo()
        configuraRecyclerView(todasNotas)

        lista_notas_insere_nota.setOnClickListener {
            val iniciaFormularioNota = Intent(this, FormularioNotaActivity::class.java)
            startActivity(iniciaFormularioNota)
        }
    }

//    override fun onResume() {
//        todasNotas = emptyList()
//        todasNotas = dao.notas
//        adapter.notifyDataSetChanged()
//        super.onResume()
//
//    }

    private fun notasDeExemplo(): List<Nota> {
        dao.insere(Nota("Primeira notas", "Descrição pequena"), Nota("Segunda nota", "Segunda descrição é bem maior que a da primeira nota"))
        return dao.notas
    }

    private fun configuraRecyclerView(notas: List<Nota>) {
        configuraAdapter(notas)
    }

    private fun configuraAdapter(notas: List<Nota>) {
        adapter = ListaNotasAdapter(this, notas)
        lista_notas_recyclerview.adapter = adapter
    }
}