package br.com.alura.ceep.ui.recyclerview.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.alura.ceep.R
import br.com.alura.ceep.model.Nota
import kotlinx.android.synthetic.main.item_nota.view.*

class ListaNotasAdapter(private val context: Context, private val notas: List<Nota>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private var quantidadeViewCriada = 0
        private var quantidadeBindView = 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        quantidadeViewCriada++
        val viewCriada = LayoutInflater.from(context).inflate(R.layout.item_nota, parent, false)
        Log.i("recyclerView adapter", "viewHolder criado: $quantidadeViewCriada")
        return NotaViewHolder(viewCriada)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        quantidadeBindView++
        val nota = notas[position]
        holder.itemView.item_nota_titulo.text = nota.titulo
        holder.itemView.item_nota_descricao.text = nota.descricao
        Log.i("recyclerView adapter", "bind: posição $position quantidade $quantidadeBindView")
    }

    override fun getItemCount(): Int {
        return notas.size
    }

    class NotaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}