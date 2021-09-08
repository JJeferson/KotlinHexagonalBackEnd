package com.CadastroCliente.framework.interceptor

import org.springframework.web.servlet.HandlerInterceptor
import java.lang.Exception
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import com.auth0.jwt.exceptions.JWTDecodeException
import com.auth0.jwt.exceptions.TokenExpiredException
import com.google.gson.Gson
import com.CadastroCliente.framework.errors.Error
import javax.naming.Context

class AuthInterceptor(TokenFixo: String?) : HandlerInterceptor {

    var token: String? = TokenFixo
/*
    fun AuthInterceptor(TokenFixo: String?) {
        token = TokenFixo
    }
*/
    @Override
    @Throws(Exception::class)
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val authorization = request.getHeader("Authorization")
        try {
            if (authorization == null) {
                val error = Error().Error("401", "Unauthorized Request",
                        "Precisa informar um header Authorization", 1)
                response.writer.write(Gson().toJson(error))
                response.status = 401
                response.contentType = "application/json"
                return false
            }
            val tokenDecoded = Base64.getDecoder().decode(authorization)
            val tokenComoString = String(tokenDecoded)
            if (tokenComoString != token) {
                val error = Error().Error("401", "Unauthorized Request",
                        "Token está errado. Verifique.", 1)
                response.writer.write(Gson().toJson(error))
                response.status = 401
                response.contentType = "application/json"
                return false
            }
        } catch (ex: TokenExpiredException) {
            val error = Error().Error("401", "Unauthorized Request", "Token expirado", 1)
            response.writer.write(Gson().toJson(error))
            response.status = 401
            response.contentType = "application/json"
            return false
        } catch (e: JWTDecodeException) {
            val error = Error().Error("401", "Unauthorized Request", "Token inválido", 1)
            response.writer.write(Gson().toJson(error))
            response.status = 401
            response.contentType = "application/json"
            return false
        } catch (e: Exception) {
            val error = Error().Error("401", "Unauthorized Request", e.message, 1)
            response.writer.write(Gson().toJson(error))
            response.status = 401
            response.contentType = "application/json"
            return false
        }
        return true
    }

}