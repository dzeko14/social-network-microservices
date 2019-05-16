package com.github.dzeko14.socialNetwork.client.validator

import com.github.dzeko14.socialNetwork.client.feignClient.AuthClient
import com.github.dzeko14.socialNetwork.client.model.TokenRequest
import com.github.dzeko14.socialNetwork.entities.Token
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.server.ResponseStatusException

@Component
class TokenValidator @Autowired constructor(
        private val authClient: AuthClient
) {
    fun validate(token: Token) {
        if (!authClient.auth(token)) {
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED, "You are not authorized! Please log in!")
        }
    }

    fun <T> validate(tokenRequest: TokenRequest<T>) {
        validate(tokenRequest.token)
    }
}