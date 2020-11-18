package com.fiap.lejour.dto

data class GraficoEntradaSaida (
        val entrada: Meses,
        val saida: Meses
)

data class Meses (
        val janeiro: Int,
        val fevereiro: Int,
        val marco: Int,
        val abril: Int,
        val maio: Int,
        val junho: Int,
        val julho: Int,
        val agosto: Int,
        val setembro: Int,
        val outubro: Int,
        val novembro: Int,
        val dezembro: Int,
)