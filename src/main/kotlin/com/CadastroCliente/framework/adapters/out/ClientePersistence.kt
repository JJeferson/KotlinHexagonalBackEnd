package com.CadastroCliente.framework.adapters.out

import com.CadastroCliente.application.out.ClientePortOut
import com.CadastroCliente.config.controller_advice.exceptions.NotFoundException
import com.CadastroCliente.domain.Cliente
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ClientePersistence: ClientePortOut {

    @Autowired
    var clienteRepository: ClienteRepository? = null

    override fun SaveCliente(cliente: Cliente?): Cliente? {
        return clienteRepository?.save(cliente)
    }

    override fun getCliente(): List<Cliente?>? {
        if(clienteRepository?.findAll()?.size==0){
            throw NotFoundException("Nenhum registro encontrado")
        }
        return clienteRepository?.findAll()
    }

    override fun getClientePorNome(nome: String?): List<Cliente?>? {
        return clienteRepository?.findAllByNomeContains(nome)
    }

    override fun getClientePorId(id: String?): Cliente? {
       return id?.let { clienteRepository?.findById(it)?.get() }
    }
}