package com.fiap.lejour.dto

data class TopList (
        val total: Int,
        val tags: List<Tags>
)

data class Tags (
        val nome: String,
        val total: Int
)