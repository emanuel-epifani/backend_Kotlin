package com.example.be_kotlin.request.crud_jdbcSQL.controllers

import com.example.be_kotlin.request.crud_jdbcSQL.models.Studente
import com.example.be_kotlin.request.crud_jdbcSQL.repositories.StudenteRepository

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/studente")
class StudentiController {
    //http://localhost:8080/studente/

    //Create
    @PostMapping("/createStudenteTable")
    fun createStudenteTable() {
        StudenteRepository.createStudenteTable()
    }

    //Insert
    @PostMapping("/insertStudenti")
    fun insertStudent(@RequestBody nome: String, cognome: String, genere: String) {
        StudenteRepository.insertStudent(nome, cognome, genere)
    }

    //Read
    @GetMapping("/readStudent")
    fun readStudent(): ArrayList<Studente> {
        return StudenteRepository.selectStudenti()
    }

    //Update
    @PostMapping("/modificaStudente")
    fun updateStudent(@RequestBody studente: Studente) {
        StudenteRepository.updateStudente(studente)
    }

    //Delete
    @PostMapping("/eliminaStudente")
    fun deleteStudent(@RequestBody studente: Studente) {
        StudenteRepository.deleteStudente(studente)
    }

}
