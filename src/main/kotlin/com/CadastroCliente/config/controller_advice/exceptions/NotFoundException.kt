package com.CadastroCliente.config.controller_advice.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus


@ResponseStatus(code = HttpStatus.NOT_FOUND)
class NotFoundException(message: String?) : RuntimeException(message) {
    companion object {
        private const val serialVersionUID = 5291022396354246343L
    }
}