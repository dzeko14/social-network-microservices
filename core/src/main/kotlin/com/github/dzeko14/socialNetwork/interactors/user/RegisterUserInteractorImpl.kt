package com.github.dzeko14.socialNetwork.interactors.user

import com.github.dzeko14.socialNetwork.entities.User
import com.github.dzeko14.socialNetwork.exceptions.EmptyFieldException
import com.github.dzeko14.socialNetwork.provider.StorageProvider

class RegisterUserInteractorImpl(
        private val userStorageProvider: StorageProvider<User>
) : RegisterUserInteractor {

    override fun register(user: User): User {
        if (user.login.isEmpty() || user.password.isEmpty())
            throw EmptyFieldException(user)

        val u = userStorageProvider.save(user)
        return User.withoutPassword(u)
    }
}