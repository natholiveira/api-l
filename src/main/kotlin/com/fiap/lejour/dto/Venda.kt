package com.fiap.lejour.dto

import java.text.SimpleDateFormat
import java.util.*

data class VendaJSONDTO (
        val ID: Int,
        val WEDDING_ID: Int,
        val VENDOR_ID: Int,
        val AMOUNT: Double,
        val VENDOR_AMOUNT: Double,
        val CREATED_AT: String,
        val ACCEPTED: String,
        val VENDOR_CATEGORY: String
) {
    fun toVenda() = VendaDTO (
            id = this.ID,
            casamentoId = this.WEDDING_ID,
            vendedorId = this.VENDOR_ID,
            valor = this.AMOUNT,
            valorVendedor = this.VENDOR_AMOUNT,
            data = SimpleDateFormat("yyyy-MM-dd").parse(this.CREATED_AT),
            aceito = this.ACCEPTED,
            categoriaVendedor = this.VENDOR_CATEGORY,
            lucro = this.AMOUNT - this.VENDOR_AMOUNT
    )
}

data class VendaDTO (
        val id: Int,
        val casamentoId: Int,
        val vendedorId: Int,
        val valor: Double,
        val valorVendedor: Double,
        val data: Date,
        val aceito: String,
        val categoriaVendedor: String,
        val lucro: Double
)