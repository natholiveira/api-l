package com.fiap.lejour.dto

enum class Servico(val categoria: String, val nome: String) {
    ESPACO("espaco", "Espaço"),
    SOM_ILUMINACAO("som-iluminacao", "Som & Iluminação"),
    FOTO_FILMAGEM("foto-e-filmagem", "Foto & Filmagem"),
    ACESSORIA_CASAMENTO("acessoria-de-casamento", "Acessoria de Casamento"),
    BUFFET("buffet", "Buffet"),
    DECORACAO_CENOGRAFIA("decoracao-cenografia", "Decoração & Cenografia"),
    BOLOS_DOCE("bolos-doce", "Bolos & Doces"),
    DJ("DJ", "Dj"),
    CORAL_ORQUESTRA("coral-orquestra", "Coral & Orquestra"),
    LISTA_PRESENTES("lista-de-presente", "Lista de Presente");
}

fun getCategorias(): List<Categoria> {
    val categorias = arrayListOf<Categoria>()
    Servico.values().forEach {
        val categoria = Categoria(it, it.nome)
        categorias.add(categoria)
    }
    return categorias
}