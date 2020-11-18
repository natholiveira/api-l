package com.fiap.lejour.dto

data class DashDTO (
        val categorias: List<Categoria>,
        val conversaoVenda: ConversaoVendaList,
        val faixaInvestimento: FaixaInvestimento,
        val tagsPesquisadas: TopList,
        val fornecedores: TopList,
        val servicosMaisContratados: TopList,
        val entradaCaixa: GraficoEntradaSaida,
        val casamento: GraficoEntradaSaida
)

data class Categoria(
        val servico: Servico,
        val nome: String
)