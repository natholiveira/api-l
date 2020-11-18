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
        val janeiro = ConversaoVendas(titulo = "Jan", entrou = 96, agendou = 93, orcou = 85, contratou = 79, total = 122530.0)
        val fevereiro = ConversaoVendas(titulo = "Fev", entrou = 115, agendou = 110, orcou = 104, contratou = 97, total = 149432.0)
        val marco = ConversaoVendas(titulo = "Mar", entrou = 21, agendou = 19, orcou = 17, contratou = 13, total = 120830.0)
        val abril = ConversaoVendas(titulo = "Abr", entrou = 19, agendou = 17, orcou = 13, contratou = 12, total = 132547.0)
        val maio = ConversaoVendas(titulo = "Mai", entrou = 24, agendou = 23, orcou = 20, contratou = 18, total = 126447.0)
        val jun = ConversaoVendas(titulo = "Jun", entrou = 17, agendou = 16, orcou = 14, contratou = 10, total = 162645.0)
        val julho = ConversaoVendas(titulo = "Jul", entrou = 14, agendou = 12, orcou = 10, contratou = 9, total = 152835.0)
        val agosto = ConversaoVendas(titulo = "Ago", entrou = 13, agendou = 10, orcou = 8, contratou = 7, total = 143704.0)
        val setembro = ConversaoVendas(titulo = "Set", entrou = 16, agendou = 13, orcou = 12, contratou = 10, total = 158645.0)
        val outubro = ConversaoVendas(titulo = "Out", entrou = 17, agendou = 14, orcou = 12, contratou = 9, total = 149978.0)
        val novembro = ConversaoVendas(titulo = "Nov", entrou = 9, agendou = 2, orcou = 1, contratou = 0, total = 135465.0)
        val dezembro = ConversaoVendas(titulo = "Dez", entrou = 0, agendou = 0, orcou = 0, contratou = 0, total = 82203.0)

        return ConversaoVendaList(arrayListOf(janeiro, fevereiro, marco, abril, maio, jun, julho, agosto, setembro, outubro, novembro, dezembro))
    }

    override fun getEntradaCaixa(servico: Servico?): GraficoEntradaSaida {
        val vendasEntrada = if (servico == null) vendas else vendas.filter { vendaDTO -> vendaDTO.categoriaVendedor.equals(servico.categoria) }

        return GraficoEntradaSaida(entrada = Meses(
                janeiro = Math.round(vendasEntrada.filter { vendaDTO -> vendaDTO.data.after(MesEnum.JANEIRO.dataInicio) && vendaDTO.data.before(MesEnum.JANEIRO.dataFim) }.map { it.lucro }.sum()).toInt(),
                fevereiro = Math.round(vendasEntrada.filter { vendaDTO -> vendaDTO.data.after(MesEnum.FEVEREIRO.dataInicio) && vendaDTO.data.before(MesEnum.FEVEREIRO.dataFim) }.map { it.lucro }.sum()).toInt(),
                marco = Math.round(vendasEntrada.filter { vendaDTO -> vendaDTO.data.after(MesEnum.MARCO.dataInicio) && vendaDTO.data.before(MesEnum.MARCO.dataFim) }.map { it.lucro }.sum()).toInt(),
                abril = Math.round(vendasEntrada.filter { vendaDTO -> vendaDTO.data.after(MesEnum.ABRIL.dataInicio) && vendaDTO.data.before(MesEnum.ABRIL.dataFim) }.map { it.lucro }.sum()).toInt(),
                maio = Math.round(vendasEntrada.filter { vendaDTO -> vendaDTO.data.after(MesEnum.MAIO.dataInicio) && vendaDTO.data.before(MesEnum.MAIO.dataFim) }.map { it.lucro }.sum()).toInt(),
                junho = Math.round(vendasEntrada.filter { vendaDTO -> vendaDTO.data.after(MesEnum.JUNHO.dataInicio) && vendaDTO.data.before(MesEnum.JUNHO.dataFim) }.map { it.lucro }.sum()).toInt(),
                julho = Math.round(vendasEntrada.filter { vendaDTO -> vendaDTO.data.after(MesEnum.JULHO.dataInicio) && vendaDTO.data.before(MesEnum.JULHO.dataFim) }.map { it.lucro }.sum()).toInt(),
                agosto = Math.round(vendasEntrada.filter { vendaDTO -> vendaDTO.data.after(MesEnum.AGOSTO.dataInicio) && vendaDTO.data.before(MesEnum.AGOSTO.dataFim) }.map { it.lucro }.sum()).toInt(),
                outubro = Math.round(vendasEntrada.filter { vendaDTO -> vendaDTO.data.after(MesEnum.OUTUBRO.dataInicio) && vendaDTO.data.before(MesEnum.OUTUBRO.dataFim) }.map { it.lucro }.sum()).toInt(),
                setembro = Math.round(vendasEntrada.filter { vendaDTO -> vendaDTO.data.after(MesEnum.SETEMPRO.dataInicio) && vendaDTO.data.before(MesEnum.SETEMPRO.dataFim) }.map { it.lucro }.sum()).toInt(),
                novembro = Math.round(vendasEntrada.filter { vendaDTO -> vendaDTO.data.after(MesEnum.NOVEMBRO.dataInicio) && vendaDTO.data.before(MesEnum.NOVEMBRO.dataFim) }.map { it.lucro }.sum()).toInt(),
                dezembro = Math.round(vendasEntrada.filter { vendaDTO -> vendaDTO.data.after(MesEnum.DEZEMBRO.dataInicio) && vendaDTO.data.before(MesEnum.DEZEMBRO.dataFim) }.map { it.lucro }.sum()).toInt()
        ),
        saida = Meses(
                janeiro = Math.round(vendasEntrada.filter { vendaDTO -> vendaDTO.data.after(MesEnum.JANEIRO.dataInicio) && vendaDTO.data.before(MesEnum.JANEIRO.dataFim) }.map { it.valor }.sum()).toInt(),
                fevereiro = Math.round(vendasEntrada.filter { vendaDTO -> vendaDTO.data.after(MesEnum.FEVEREIRO.dataInicio) && vendaDTO.data.before(MesEnum.FEVEREIRO.dataFim) }.map { it.valor }.sum()).toInt(),
                marco = Math.round(vendasEntrada.filter { vendaDTO -> vendaDTO.data.after(MesEnum.MARCO.dataInicio) && vendaDTO.data.before(MesEnum.MARCO.dataFim) }.map { it.valor }.sum()).toInt(),
                abril = Math.round(vendasEntrada.filter { vendaDTO -> vendaDTO.data.after(MesEnum.ABRIL.dataInicio) && vendaDTO.data.before(MesEnum.ABRIL.dataFim) }.map { it.valor }.sum()).toInt(),
                maio = Math.round(vendasEntrada.filter { vendaDTO -> vendaDTO.data.after(MesEnum.MAIO.dataInicio) && vendaDTO.data.before(MesEnum.MAIO.dataFim) }.map { it.valor }.sum()).toInt(),
                junho = Math.round(vendasEntrada.filter { vendaDTO -> vendaDTO.data.after(MesEnum.JUNHO.dataInicio) && vendaDTO.data.before(MesEnum.JUNHO.dataFim) }.map { it.valor }.sum()).toInt(),
                julho = Math.round(vendasEntrada.filter { vendaDTO -> vendaDTO.data.after(MesEnum.JULHO.dataInicio) && vendaDTO.data.before(MesEnum.JULHO.dataFim) }.map { it.valor }.sum()).toInt(),
                agosto = Math.round(vendasEntrada.filter { vendaDTO -> vendaDTO.data.after(MesEnum.AGOSTO.dataInicio) && vendaDTO.data.before(MesEnum.AGOSTO.dataFim) }.map { it.valor }.sum()).toInt(),
                outubro = Math.round(vendasEntrada.filter { vendaDTO -> vendaDTO.data.after(MesEnum.OUTUBRO.dataInicio) && vendaDTO.data.before(MesEnum.OUTUBRO.dataFim) }.map { it.valor }.sum()).toInt(),
                setembro = Math.round(vendasEntrada.filter { vendaDTO -> vendaDTO.data.after(MesEnum.SETEMPRO.dataInicio) && vendaDTO.data.before(MesEnum.SETEMPRO.dataFim) }.map { it.valor }.sum()).toInt(),
                novembro = Math.round(vendasEntrada.filter { vendaDTO -> vendaDTO.data.after(MesEnum.NOVEMBRO.dataInicio) && vendaDTO.data.before(MesEnum.NOVEMBRO.dataFim) }.map { it.valor }.sum()).toInt(),
                dezembro = Math.round(vendasEntrada.filter { vendaDTO -> vendaDTO.data.after(MesEnum.DEZEMBRO.dataInicio) && vendaDTO.data.before(MesEnum.DEZEMBRO.dataFim) }.map { it.valor }.sum()).toInt()
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

    override fun getTagsPesquisadas(servico: Servico?): TopList {
        var tags = arrayListOf<Tags>()
        if (servico!= null) {
            when(servico) {
                Servico.ESPACO -> tags = arrayListOf(
                        Tags("casamento classico", 1324),
                        Tags("espaço moderno", 1205),
                        Tags("casamento no campo", 1076),
                        Tags("Salão Amplo", 856),
                        Tags("cerimônia ao ar livre", 765))
                Servico.SOM_ILUMINACAO -> tags = arrayListOf(
                        Tags("balada", 1057),
                        Tags("DJ de casamento", 997),
                        Tags("palco", 864),
                        Tags("painel de led", 756),
                        Tags("atração em casamento", 509))
                Servico.FOTO_FILMAGEM -> tags = arrayListOf(
                        Tags("fotógrafo", 1004),
                        Tags("foto natural", 932),
                        Tags("fotografia de casamento no campo", 845),
                        Tags("impressão de fotos", 654),
                        Tags("álbum", 508))
            }
        } else {
            tags = arrayListOf(
                    Tags("casamento classico", 1324),
                    Tags("espaço moderno", 1205),
                    Tags("balada", 1057),
                    Tags("fotógrafo", 1004),
                    Tags("DJ de casamento", 997))
        }

        return TopList(total = 7035, tags = tags)
    }

    override fun getFornecedores(servico: Servico?): TopList {
        var tags = arrayListOf<Tags>()
        if (servico!= null) {
            when(servico) {
                Servico.ESPACO -> tags = arrayListOf(
                        Tags("Casa sofisticada em Mairiporã", 1430),
                        Tags("Fazenda histórica com casarão em Itu", 1423),
                        Tags("Espaço Contemporâneo em Pinheiros", 1402),
                        Tags("Espaço Contemporâneo em Pinheiros", 1393),
                        Tags("cerimônia ao ar livre", 1387))
                Servico.SOM_ILUMINACAO -> tags = arrayListOf(
                        Tags("Som&iluminação FSL em Sorocaba", 1323),
                        Tags("Som e Iluminação RP em São Paulo", 1123),
                        Tags("DJ, Som & Iluminação KS em Sorocaba", 832),
                        Tags("Som e Iluminação SS em Mairiporã", 793),
                        Tags("Som e Iluminação EE em São Paulo", 503))
                Servico.FOTO_FILMAGEM -> tags = arrayListOf(
                        Tags("Foto & Filmagem DV em São Paulo", 1295),
                        Tags("Foto & Filmagem OS em São Paulo", 1105),
                        Tags("Foto & Filmagem AE em Atibaia", 907),
                        Tags("Fotografia IS em Campinas", 867),
                        Tags("Fotografia CF em São Paulo", 674))
            }
        } else {
            tags = arrayListOf(
                    Tags("Casa sofisticada em Mairiporã", 1430),
                    Tags("Fazenda histórica com casarão em Itu", 1423),
                    Tags("Som&iluminação FSL em Sorocaba", 1323),
                    Tags("Foto & Filmagem DV em São Paulo", 1295),
                    Tags("Decoração LS em Campinas", 1142))
        }
        return TopList(total = 153043, tags = tags)
    }

    override fun getServicosMaisContratados(): TopList {
        val tags = arrayListOf(Tags("Espaços", 4225), Tags("Decoração", 4041), Tags("Banda", 3844), Tags("Bar & Bebida", 3320), Tags("Foto & Vídeo", 3102))
        return TopList(total = 20234, tags = tags)
    }

}