package br.com.alura.ceep.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import br.com.alura.ceep.R
import br.com.alura.ceep.dao.NotaDAO
import br.com.alura.ceep.model.Nota
import br.com.alura.ceep.ui.activity.ConstantesActivities.Companion.CHAVE_NOTA
import br.com.alura.ceep.ui.activity.ConstantesActivities.Companion.CHAVE_POSICAO
import br.com.alura.ceep.ui.activity.ConstantesActivities.Companion.CODIGO_REQUISICAO_ALTERA_NOTA
import br.com.alura.ceep.ui.activity.ConstantesActivities.Companion.CODIGO_REQUISICAO_INSERE_NOTA
import br.com.alura.ceep.ui.activity.ConstantesActivities.Companion.POSICAO_INVALIDA
import br.com.alura.ceep.ui.activity.ConstantesActivities.Companion.TITULO_APPBAR
import br.com.alura.ceep.ui.recyclerview.adapter.ListaNotasAdapter
import br.com.alura.ceep.ui.recyclerview.helper.callback.NotaItemTouchHelperCallback
import kotlinx.android.synthetic.main.activity_lista_notas.*

class ListaNotasActivity : AppCompatActivity() {

    private val dao = NotaDAO()
    private lateinit var adapter: ListaNotasAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_notas)

        title = TITULO_APPBAR
        val todasNotas = pegaTodasNotas()
        configuraRecyclerView(todasNotas)
        botaoInsereNota()
    }

    private fun botaoInsereNota() {
        lista_notas_insere_nota.setOnClickListener {
            vaiParaFormularioNotaActivityInsere()
        }
    }

    private fun vaiParaFormularioNotaActivityInsere() {
        val iniciaFormularioNota = Intent(this, FormularioNotaActivity::class.java)
        startActivityForResult(iniciaFormularioNota, CODIGO_REQUISICAO_INSERE_NOTA)
    }

    private fun pegaTodasNotas(): List<Nota> {
        return dao.notas
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (ehResultadoInsereNota(requestCode, data)) {
            if (resultadoOk(resultCode)) {
                val notaRecebida = data!!.getParcelableExtra<Nota>(CHAVE_NOTA)!!
                dao.insere(notaRecebida)
            }
        }

        if (ehResultadoAlteraNota(requestCode, data)) {
            if (resultadoOk(resultCode)) {
                val notaRecebida = data!!.getParcelableExtra<Nota>(CHAVE_NOTA)
                val posicaoRecebida = data.getIntExtra(CHAVE_POSICAO, POSICAO_INVALIDA)
                if (ehPosicaoValida(posicaoRecebida)) {
                    altera(notaRecebida, posicaoRecebida)
                } //else {
//                    Toast.makeText(
//                        this,
//                        "Ocorreu um problema na alteração da nota",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }
            }
        }
    }

    private fun altera(nota: Nota?, posicao: Int) {
        dao.altera(nota!!, posicao)
        adapter.notifyDataSetChanged()
    }

    private fun ehPosicaoValida(posicao: Int) = posicao > POSICAO_INVALIDA

    private fun ehResultadoAlteraNota(
        requestCode: Int,
        data: Intent?
    ) =
        ehCodigoRequisicaoAlteraNota(requestCode) && temNota(data)

    private fun ehCodigoRequisicaoAlteraNota(requestCode: Int) =
        requestCode == CODIGO_REQUISICAO_ALTERA_NOTA

    private fun ehResultadoInsereNota(
        requestCode: Int,
        data: Intent?
    ) =
        ehCodigoRequisicaoInsereNota(requestCode) && temNota(
            data
        )

    private fun temNota(data: Intent?) = data != null && data.hasExtra(CHAVE_NOTA)

    private fun resultadoOk(resultCode: Int) =
        resultCode == Activity.RESULT_OK

    private fun ehCodigoRequisicaoInsereNota(requestCode: Int) =
        requestCode == CODIGO_REQUISICAO_INSERE_NOTA

    override fun onResume() {
        super.onResume()
    }

    private fun configuraRecyclerView(notas: List<Nota>) {
        configuraAdapter(notas)
        configuraItemTouchHelper()
    }

    private fun configuraItemTouchHelper() {
        val itemTouchHelper = ItemTouchHelper(NotaItemTouchHelperCallback(adapter))
        itemTouchHelper.attachToRecyclerView(lista_notas_recyclerview)
    }

    private fun configuraAdapter(notas: List<Nota>) {
        adapter = ListaNotasAdapter(this, notas) { nota, posicao ->
            vaiParaFormularioNotaAcativityAltera(nota, posicao)
        }
        lista_notas_recyclerview.adapter = adapter
    }

    private fun vaiParaFormularioNotaAcativityAltera(
        nota: Nota,
        posicao: Int
    ) {
        val abreFormularioComNota = Intent(this, FormularioNotaActivity::class.java)
        abreFormularioComNota.putExtra(CHAVE_NOTA, nota)
        abreFormularioComNota.putExtra(CHAVE_POSICAO, posicao)
        startActivityForResult(abreFormularioComNota, CODIGO_REQUISICAO_ALTERA_NOTA)
    }
}