package com.fiap.lejour.dto

data class FaixaInvestimento(
        val total: Int,
        val mes: String,
        val faixas: List<Faixas>

)

data class Faixas(
        val titulo: String,
        val total: Int
)