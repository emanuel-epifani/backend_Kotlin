package com.example.be_kotlin.request.crud_jdbcSQL.models


class Studente {
    var nome: String? = null
    var cognome: String? = null
    var genere: String? = null

    constructor() {}
    constructor(nome: String?, cognome: String?, genere: String?) {
        this.nome = nome
        this.cognome = cognome
        this.genere = genere
    }

    override fun toString(): String {
        return "Studente{" +
                "nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", genere='" + genere + '\'' +
                '}'
    }
}