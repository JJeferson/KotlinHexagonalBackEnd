package com.CadastroCliente.framework.errors

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

class Error {
    private val serialVersionUID = -3098212556060809862L

    @JsonProperty(value = "code", required = true)
    private var code: String? = null

    @JsonProperty(value = "reason", required = true)
    private var reason: String? = null

    @JsonProperty(value = "message", required = false)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private var message: String? = null

    @JsonProperty(value = "status", required = false)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private var status: Int? = null

    @JsonProperty(value = "referenceError", required = false)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private val referenceError: String? = null

    @JsonProperty(value = "@baseType", required = false)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private val baseType: String? = null

    @JsonProperty(value = "@schemaLocation", required = false)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private val schemaLocation: String? = null

    @JsonProperty(value = "@type", required = false)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private val type: String? = null
    /*
    fun Error() {
        super()
    }
    */
    fun Error(code: String?, reason: String?, status: Int?) {
        this.code = code
        this.reason = reason
        this.status = status
    }

    fun Error(code: String?, reason: String?, message: String?, status: Int?) {
        this.code = code
        this.reason = reason
        this.message = message
        this.status = status
    }
}