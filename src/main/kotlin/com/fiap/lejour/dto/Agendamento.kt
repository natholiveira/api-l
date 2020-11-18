package com.fiap.lejour.dto

import java.text.SimpleDateFormat
import java.util.*

data class AgendamentoJSONDTO (
        val ID: Int,
        val WEDDING_ID: Int,
        val VENDOR_ID: Int,
        val STATUS: String,
        val VENDOR_CATEGORY: String,
        val BEGINS_AT: String
) {
    fun toAgendamento() = AgendamentoDTO(
            id = this.ID,
            casamentoId = this.WEDDING_ID,
            vendedorId = this.VENDOR_ID,
            status = this.STATUS,
            categoriaVendedor = this.VENDOR_CATEGORY,
            data = SimpleDateFormat("yyyy-MM-dd").parse(this.BEGINS_AT)
    )
}

data class AgendamentoDTO (
        val id: Int,
        val casamentoId: Int,
        val vendedorId: Int,
        val status: String,
        val categoriaVendedor: String,
        val data: Date
)