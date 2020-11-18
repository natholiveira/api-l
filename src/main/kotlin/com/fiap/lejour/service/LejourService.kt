package com.fiap.lejour.service

import com.fiap.lejour.dto.AgendamentoDTO
import com.fiap.lejour.dto.CasamentoDTO
import com.fiap.lejour.dto.User
import com.fiap.lejour.dto.VendaDTO

interface LejourService {
    fun getCasamentoList(): List<CasamentoDTO>
    fun getUsuarioList(): List<User>
    fun getAgendamentoList(): List<AgendamentoDTO>
    fun getVendaList(): List<VendaDTO>
}