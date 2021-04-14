package br.com.alura.ceep.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import br.com.alura.ceep.R
import br.com.alura.ceep.dao.NotaDAO
import br.com.alura.ceep.model.Nota
import kotlinx.android.synthetic.main.activity_formulario_nota.*

class FormularioNotaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario_nota)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_formulario_nota_salva, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_formulario_nota_ic_salva -> {
                val titulo = formulario_nota_titulo.text.toString()
                val descricao = formulario_nota_descricao.text.toString()
                val notaCriada = Nota(titulo, descricao)
                NotaDAO().insere(notaCriada)
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}