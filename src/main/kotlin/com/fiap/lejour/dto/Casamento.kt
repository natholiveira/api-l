package com.fiap.lejour.dto

import java.text.SimpleDateFormat
import java.util.*

data class CasamentoJSONDTO (
        val ID: Int,
        val OWNER_ID: Int,
        val BUDGET: String,
        val WEDDING_DATE: String,
        val NUMBER_OF_GUESTS: String,
        val STYLE: String
) {
    fun toCasamento(): CasamentoDTO = CasamentoDTO(
            id = this.ID,
            ownerId = this.OWNER_ID,
            budget = this.BUDGET.toInt(),
            weddingDate = SimpleDateFormat("yyyy-MM-dd").parse(this.WEDDING_DATE),
            numberOfGuests = this.NUMBER_OF_GUESTS,
            style = this.STYLE
    )
}

data class CasamentoDTO(
        val id: Int,
        val ownerId: Int,
        val budget: Int,
        val weddingDate: Date,
        val numberOfGuests: String,
        val style: String
)