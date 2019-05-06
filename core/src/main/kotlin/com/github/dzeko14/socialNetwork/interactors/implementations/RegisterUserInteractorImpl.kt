package com.github.dzeko14.socialNetwork.interactors.implementations

import com.github.dzeko14.socialNetwork.entities.User
import com.github.dzeko14.socialNetwork.exceptions.EmptyFieldException
import com.github.dzeko14.socialNetwork.interactors.RegisterUserInteractor
import com.github.dzeko14.socialNetwork.provider.StorageProvider

class RegisterUserInteractorImpl(
        private val userStorageProvider: StorageProvider<User>
) : RegisterUserInteractor {

    override fun register(user: User) {
        if (user.login.isEmpty() || user.password.isEmpty())
            throw EmptyFieldException(user)

        userStorageProvider.save(user)
    }
}