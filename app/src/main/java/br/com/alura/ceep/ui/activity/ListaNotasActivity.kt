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
        val todasNotas = notasDeExemplo()
        configuraRecyclerView(todasNotas)
    }

    private fun notasDeExemplo(): List<Nota> {
        val dao = NotaDAO()
        dao.insere(Nota("Primeira notas", "Descrição pequena"), Nota("Segunda nota", "Segunda descrição é bem maior que a da primeira nota"))
        return dao.notas
    }

    private fun configuraRecyclerView(notas: List<Nota>) {
        configuraAdapter(notas)
//        configuraLayoutManager()
        //foi colocado diretamente no xml
    }

//    private fun configuraLayoutManager() {
//        val layoutManager = LinearLayoutManager(this)
//        lista_notas_recyclerview.layoutManager = layoutManager
//    }

    private fun configuraAdapter(notas: List<Nota>) {
        lista_notas_recyclerview.adapter = ListaNotasAdapter(this, notas)
    }
}