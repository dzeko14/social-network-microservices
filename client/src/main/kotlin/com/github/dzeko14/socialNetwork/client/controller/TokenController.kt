package com.github.dzeko14.socialNetwork.client.controller

import com.github.dzeko14.socialNetwork.client.validator.TokenValidator
import com.github.dzeko14.socialNetwork.entities.Token
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class TokenController @Autowired constructor(
        private val tokenValidator: TokenValidator
) {
    @PostMapping("/token/validate")
    fun validateToken(@RequestBody token: Token) {
        tokenValidator.validate(token)
    }
}