package br.com.alura.ceep.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import br.com.alura.ceep.R
import br.com.alura.ceep.model.Nota
import kotlinx.android.synthetic.main.item_nota.view.*

class ListaNotasAdapter(
    private val context: Context,
    private val notas: List<Nota>
) : BaseAdapter(){
    override fun getCount(): Int {
        return notas.size
    }

    override fun getItem(posicao: Int): Any {
        return notas[posicao]
    }

    override fun getItemId(posicao: Int): Long {
        return 0
    }

    override fun getView(posicao: Int, view: View?, parent: ViewGroup?): View {
        val viewCriada = LayoutInflater.from(context).inflate(R.layout.item_nota, parent, false)
        val nota = notas[posicao]
        viewCriada.item_nota_titulo.text = nota.titulo
        viewCriada.item_nota_descricao.text = nota.descricao

        return viewCriada
    }

}