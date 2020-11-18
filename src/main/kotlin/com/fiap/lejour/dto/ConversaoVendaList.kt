package com.fiap.lejour.dto

data class ConversaoVendaList (
    val mes: List<ConversaoVendas>,
    val total: Double = 1510814.0
)

data class ConversaoVendas(
        val titulo: String,
        val entrou: Int,
        val orcou: Int,
        val agendou: Int,
        val contratou: Int,
        val total: Double
)