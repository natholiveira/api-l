package com.fiap.lejour.dto

data class DashDTO (
        val conversaoVenda: ConversaoVendaList,
        val faixaInvestimento: FaixaInvestimento,
        val topList: TopList,
        val fornecedores: TopList,
        val servicosMaisContratados: TopList,
        val entradaCaixa: GraficoEntradaSaida,
        val casamento: GraficoEntradaSaida
)