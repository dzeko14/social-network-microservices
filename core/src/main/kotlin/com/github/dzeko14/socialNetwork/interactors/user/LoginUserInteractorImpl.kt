package com.github.dzeko14.socialNetwork.interactors.user

import com.github.dzeko14.socialNetwork.entities.Token
import com.github.dzeko14.socialNetwork.entities.User
import com.github.dzeko14.socialNetwork.exceptions.EmptyFieldException
import com.github.dzeko14.socialNetwork.exceptions.WrongPasswordException
import com.github.dzeko14.socialNetwork.provider.StorageProvider
import com.github.dzeko14.socialNetwork.provider.UserByLoginProvider
import com.github.dzeko14.socialNetwork.validator.PasswordValidator
import com.github.dzeko14.socialNetwork.validator.TokenValidator

class LoginUserInteractorImpl(
        private val passwordValidator: PasswordValidator,
        private val tokenValidator: TokenValidator,
        private val userByLoginProvider: UserByLoginProvider,
        private val getUserIdByTokenInteractor: GetUserIdByTokenInteractor
) : LoginUserInteractor {

    override fun login(user: User, token: Token): Token {
        val lUser = userByLoginProvider.getUserByLogin(user.login)
        if (tokenValidator.validateToken(token)
                && getUserIdByTokenInteractor.getUserIdByToken(token) == lUser.id){
            return token
        }

        if (user.login.isEmpty() || user.password.isEmpty()) {
            throw EmptyFieldException(user)
        }

        if (!passwordValidator.validateUserPassword(user)) {
            throw WrongPasswordException(user)
        }

        return tokenValidator.generateToken(lUser)
    }
}