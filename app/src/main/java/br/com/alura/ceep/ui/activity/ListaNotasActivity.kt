package br.com.alura.ceep.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.alura.ceep.R
import br.com.alura.ceep.dao.NotaDAO
import br.com.alura.ceep.model.Nota
import br.com.alura.ceep.ui.activity.ConstantesActivities.Companion.CHAVE_NOTA
import br.com.alura.ceep.ui.activity.ConstantesActivities.Companion.CODIGO_REQUISICAO_INSERE_NOTA
import br.com.alura.ceep.ui.activity.ConstantesActivities.Companion.CODIGO_RESULTADO_NOTA_CRIADA
import br.com.alura.ceep.ui.recyclerview.adapter.ListaNotasAdapter
import kotlinx.android.synthetic.main.activity_lista_notas.*

class ListaNotasActivity : AppCompatActivity() {

    private val dao = NotaDAO()
    private lateinit var adapter: ListaNotasAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_notas)
        val todasNotas = pegaTodasNotas()
        configuraRecyclerView(todasNotas)
        botaoInsereNota()
    }

    private fun botaoInsereNota() {
        lista_notas_insere_nota.setOnClickListener {
            vaiParaFormularioNotaActivity()
        }
    }

    private fun vaiParaFormularioNotaActivity() {
        val iniciaFormularioNota = Intent(this, FormularioNotaActivity::class.java)
        startActivityForResult(iniciaFormularioNota, CODIGO_REQUISICAO_INSERE_NOTA)
    }

    private fun pegaTodasNotas(): List<Nota> {
        for (i in 1..10) {
            dao.insere(Nota("Titulo $i", "Descrição $i"))
        }
        return dao.notas
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (ehResultadoComNota(requestCode, resultCode, data)) {
            val notaRecebida = data!!.getParcelableExtra<Nota>(CHAVE_NOTA)!!
            dao.insere(notaRecebida)
        }

        if (requestCode == 2 && resultCode == CODIGO_RESULTADO_NOTA_CRIADA && temNota(data) && data!!.hasExtra("posicao")) {
            val notaRecebida = data!!.getParcelableExtra<Nota>(CHAVE_NOTA)
            val posicaoRecebida = data.getIntExtra("posicao", -1)
            dao.altera(notaRecebida!!, posicaoRecebida)
            adapter.notifyDataSetChanged()
        }
    }

    private fun ehResultadoComNota(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) =
        heCodigoRequisicaoInsereNota(requestCode) && heCodigoResultadoNotaCriada(resultCode) && temNota(
            data
        )

    private fun temNota(data: Intent?) = data!!.hasExtra(CHAVE_NOTA)

    private fun heCodigoResultadoNotaCriada(resultCode: Int) =
        resultCode == CODIGO_RESULTADO_NOTA_CRIADA

    private fun heCodigoRequisicaoInsereNota(requestCode: Int) =
        requestCode == CODIGO_REQUISICAO_INSERE_NOTA

    override fun onResume() {
        super.onResume()
    }

    private fun configuraRecyclerView(notas: List<Nota>) {
        configuraAdapter(notas)
    }

    private fun configuraAdapter(notas: List<Nota>) {
        adapter = ListaNotasAdapter(this, notas) { nota, posicao ->
            val abreFormularioComNota = Intent(this, FormularioNotaActivity::class.java)
            abreFormularioComNota.putExtra(CHAVE_NOTA, nota)
            abreFormularioComNota.putExtra("posicao", posicao)
            startActivityForResult(abreFormularioComNota, 2)
        }
        lista_notas_recyclerview.adapter = adapter
    }
}