package com.github.dzeko14.socialNetwork.validator

import com.github.dzeko14.socialNetwork.entities.Token
import com.github.dzeko14.socialNetwork.entities.User

interface TokenValidator {
    fun validateToken(token: Token): Boolean

    fun generateToken(user: User): Token
}