package com.fiap.lejour.service.impl

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fiap.lejour.dto.*
import com.fiap.lejour.service.LejourService
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class LejourServiceImpl : LejourService {
    val url = "https://sheet2api.com/v1/ByR2h1huRjyQ/fiap"
    override fun getCasamentoList(): List<CasamentoDTO> {

        val restTemplate = RestTemplate()

        val resultJson = restTemplate.getForObject("$url/wedding", String::class.java)

        var casamentoList = getMapper().readValue(resultJson, object : TypeReference<List<CasamentoJSONDTO?>?>() {})

        casamentoList = casamentoList?.filter { casamentoDTO -> !casamentoDTO?.WEDDING_DATE.equals("NULL")  && !casamentoDTO?.BUDGET.equals("NULL")}

        val casamentos =  arrayListOf<CasamentoDTO>()

        casamentoList?.forEach { casamentoJson ->
            val casamento = casamentoJson?.toCasamento()

            casamentos.add(casamento!!)
        }

        return casamentos
    }

    override fun getUsuarioList(): List<User> {
        val restTemplate = RestTemplate()

        val resultJson = restTemplate.getForObject("$url/user?limit=2000", String::class.java)

        var userList = getMapper().readValue(resultJson, object : TypeReference<List<UserJSONDTO?>?>() {})

        val users =  arrayListOf<User>()

        userList?.forEach { userJson ->
            val user = userJson?.toUser()

            users.add(user!!)
        }

        return users
    }

    override fun getAgendamentoList(): List<AgendamentoDTO> {
        val restTemplate = RestTemplate()

        val resultJson = restTemplate.getForObject("$url/appointment?limit=2000", String::class.java)

        var agendamentoList = getMapper().readValue(resultJson, object : TypeReference<List<AgendamentoJSONDTO?>?>() {})

        val agendamentos =  arrayListOf<AgendamentoDTO>()

        agendamentoList?.forEach { agendamentoJSON ->
            val agendamento = agendamentoJSON?.toAgendamento()

            agendamentos.add(agendamento!!)
        }

        return agendamentos
    }

    override fun getVendaList(): List<VendaDTO> {
        val restTemplate = RestTemplate()

        val resultJson = restTemplate.getForObject("$url/invoice?limit=2000", String::class.java)

        var vendaList = getMapper().readValue(resultJson, object : TypeReference<List<VendaJSONDTO?>?>() {})

        val vendas =  arrayListOf<VendaDTO>()

        vendaList?.forEach { vendaJSON ->
            val venda = vendaJSON?.toVenda()

            vendas.add(venda!!)
        }

        return vendas
    }

    private fun getMapper() = jacksonObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
}