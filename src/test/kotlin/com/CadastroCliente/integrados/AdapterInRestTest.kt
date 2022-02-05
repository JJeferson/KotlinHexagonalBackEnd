package com.CadastroCliente.integrados

import com.CadastroCliente.domain.Cliente
import com.CadastroCliente.domain.Contato
import com.CadastroCliente.framework.adapters.out.ClienteRepository
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers


@SpringBootTest
@AutoConfigureMockMvc
class AdapterInRestTest {

    @Autowired
    var clienteRepository: ClienteRepository? = null

    @Autowired
    var mockMvc: MockMvc? = null

    @Autowired
    private val objectMapper: ObjectMapper? = null


    fun alimentaObj():Cliente{
        var novoObj:Cliente = Cliente()
        novoObj.id="teste"
        novoObj.nome="teste"
        var c:Contato = Contato()
        c.fone="123456789"
        novoObj.contatos= listOf(c)

        return novoObj
    }


    @Test
    fun findbyidTest404(){
        var idValido="11aa"
        val testeInsercao = alimentaObj()
        testeInsercao.id=idValido
        clienteRepository?.save(testeInsercao)

        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        headers["Authorization"] = "MTIzNjU0Nzg5"

        idValido=idValido+22
        mockMvc!!.perform(
            MockMvcRequestBuilders.get("/api/findbyid?id=$idValido").
            contentType("application/json").
            headers(headers)).
        andExpect(MockMvcResultMatchers.status().`is`(404))
        clienteRepository?.delete(testeInsercao)
    }
   @Test
   fun findbyidTestok(){
       var idValido="11aa"
       val testeInsercao = alimentaObj()
       testeInsercao.id=idValido
       clienteRepository?.save(testeInsercao)

       val headers = HttpHeaders()
       headers.contentType = MediaType.APPLICATION_JSON
       headers["Authorization"] = "MTIzNjU0Nzg5"

       mockMvc!!.perform(
           MockMvcRequestBuilders.get("/api/findbyid?id=$idValido").
           contentType("application/json").
           headers(headers)).
           andExpect(MockMvcResultMatchers.status().isOk)
       clienteRepository?.delete(testeInsercao)
   }


    @Test
    fun novoClienteTest()  {

        var headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        headers["Authorization"] = "MTIzNjU0Nzg5"

        var obj:Cliente = alimentaObj()
        mockMvc!!.perform(
            MockMvcRequestBuilders.post("/api/novo_cliente").
            contentType("application/json").
            content(objectMapper!!.writeValueAsString(obj)).
            headers(headers)).
            andExpect(MockMvcResultMatchers.status().isOk)

    }



}