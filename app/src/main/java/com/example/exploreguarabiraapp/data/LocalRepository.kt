package com.example.exploreguarabiraapp.data

import kotlinx.coroutines.flow.Flow

/**
 * Define o contrato para qualquer fonte de dados (Mock ou API).
 *
 * NOTA DE MANUTENIBILIDADE:
 * - Usamos Flow (Streams Reativos) para que a UI possa reagir automaticamente
 * a mudanças na fonte de dados (ex: uma atualização do Firebase/API).
 */
interface LocalRepository {
    /**
     * Retorna um Flow com todas as categorias disponíveis.
     */
    fun getTodasCategorias(): Flow<List<Categoria>>

    /**
     * Retorna um Flow com todos os locais de uma categoria específica.
     */
    fun getLocaisPorCategoria(categoriaId: Int): Flow<List<Local>>

    /**
     * Retorna um Flow para um local específico.
     */
    fun getLocalDetalhes(localId: Int): Flow<Local?>
}