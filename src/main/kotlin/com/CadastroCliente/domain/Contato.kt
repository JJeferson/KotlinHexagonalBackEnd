package com.CadastroCliente.domain

import javax.validation.constraints.Max
import javax.validation.constraints.Min

class Contato {
    @Min(9)
    @Max(11)
    var fone:String? = null

}