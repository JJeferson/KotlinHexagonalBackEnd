package com.CadastroCliente.domain

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Document(collection = "Cliente")
class Cliente {

    @Id
    @JsonProperty("_id")
    var id: String? = null
    var nome:String? = null
    var idade:Integer? = null
    var contatos:List<Contato>?=null
    var enderecos:List<Endereco>?=null
}