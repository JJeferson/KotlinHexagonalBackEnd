package com.CadastroCliente.framework.adapters.`in`.rest

import com.CadastroCliente.application.`in`.ClienteUseCase
import com.CadastroCliente.application.out.ClientePortOut
import com.CadastroCliente.domain.Cliente
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.CacheEvict
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/api"])
class ClienteRest {

    @Autowired
    var clienteUseCase: ClienteUseCase? = null

    @Transactional
    @CacheEvict(value = ["/novo_cliente"], allEntries = true)
    @PostMapping("/novo_cliente")
    fun novaCliente(@RequestBody cliente: Cliente?): ResponseEntity<Cliente?>? {
        val clienteSalvo:Cliente? = clienteUseCase?.SaveCliente(cliente)
        return ResponseEntity.ok<Cliente?>(clienteSalvo)
    }
}