package com.fiap.lejour.service

import com.fiap.lejour.dto.*

interface DashService {

    fun getFaixaInvestimento(mes: MesEnum): FaixaInvestimento
    fun getConversaoVenda(): ConversaoVendaList
    fun getTagsPesquisadas(servico: Servico?): TopList
    fun getFornecedores(servico: Servico?): TopList
    fun getServicosMaisContratados(): TopList
    fun getEntradaCaixa(servico: Servico?): GraficoEntradaSaida
    fun getCasamento(): GraficoEntradaSaida
}