package com.CadastroCliente.application.`in`

import com.CadastroCliente.domain.Cliente

interface ClienteUseCase {
    fun SaveCliente(cliente: Cliente?): Cliente?
    fun getCliente(): List<Cliente?>?
    fun getClientePorNome(nome: String?): List<Cliente?>?
    fun getClientePorId(id: String?): Cliente?
}