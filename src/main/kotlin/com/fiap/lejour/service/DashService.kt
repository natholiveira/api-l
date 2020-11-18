package com.fiap.lejour.service

import com.fiap.lejour.dto.*

interface DashService {

    fun getFaixaInvestimento(mes: MesEnum): FaixaInvestimento
    fun getConversaoVenda(): ConversaoVendaList
    fun getTagsPesquisadas(): TopList
    fun getFornecedores(): TopList
    fun getServicosMaisContratados(): TopList
    fun getEntradaCaixa(): GraficoEntradaSaida
    fun getCasamento(): GraficoEntradaSaida
}