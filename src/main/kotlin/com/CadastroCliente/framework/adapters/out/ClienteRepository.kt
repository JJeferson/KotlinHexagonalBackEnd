package com.CadastroCliente.framework.adapters.out

import com.CadastroCliente.domain.Cliente
import org.springframework.data.mongodb.repository.MongoRepository

interface ClienteRepository : MongoRepository<Cliente, String> {
    fun save(cliente: Cliente?): Cliente?
    fun findAllByNomeContains (nome : String? ):List<Cliente?>
}