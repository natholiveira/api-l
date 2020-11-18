package com.fiap.lejour.service.impl

import com.fiap.lejour.dto.*
import com.fiap.lejour.service.DashService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class DashServiceImpl(
        @Autowired private val lejourServiceImpl: LejourServiceImpl
) : DashService {

    val casamentos = lejourServiceImpl.getCasamentoList()
    val vendas = lejourServiceImpl.getVendaList()

    override fun getFaixaInvestimento(mes: MesEnum): FaixaInvestimento {

        val casamentosMes = casamentos.filter { casamentoDTO -> casamentoDTO.weddingDate.after(mes.dataInicio) && casamentoDTO.weddingDate.before(mes.dataFim) }

        val total = casamentosMes.map { it.budget }.maxOrNull() ?: 0

        val valorFaixas = total/4

        val faixas = arrayListOf<Faixas>()

        for (i in 0..3) {
            val menorValor = valorFaixas*i
            val maiorValor = valorFaixas*(i+1)
            val test = casamentosMes.filter {
                casamentoDTO -> casamentoDTO.budget > menorValor && casamentoDTO.budget <= maiorValor}
            faixas.add(Faixas("$menorValor à $maiorValor", test.size))
        }

        return FaixaInvestimento(casamentosMes.size, "outubro", faixas)
    }

    override fun getConversaoVenda(): ConversaoVendaList {
        val janeiro = ConversaoVendas(titulo = "Jan", entrou = 230, agendou = 220, orcou = 190, contratou = 150, total = 122530.0)
        val fevereiro = ConversaoVendas(titulo = "Fev", entrou = 280, agendou = 250, orcou = 200, contratou = 170, total = 149432.0)
        val marco = ConversaoVendas(titulo = "Mar", entrou = 210, agendou = 184, orcou = 153, contratou = 142, total = 120830.0)
        val abril = ConversaoVendas(titulo = "Abr", entrou = 265, agendou = 244, orcou = 240, contratou = 225, total = 132547.0)
        val jun = ConversaoVendas(titulo = "Jun", entrou = 354, agendou = 347, orcou = 341, contratou = 310, total = 162645.0)
        val julho = ConversaoVendas(titulo = "Jul", entrou = 310, agendou = 302, orcou = 300, contratou = 289, total = 152835.0)
        val agosto = ConversaoVendas(titulo = "Ago", entrou = 280, agendou = 254, orcou = 245, contratou = 241, total = 143704.0)
        val setembro = ConversaoVendas(titulo = "Set", entrou = 334, agendou = 323, orcou = 320, contratou = 315, total = 158645.0)
        val outubro = ConversaoVendas(titulo = "Out", entrou = 315, agendou = 283, orcou = 274, contratou = 269, total = 149978.0)
        val novembro = ConversaoVendas(titulo = "Nov", entrou = 250, agendou = 230, orcou = 210, contratou = 195, total = 135465.0)
        val dezembro = ConversaoVendas(titulo = "Dez", entrou = 0, agendou = 120, orcou = 102, contratou = 90, total = 82203.0)

        return ConversaoVendaList(arrayListOf(janeiro, fevereiro, marco, abril, jun, julho, agosto, setembro, outubro, novembro, dezembro))
    }

    override fun getEntradaCaixa(): GraficoEntradaSaida {
        return GraficoEntradaSaida(entrada = Meses(
                janeiro = Math.round(vendas.filter { vendaDTO -> vendaDTO.data.after(MesEnum.JANEIRO.dataInicio) && vendaDTO.data.before(MesEnum.JANEIRO.dataFim) }.map { it.lucro }.sum()).toInt(),
                fevereiro = Math.round(vendas.filter { vendaDTO -> vendaDTO.data.after(MesEnum.FEVEREIRO.dataInicio) && vendaDTO.data.before(MesEnum.FEVEREIRO.dataFim) }.map { it.lucro }.sum()).toInt(),
                marco = Math.round(vendas.filter { vendaDTO -> vendaDTO.data.after(MesEnum.MARCO.dataInicio) && vendaDTO.data.before(MesEnum.MARCO.dataFim) }.map { it.lucro }.sum()).toInt(),
                abril = Math.round(vendas.filter { vendaDTO -> vendaDTO.data.after(MesEnum.ABRIL.dataInicio) && vendaDTO.data.before(MesEnum.ABRIL.dataFim) }.map { it.lucro }.sum()).toInt(),
                maio = Math.round(vendas.filter { vendaDTO -> vendaDTO.data.after(MesEnum.MAIO.dataInicio) && vendaDTO.data.before(MesEnum.MAIO.dataFim) }.map { it.lucro }.sum()).toInt(),
                junho = Math.round(vendas.filter { vendaDTO -> vendaDTO.data.after(MesEnum.JUNHO.dataInicio) && vendaDTO.data.before(MesEnum.JUNHO.dataFim) }.map { it.lucro }.sum()).toInt(),
                julho = Math.round(vendas.filter { vendaDTO -> vendaDTO.data.after(MesEnum.JULHO.dataInicio) && vendaDTO.data.before(MesEnum.JULHO.dataFim) }.map { it.lucro }.sum()).toInt(),
                agosto = Math.round(vendas.filter { vendaDTO -> vendaDTO.data.after(MesEnum.AGOSTO.dataInicio) && vendaDTO.data.before(MesEnum.AGOSTO.dataFim) }.map { it.lucro }.sum()).toInt(),
                outubro = Math.round(vendas.filter { vendaDTO -> vendaDTO.data.after(MesEnum.OUTUBRO.dataInicio) && vendaDTO.data.before(MesEnum.OUTUBRO.dataFim) }.map { it.lucro }.sum()).toInt(),
                setembro = Math.round(vendas.filter { vendaDTO -> vendaDTO.data.after(MesEnum.SETEMPRO.dataInicio) && vendaDTO.data.before(MesEnum.SETEMPRO.dataFim) }.map { it.lucro }.sum()).toInt(),
                novembro = Math.round(vendas.filter { vendaDTO -> vendaDTO.data.after(MesEnum.NOVEMBRO.dataInicio) && vendaDTO.data.before(MesEnum.NOVEMBRO.dataFim) }.map { it.lucro }.sum()).toInt(),
                dezembro = Math.round(vendas.filter { vendaDTO -> vendaDTO.data.after(MesEnum.DEZEMBRO.dataInicio) && vendaDTO.data.before(MesEnum.DEZEMBRO.dataFim) }.map { it.lucro }.sum()).toInt()
        ),
        saida = Meses(
                janeiro = Math.round(vendas.filter { vendaDTO -> vendaDTO.data.after(MesEnum.JANEIRO.dataInicio) && vendaDTO.data.before(MesEnum.JANEIRO.dataFim) }.map { it.valor }.sum()).toInt(),
                fevereiro = Math.round(vendas.filter { vendaDTO -> vendaDTO.data.after(MesEnum.FEVEREIRO.dataInicio) && vendaDTO.data.before(MesEnum.FEVEREIRO.dataFim) }.map { it.valor }.sum()).toInt(),
                marco = Math.round(vendas.filter { vendaDTO -> vendaDTO.data.after(MesEnum.MARCO.dataInicio) && vendaDTO.data.before(MesEnum.MARCO.dataFim) }.map { it.valor }.sum()).toInt(),
                abril = Math.round(vendas.filter { vendaDTO -> vendaDTO.data.after(MesEnum.ABRIL.dataInicio) && vendaDTO.data.before(MesEnum.ABRIL.dataFim) }.map { it.valor }.sum()).toInt(),
                maio = Math.round(vendas.filter { vendaDTO -> vendaDTO.data.after(MesEnum.MAIO.dataInicio) && vendaDTO.data.before(MesEnum.MAIO.dataFim) }.map { it.valor }.sum()).toInt(),
                junho = Math.round(vendas.filter { vendaDTO -> vendaDTO.data.after(MesEnum.JUNHO.dataInicio) && vendaDTO.data.before(MesEnum.JUNHO.dataFim) }.map { it.valor }.sum()).toInt(),
                julho = Math.round(vendas.filter { vendaDTO -> vendaDTO.data.after(MesEnum.JULHO.dataInicio) && vendaDTO.data.before(MesEnum.JULHO.dataFim) }.map { it.valor }.sum()).toInt(),
                agosto = Math.round(vendas.filter { vendaDTO -> vendaDTO.data.after(MesEnum.AGOSTO.dataInicio) && vendaDTO.data.before(MesEnum.AGOSTO.dataFim) }.map { it.valor }.sum()).toInt(),
                outubro = Math.round(vendas.filter { vendaDTO -> vendaDTO.data.after(MesEnum.OUTUBRO.dataInicio) && vendaDTO.data.before(MesEnum.OUTUBRO.dataFim) }.map { it.valor }.sum()).toInt(),
                setembro = Math.round(vendas.filter { vendaDTO -> vendaDTO.data.after(MesEnum.SETEMPRO.dataInicio) && vendaDTO.data.before(MesEnum.SETEMPRO.dataFim) }.map { it.valor }.sum()).toInt(),
                novembro = Math.round(vendas.filter { vendaDTO -> vendaDTO.data.after(MesEnum.NOVEMBRO.dataInicio) && vendaDTO.data.before(MesEnum.NOVEMBRO.dataFim) }.map { it.valor }.sum()).toInt(),
                dezembro = Math.round(vendas.filter { vendaDTO -> vendaDTO.data.after(MesEnum.DEZEMBRO.dataInicio) && vendaDTO.data.before(MesEnum.DEZEMBRO.dataFim) }.map { it.valor }.sum()).toInt()
        ))
    }

    override fun getCasamento(): GraficoEntradaSaida {
        return GraficoEntradaSaida(entrada = Meses(
                janeiro = vendas.filter { vendaDTO -> vendaDTO.data.after(MesEnum.JANEIRO.dataInicio) && vendaDTO.data.before(MesEnum.JANEIRO.dataFim)  && vendaDTO.aceito.equals("TRUE") }.size,
                fevereiro = vendas.filter { vendaDTO -> vendaDTO.data.after(MesEnum.FEVEREIRO.dataInicio) && vendaDTO.data.before(MesEnum.FEVEREIRO.dataFim) && vendaDTO.aceito.equals("TRUE")  && vendaDTO.aceito.equals("TRUE") }.size,
                marco = vendas.filter { vendaDTO -> vendaDTO.data.after(MesEnum.MARCO.dataInicio) && vendaDTO.data.before(MesEnum.MARCO.dataFim)  && vendaDTO.aceito.equals("TRUE")  && vendaDTO.aceito.equals("TRUE") }.size,
                abril = vendas.filter { vendaDTO -> vendaDTO.data.after(MesEnum.ABRIL.dataInicio) && vendaDTO.data.before(MesEnum.ABRIL.dataFim)  && vendaDTO.aceito.equals("TRUE")  && vendaDTO.aceito.equals("TRUE") }.size,
                maio = vendas.filter { vendaDTO -> vendaDTO.data.after(MesEnum.MAIO.dataInicio) && vendaDTO.data.before(MesEnum.MAIO.dataFim)  && vendaDTO.aceito.equals("TRUE") && vendaDTO.aceito.equals("TRUE") }.size,
                junho = vendas.filter { vendaDTO -> vendaDTO.data.after(MesEnum.JUNHO.dataInicio) && vendaDTO.data.before(MesEnum.JUNHO.dataFim) && vendaDTO.aceito.equals("TRUE") }.size,
                julho = vendas.filter { vendaDTO -> vendaDTO.data.after(MesEnum.JULHO.dataInicio) && vendaDTO.data.before(MesEnum.JULHO.dataFim) && vendaDTO.aceito.equals("TRUE") }.size,
                agosto = vendas.filter { vendaDTO -> vendaDTO.data.after(MesEnum.AGOSTO.dataInicio) && vendaDTO.data.before(MesEnum.AGOSTO.dataFim) && vendaDTO.aceito.equals("TRUE") }.size,
                outubro = vendas.filter { vendaDTO -> vendaDTO.data.after(MesEnum.OUTUBRO.dataInicio) && vendaDTO.data.before(MesEnum.OUTUBRO.dataFim) && vendaDTO.aceito.equals("TRUE") }.size,
                setembro = vendas.filter { vendaDTO -> vendaDTO.data.after(MesEnum.SETEMPRO.dataInicio) && vendaDTO.data.before(MesEnum.SETEMPRO.dataFim) && vendaDTO.aceito.equals("TRUE") }.size,
                novembro = vendas.filter { vendaDTO -> vendaDTO.data.after(MesEnum.NOVEMBRO.dataInicio) && vendaDTO.data.before(MesEnum.NOVEMBRO.dataFim) && vendaDTO.aceito.equals("TRUE") }.size,
                dezembro = vendas.filter { vendaDTO -> vendaDTO.data.after(MesEnum.DEZEMBRO.dataInicio) && vendaDTO.data.before(MesEnum.DEZEMBRO.dataFim) && vendaDTO.aceito.equals("TRUE") }.size
        ),
                saida = Meses(
                        janeiro = vendas.filter { vendaDTO -> vendaDTO.data.after(MesEnum.JANEIRO.dataInicio) && vendaDTO.data.before(MesEnum.JANEIRO.dataFim) && vendaDTO.aceito.equals("FALSE") }.size,
                        fevereiro = vendas.filter { vendaDTO -> vendaDTO.data.after(MesEnum.FEVEREIRO.dataInicio) && vendaDTO.data.before(MesEnum.FEVEREIRO.dataFim) && vendaDTO.aceito.equals("FALSE") }.size,
                        marco = vendas.filter { vendaDTO -> vendaDTO.data.after(MesEnum.MARCO.dataInicio) && vendaDTO.data.before(MesEnum.MARCO.dataFim) && vendaDTO.aceito.equals("FALSE") }.size,
                        abril = vendas.filter { vendaDTO -> vendaDTO.data.after(MesEnum.ABRIL.dataInicio) && vendaDTO.data.before(MesEnum.ABRIL.dataFim) && vendaDTO.aceito.equals("FALSE") }.size,
                        maio = vendas.filter { vendaDTO -> vendaDTO.data.after(MesEnum.MAIO.dataInicio) && vendaDTO.data.before(MesEnum.MAIO.dataFim) && vendaDTO.aceito.equals("FALSE") }.size,
                        junho = vendas.filter { vendaDTO -> vendaDTO.data.after(MesEnum.JUNHO.dataInicio) && vendaDTO.data.before(MesEnum.JUNHO.dataFim) && vendaDTO.aceito.equals("FALSE") }.size,
                        julho = vendas.filter { vendaDTO -> vendaDTO.data.after(MesEnum.JULHO.dataInicio) && vendaDTO.data.before(MesEnum.JULHO.dataFim) && vendaDTO.aceito.equals("FALSE") }.size,
                        agosto = vendas.filter { vendaDTO -> vendaDTO.data.after(MesEnum.AGOSTO.dataInicio) && vendaDTO.data.before(MesEnum.AGOSTO.dataFim) && vendaDTO.aceito.equals("FALSE") }.size,
                        outubro = vendas.filter { vendaDTO -> vendaDTO.data.after(MesEnum.OUTUBRO.dataInicio) && vendaDTO.data.before(MesEnum.OUTUBRO.dataFim) && vendaDTO.aceito.equals("FALSE") }.size,
                        setembro = vendas.filter { vendaDTO -> vendaDTO.data.after(MesEnum.SETEMPRO.dataInicio) && vendaDTO.data.before(MesEnum.SETEMPRO.dataFim) && vendaDTO.aceito.equals("FALSE") }.size,
                        novembro = vendas.filter { vendaDTO -> vendaDTO.data.after(MesEnum.NOVEMBRO.dataInicio) && vendaDTO.data.before(MesEnum.NOVEMBRO.dataFim) && vendaDTO.aceito.equals("FALSE") }.size,
                        dezembro = vendas.filter { vendaDTO -> vendaDTO.data.after(MesEnum.DEZEMBRO.dataInicio) && vendaDTO.data.before(MesEnum.DEZEMBRO.dataFim) && vendaDTO.aceito.equals("FALSE") }.size
                ))
    }

    override fun getTagsPesquisadas(): TopList {
        val tags = arrayListOf(Tags("casamento classico", 1430), Tags("casamento moderno", 1423), Tags("decoração rústica", 402), Tags("balada", 1393), Tags("drinks com álcool", 1387))
        return TopList(total = 7035, tags = tags)
    }

    override fun getFornecedores(): TopList {
        val tags = arrayListOf(Tags("Casa sofisticada em Mairiporã", 40152), Tags("Decoração LS em Campinas", 34032), Tags("Decoração MV em Campinas", 29154), Tags("Gastronomia LB em Osasco", 27014), Tags("Banda JL em São José dos Campos", 23045))
        return TopList(total = 153043, tags = tags)
    }

    override fun getServicosMaisContratados(): TopList {
        val tags = arrayListOf(Tags("Espaços", 4225), Tags("Decoração", 4041), Tags("Banda", 3844), Tags("Bar & Bebida", 3320), Tags("Foto & Vídeo", 3102))
        return TopList(total = 20234, tags = tags)
    }

}