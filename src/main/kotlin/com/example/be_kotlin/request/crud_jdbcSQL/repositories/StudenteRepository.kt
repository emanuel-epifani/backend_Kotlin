package com.example.be_kotlin.request.crud_jdbcSQL.repositories

import com.example.be_kotlin.request.crud_jdbcSQL.models.Studente
import java.sql.*


//https://kotlinlang.org/docs/jvm-spring-boot-restful.html#configure-the-database
//https://levelup.gitconnected.com/how-to-access-database-with-kotlin-6b86f6680cd7



class StudenteRepository {


    companion object {
        private const val DB_URL = "jdbc:postgresql://localhost:5433/emanuelepifani"
        private const val USER = "emanuelepifani"
        private const val PASS = "Giurisprudenza39"

        //Create-V
        fun createStudenteTable() {
            try {
                val conn: Connection = DriverManager.getConnection(DB_URL, USER, PASS)
                var pstmt: PreparedStatement? = null
                pstmt = conn.prepareStatement("CREATE TABLE Studente (nome varchar(255), cognome varchar(255), genere varchar(255));")
                pstmt.executeUpdate()
                pstmt.close() //chiudo lo statement
                conn.close() //chiudo la connessione
            } catch (e: SQLException) {
                println("SQLException: " + e.message)
                println("SQLState: " + e.sqlState)
                println("VendorError: " + e.errorCode)
            }
        }

        //Insert
        fun insertStudent(nome: String, cognome: String, genere: String) {
            try {
                val conn: Connection = DriverManager.getConnection(DB_URL, USER, PASS)
                var pstmt: PreparedStatement? = null
                pstmt = conn.prepareStatement("INSERT INTO studente (nome, cognome, genere) VALUES (?,?,?)")
                pstmt.setString(1, nome) //nome personaggio
                pstmt.setString(2, cognome) //ruolo personaggio
                pstmt.setString(3, genere) //isAlive
                pstmt.executeUpdate()
                pstmt.close() //chiudo lo statement
                conn.close() //chiudo la connessione
            } catch (e: SQLException) {
                println("SQLException: " + e.message)
                println("SQLState: " + e.sqlState)
                println("VendorError: " + e.errorCode)
            }
        }

        //Read
        fun selectStudenti(): ArrayList<Studente> {
            try {
                val studentList = ArrayList<Studente>()
                val conn: Connection = DriverManager.getConnection(DB_URL, USER, PASS)
                val stmt: Statement = conn.createStatement()
                val rs: ResultSet = stmt.executeQuery("SELECT * FROM studente")
                while (rs.next()) {
                    val st = Studente(
                        rs.getString("nome"),
                        rs.getString("cognome"),
                        rs.getString("genere")
                    )
                    studentList.add(st)
                }
                conn.close()
                stmt.close()
                return studentList
            } catch (ex: SQLException) {
                // handle any errors
                println("SQLException: " + ex.message)
                println("SQLState: " + ex.sqlState)
                println("VendorError: " + ex.errorCode)
            }
            return ArrayList()
        }

        //Update
        fun updateStudente(studente: Studente) {
            try {
                val SQL_DELETE = "UPDATE studente SET nome=?,cognome=?,genere=? WHERE nome=?"
                val conn: Connection = DriverManager.getConnection(DB_URL, USER, PASS)
                val pstmt: PreparedStatement = conn.prepareStatement(SQL_DELETE)
                pstmt.setString(1, studente.nome)
                pstmt.setString(2, studente.cognome)
                pstmt.setString(3, studente.genere)
                pstmt.setString(4, "Zeno")
                val row = pstmt.executeUpdate()
                conn.close()
                pstmt.close()
            } catch (ex: SQLException) {
                println("SQLException: " + ex.message)
                println("SQLState: " + ex.sqlState)
                println("VendorError: " + ex.errorCode)
            }
        }

        //Delete
        fun deleteStudente(studente: Studente): Studente? {
            try {
                val SQL_DELETE = "DELETE FROM studente WHERE nome=? AND cognome=? AND genere=?"
                val conn: Connection = DriverManager.getConnection(DB_URL, USER, PASS)
                val stmt: PreparedStatement = conn.prepareStatement(SQL_DELETE)
                stmt.setString(1, studente.nome)
                stmt.setString(2, studente.cognome)
                stmt.setString(3, studente.genere)
                val row = stmt.executeUpdate()
                conn.close()
                stmt.close()
                return studente
            } catch (ex: SQLException) {
                println("SQLException: " + ex.message)
                println("SQLState: " + ex.sqlState)
                println("VendorError: " + ex.errorCode)
            }
            return null
        }
    }


}

