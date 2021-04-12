package br.com.alura.ceep.dao

import br.com.alura.ceep.model.Nota
import java.util.*

class NotaDAO {

    val notas: List<Nota> = Companion.notas

    companion object {
        private val notas: MutableList<Nota> = mutableListOf()
    }

    fun insere(vararg notas: Nota) {
        Companion.notas.addAll(notas)
    }

    fun altera(nota: Nota, posicao: Int) {
        Companion.notas[posicao] = nota
    }

    fun remove(posicao: Int) {
        Companion.notas.removeAt(posicao)
    }

    fun troca(posicaoInicio: Int, posicaoFim: Int) {
        Collections.swap(Companion.notas, posicaoInicio, posicaoFim)
    }

    fun removeTodos(){
        Companion.notas.clear()
    }
}