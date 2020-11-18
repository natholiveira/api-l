package com.fiap.lejour.controller

import com.fiap.lejour.dto.*
import com.fiap.lejour.service.DashService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import javax.websocket.server.PathParam

@RestController
class DashController(
        @Autowired val dashService: DashService
) {

    @GetMapping(("/dash"))
    @CrossOrigin
    private fun getFaixa(
            @PathParam("mes") mes: MesEnum?,
            @PathParam("filtroEntradaCaixa") filtroEntradaCaixa: Servico?,
            @PathParam("filtroTagsPesquisadas") filtroTagsPesquisadas: Servico?,
            @PathParam("filtroFornecedores") filtroFornecedores: Servico?
    ): DashDTO {

        return DashDTO(
                categorias = getCategorias(),
                conversaoVenda = dashService.getConversaoVenda(),
                faixaInvestimento = dashService.getFaixaInvestimento(mes ?: MesEnum.NOVEMBRO),
                tagsPesquisadas = dashService.getTagsPesquisadas(filtroTagsPesquisadas),
                fornecedores = dashService.getFornecedores(filtroFornecedores),
                entradaCaixa = dashService.getEntradaCaixa(filtroEntradaCaixa),
                servicosMaisContratados = dashService.getServicosMaisContratados(),
                casamento = dashService.getCasamento()
        )
    }
}