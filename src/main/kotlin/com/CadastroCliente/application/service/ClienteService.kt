package com.CadastroCliente.application.service

import com.CadastroCliente.application.`in`.ClienteUseCase
import com.CadastroCliente.application.out.ClientePortOut
import com.CadastroCliente.domain.Cliente
import com.CadastroCliente.framework.adapters.out.ClienteRepository
import org.springframework.beans.factory.annotation.Autowired

class ClienteService : ClienteUseCase{
    @Autowired
    var clientePortOut: ClientePortOut? = null

    override fun SaveCliente(cliente: Cliente?): Cliente? {
       return clientePortOut?.SaveCliente(cliente)
    }

    override fun getCliente(): List<Cliente?>? {
       return  clientePortOut?.getCliente()
    }

    override fun getClientePorNome(nome: String?): List<Cliente?>? {
        return clientePortOut?.getClientePorNome(nome)
    }

    override fun getNotasPorId(id: String?): Cliente? {
        return clientePortOut?.getNotasPorId(id)
    }
}