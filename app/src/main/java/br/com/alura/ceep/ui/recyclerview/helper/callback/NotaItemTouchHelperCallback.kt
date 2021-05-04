package br.com.alura.ceep.ui.recyclerview.helper.callback

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import br.com.alura.ceep.dao.NotaDAO
import br.com.alura.ceep.ui.recyclerview.adapter.ListaNotasAdapter

class NotaItemTouchHelperCallback(private val adapter: ListaNotasAdapter) : ItemTouchHelper.Callback() {

    private val dao: NotaDAO = NotaDAO()

    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        val marcacoesDeDeslize = ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT
        val marcacoesDeArrastar = ItemTouchHelper.DOWN or ItemTouchHelper.UP or ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT
        return makeMovementFlags(marcacoesDeArrastar, marcacoesDeDeslize)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        val posicaoInicial = viewHolder.adapterPosition
        val posicaoFinal = target.adapterPosition
        dao.troca(posicaoInicial, posicaoFinal)
        adapter.notifyDataSetChanged()
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val posicaoNotaDeslizada = viewHolder.adapterPosition
        dao.remove(posicaoNotaDeslizada)
        adapter.notifyDataSetChanged()
    }

}
