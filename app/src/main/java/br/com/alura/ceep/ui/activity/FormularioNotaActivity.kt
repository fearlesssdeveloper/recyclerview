package br.com.alura.ceep.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import br.com.alura.ceep.R
import br.com.alura.ceep.model.Nota
import br.com.alura.ceep.ui.activity.ConstantesActivities.Companion.CHAVE_NOTA
import br.com.alura.ceep.ui.activity.ConstantesActivities.Companion.CHAVE_POSICAO
import br.com.alura.ceep.ui.activity.ConstantesActivities.Companion.POSICAO_INVALIDA
import br.com.alura.ceep.ui.activity.ConstantesActivities.Companion.TITULO_APPBAR_ALTERA
import br.com.alura.ceep.ui.activity.ConstantesActivities.Companion.TITULO_APPBAR_INSERE
import kotlinx.android.synthetic.main.activity_formulario_nota.*

class FormularioNotaActivity : AppCompatActivity() {

    private var posicaoRecebida = POSICAO_INVALIDA

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario_nota)

        title = TITULO_APPBAR_INSERE

        val dadosRecebidos = intent
        if (dadosRecebidos.hasExtra(CHAVE_NOTA)) {
            title = TITULO_APPBAR_ALTERA
            val notaRecebida = dadosRecebidos.getParcelableExtra<Nota>(CHAVE_NOTA)
            posicaoRecebida = dadosRecebidos.getIntExtra(CHAVE_POSICAO, POSICAO_INVALIDA)
            preencheCampos(notaRecebida)
        }
    }

    private fun preencheCampos(nota: Nota?) {
        nota?.let {
            formulario_nota_titulo.setText(it.titulo)
            formulario_nota_descricao.setText(it.descricao)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_formulario_nota_salva, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_formulario_nota_ic_salva -> {
                val notaCriada = criaNota()
                retornaNota(notaCriada)
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun retornaNota(nota: Nota) {
        val resultadoInsercao = Intent()
        resultadoInsercao.putExtra(CHAVE_NOTA, nota)
        resultadoInsercao.putExtra(CHAVE_POSICAO, posicaoRecebida)
        setResult(Activity.RESULT_OK, resultadoInsercao)
    }

    private fun criaNota(): Nota {
        val titulo = formulario_nota_titulo.text.toString()
        val descricao = formulario_nota_descricao.text.toString()
        return Nota(titulo, descricao)
    }
}