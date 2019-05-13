package com.github.dzeko14.socialNetwork.interactors.user

import com.github.dzeko14.socialNetwork.entities.Token
import com.github.dzeko14.socialNetwork.validator.TokenValidator

class ValidateUserInteractorImpl(
        private val tokenValidator: TokenValidator
) : ValidateUserInteractor {
    override fun validate(token: Token): Boolean {
        return tokenValidator.validateToken(token)
    }
}