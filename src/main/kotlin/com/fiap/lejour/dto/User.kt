package com.fiap.lejour.dto

import java.text.SimpleDateFormat
import java.util.*

data class UserJSONDTO(
        val ID: Int,
        val CREATED_AT: String
) {
    fun toUser() = User (
            id = this.ID,
            data = SimpleDateFormat("yyyy-MM-dd").parse(this.CREATED_AT)
    )
}

data class User(
        val id: Int,
        val data: Date
)