package br.com.alura.ceep.ui.recyclerview.helper.callback

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import br.com.alura.ceep.dao.NotaDAO
import br.com.alura.ceep.ui.recyclerview.adapter.ListaNotasAdapter

class NotaItemTouchHelperCallback(private val adapter: ListaNotasAdapter) : ItemTouchHelper.Callback() {

    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        val marcacoesDeDeslize = ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT
        return makeMovementFlags(0, marcacoesDeDeslize)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val posicaoNotaDeslizada = viewHolder.adapterPosition
        NotaDAO().remove(posicaoNotaDeslizada)
        adapter.notifyDataSetChanged()
    }

}
