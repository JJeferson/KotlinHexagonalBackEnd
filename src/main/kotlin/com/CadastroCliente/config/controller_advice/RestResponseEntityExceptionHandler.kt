package com.CadastroCliente.config.controller_advice


import com.CadastroCliente.config.controller_advice.exceptions.BadGatewayException
import com.CadastroCliente.config.controller_advice.exceptions.NotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.client.HttpClientErrorException.BadRequest
import org.springframework.web.client.HttpClientErrorException.Unauthorized
import org.springframework.web.client.HttpServerErrorException.InternalServerError
import java.security.KeyManagementException
import java.security.KeyStoreException
import java.security.NoSuchAlgorithmException
import java.time.Clock

@RestControllerAdvice
class RestResponseEntityExceptionHandler {


    private val INTERNAL_SERVER_ERROR = "INTERNAL SERVER ERROR"


    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    @ExceptionHandler(
        BadGatewayException::class
    )
    fun handleBadGatewayException(ex: BadGatewayException): ResponseEntity<Any> {
        val errors = createResponse(
            HttpStatus.BAD_GATEWAY.value(),
            "Bad Gateway",
            ex.message        )
        return ResponseEntity(errors, HttpStatus.BAD_GATEWAY)
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(
        NotFoundException::class
    )
    fun handleNotFoundException(ex: NotFoundException): ResponseEntity<Any> {
        val errors = createResponse(HttpStatus.NOT_FOUND.value(), "NOT FOUND", ex.message)
        return ResponseEntity(errors, HttpStatus.NOT_FOUND)
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(
        InternalServerError::class
    )
    fun handleInternalServerErrorException(ex: InternalServerError): ResponseEntity<Any> {
        val errors = createResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), INTERNAL_SERVER_ERROR, ex.message)
        return ResponseEntity(errors, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(
        BadRequest::class
    )
    fun handleBadRequestException(ex: BadRequest): ResponseEntity<Any> {
        val errors = createResponse(HttpStatus.BAD_REQUEST.value(), "BAD REQUEST", ex.message)
        return ResponseEntity(errors, HttpStatus.BAD_REQUEST)
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(
        Unauthorized::class
    )
    fun handleUnauthorizedException(ex: Unauthorized): ResponseEntity<Any> {
        val errors = createResponse(HttpStatus.UNAUTHORIZED.value(), "UNAUTHORIZED", ex.message)
        return ResponseEntity(errors, HttpStatus.UNAUTHORIZED)
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(
        NoSuchAlgorithmException::class
    )
    fun handleNoSuchAlgorithmException(ex: Exception): ResponseEntity<Any> {
        val errors = createResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), INTERNAL_SERVER_ERROR, ex.message)
        return ResponseEntity(errors, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(
        KeyManagementException::class
    )
    fun handleKeyManagementException(ex: KeyManagementException): ResponseEntity<Any> {
        val errors = createResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), INTERNAL_SERVER_ERROR, ex.message)
        return ResponseEntity(errors, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(
        KeyStoreException::class
    )
    fun handleKeyStoreException(ex: KeyStoreException): ResponseEntity<Any> {
        val errors = createResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), INTERNAL_SERVER_ERROR, ex.message)
        return ResponseEntity(errors, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(
        NullPointerException::class
    )
    fun handleNullPointerException(ex: NullPointerException): ResponseEntity<Any> {
        val errors = createResponse(HttpStatus.BAD_REQUEST.value(), "BAD REQUEST", ex.message)
        return ResponseEntity(errors, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    fun createResponse(status: Int, error: String?, message: String?): kotlin.collections.Map<String, String?> {
        val errors: MutableMap<String, String?> = HashMap()
        errors["timestamp"] = Clock.systemUTC().instant().toString()
        errors["status"] = status.toString()
        errors["error"] = error
        errors["message"] = message
        return errors
    }
}