package com.github.dzeko14.socialNetwork.interactors.user

import com.github.dzeko14.socialNetwork.entities.Token
import com.github.dzeko14.socialNetwork.entities.User
import com.github.dzeko14.socialNetwork.exceptions.EmptyFieldException
import com.github.dzeko14.socialNetwork.exceptions.WrongPasswordException
import com.github.dzeko14.socialNetwork.validator.PasswordValidator
import com.github.dzeko14.socialNetwork.validator.TokenValidator

class LoginUserInteractorImpl(
        private val passwordValidator: PasswordValidator,
        private val tokenValidator: TokenValidator
) : LoginUserInteractor {

    override fun login(user: User, token: Token): Token {
        if (tokenValidator.validateToken(token, user)) return token

        if (user.login.isEmpty() || user.password.isEmpty()) {
            throw EmptyFieldException(user)
        }

        if (!passwordValidator.validateUserPassword(user)) {
            throw WrongPasswordException(user)
        }

        return tokenValidator.generateToken(user)
    }
}