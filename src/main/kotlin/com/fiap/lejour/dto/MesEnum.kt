package com.fiap.lejour.dto

import java.text.SimpleDateFormat
import java.util.*

enum class MesEnum(val dataInicio: Date, val dataFim: Date) {
    JANEIRO(
            SimpleDateFormat("dd-MM-yyyy").parse("31-12-2018"),
            SimpleDateFormat("dd-MM-yyyy").parse("01-02-2020")
    ),
    FEVEREIRO(
            SimpleDateFormat("dd-MM-yyyy").parse("31-01-2019"),
            SimpleDateFormat("dd-MM-yyyy").parse("01-03-2020")
    ),
    MARCO(
            SimpleDateFormat("dd-MM-yyyy").parse("29-02-2020"),
            SimpleDateFormat("dd-MM-yyyy").parse("01-04-2020")
    ),
    ABRIL(
            SimpleDateFormat("dd-MM-yyyy").parse("31-03-2020"),
            SimpleDateFormat("dd-MM-yyyy").parse("01-05-2020")
    ),
    MAIO(
            SimpleDateFormat("dd-MM-yyyy").parse("30-04-2020"),
            SimpleDateFormat("dd-MM-yyyy").parse("01-06-2020")
    ),
    JUNHO(
            SimpleDateFormat("dd-MM-yyyy").parse("31-05-2020"),
            SimpleDateFormat("dd-MM-yyyy").parse("01-07-2020")
    ),
    JULHO(
            SimpleDateFormat("dd-MM-yyyy").parse("30-06-2020"),
            SimpleDateFormat("dd-MM-yyyy").parse("01-08-2020")
    ),
    AGOSTO(
            SimpleDateFormat("dd-MM-yyyy").parse("31-07-2020"),
            SimpleDateFormat("dd-MM-yyyy").parse("01-09-2020")
    ),
    SETEMPRO(
            SimpleDateFormat("dd-MM-yyyy").parse("30-08-2020"),
            SimpleDateFormat("dd-MM-yyyy").parse("01-10-2020")
    ),
    OUTUBRO(
            SimpleDateFormat("dd-MM-yyyy").parse("31-09-2020"),
            SimpleDateFormat("dd-MM-yyyy").parse("01-11-2020")
    ),
    NOVEMBRO(
            SimpleDateFormat("dd-MM-yyyy").parse("30-10-2020"),
            SimpleDateFormat("dd-MM-yyyy").parse("01-12-2020")
    ),
    DEZEMBRO(
            SimpleDateFormat("dd-MM-yyyy").parse("31-11-2020"),
            SimpleDateFormat("dd-MM-yyyy").parse("01-01-2021")
    )
}