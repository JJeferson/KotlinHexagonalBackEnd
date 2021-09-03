package com.CadastroCliente.application.out

import com.CadastroCliente.domain.Cliente

interface ClientePortOut {
    fun SaveCliente(cliente: Cliente?): Cliente?
    fun getCliente(): List<Cliente?>?
    fun getClientePorNome(nome: String?): List<Cliente?>?
    fun getClientePorId(id: String?): Cliente?
}