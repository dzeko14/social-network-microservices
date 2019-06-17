package com.github.dzeko14.socialNetwork.interactors.user

import com.github.dzeko14.socialNetwork.entities.User
import com.github.dzeko14.socialNetwork.entities.interfaces.Identifiable
import com.github.dzeko14.socialNetwork.exceptions.NoSuchUserException
import com.github.dzeko14.socialNetwork.exceptions.WrongPasswordException
import com.github.dzeko14.socialNetwork.interactors.crud.DefaultCrudInteractor
import com.github.dzeko14.socialNetwork.provider.StorageProvider
import com.github.dzeko14.socialNetwork.validator.PasswordValidator

class UserCrudInteractor(
        private val storage: StorageProvider<User>,
        private val passwordValidator: PasswordValidator
) : DefaultCrudInteractor<User>(storage), UpdateUserInfoInteractor, DeleteUserInteractor {
    override fun getAll(): List<User> {
        return storage.getAll().map {
            object : User(
                    id = it.id,
                    login = it.login,
                    email = it.email,
                    name = it.name ) { }
        }
    }

    override fun get(id: Identifiable): User {
        val user = super.get(id)
        return object : User(
                user.id,
                user.login,
                "",
                user.email,
                user.name
        ) { }
    }

    override fun update(user: User, password: String) {
        if (!passwordValidator.validateUserPassword(
                        object : User(
                        user.id,
                        user.login,
                        password,
                        user.email,
                        user.name) { })) {
            throw WrongPasswordException(user)
        }

        val oldUserInfo = try {
             storage.getById(user)
        } catch (e: Exception) {
            throw NoSuchUserException(user)
        }


        val newName = if (user.name.isNotEmpty()) user.name else null
        val newEmail = if (user.email.isNotEmpty()) user.email else null
        val newPassword = if (user.password.isNotEmpty()) user.password else null

        storage.update(
                object : User(
                        oldUserInfo.id,
                        oldUserInfo.login,
                        newPassword ?: oldUserInfo.password,
                        newEmail ?: oldUserInfo.email,
                        newName ?: oldUserInfo.name
                ) {}
        )
    }

    override fun delete(user: User) {
        if (!storage.validate(user)) {
            throw NoSuchUserException(user)
        }

        if (!passwordValidator.validateUserPassword(user)) {
            throw WrongPasswordException(user)
        }

        storage.delete(user)
    }
}