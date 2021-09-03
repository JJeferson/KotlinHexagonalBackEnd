package com.CadastroCliente.framework.adapters.`in`.rest

import com.CadastroCliente.application.`in`.ClienteUseCase
import com.CadastroCliente.application.out.ClientePortOut
import com.CadastroCliente.domain.Cliente
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.CacheEvict
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*
import java.util.ArrayList

@RestController
@RequestMapping(value = ["/api"])
class ClienteRest {

    @Autowired
    var clienteUseCase: ClienteUseCase? = null

    @Transactional
    @CacheEvict(value = ["/novo_cliente"], allEntries = true)
    @PostMapping("/novo_cliente")
    fun novoCliente(@RequestBody cliente: Cliente?): ResponseEntity<Cliente?>? {
        val clienteSalvo:Cliente? = clienteUseCase?.SaveCliente(cliente)
        return ResponseEntity.ok<Cliente?>(clienteSalvo)
    }


    @RequestMapping(value = ["/findbyid"], method = [RequestMethod.GET])
    fun findbyid(@RequestParam(value = "id", required = false, defaultValue = "0") id: String)
    : ResponseEntity<Cliente?>? {
        return if (id == "0") {
            val clienteException = Cliente()
            clienteException.nome="ERRO: um id precisa ser informado"
            ResponseEntity.status(412).body<Cliente?>(clienteException)
        } else {
            val cliente: Cliente? = clienteUseCase?.getClientePorId(id)
            ResponseEntity.ok<Cliente?>(cliente)
        }
    }


    @RequestMapping(value = ["/lista_cliente_por_nome"], method = [RequestMethod.GET])
    fun listaClientePorNome(@RequestParam(value = "nome", required = false, defaultValue = "0")
                               nome: String): ResponseEntity<List<Cliente?>>? {
        if (nome.equals("0")) {
            val clienteException = Cliente()
            clienteException.nome="ERRO: Precisa informar um nome para pesquisa"
            val listCliente: MutableList<Cliente> = ArrayList<Cliente>()
            listCliente.add(clienteException)
            return ResponseEntity.status(412).body<List<Cliente?>>(listCliente)
        }
        var listCliente = clienteUseCase?.getClientePorNome(nome)
        if(listCliente?.size==0){
            val clienteException = Cliente()
            clienteException.nome="ERRO: Nenhum registro encontrado"
            val listCliente: MutableList<Cliente> = ArrayList<Cliente>()
            listCliente.add(clienteException)
            return ResponseEntity.status(404).body<List<Cliente?>>(listCliente)
        }

        return ResponseEntity.ok<List<Cliente?>>(listCliente)
    }


    @RequestMapping(value = ["/todos"], method = [RequestMethod.GET])
    fun todasEmLista(): ResponseEntity<List<Cliente?>>? {
        var listCliente = clienteUseCase?.getCliente()
        return ResponseEntity.ok<List<Cliente?>>(listCliente)
    }
}