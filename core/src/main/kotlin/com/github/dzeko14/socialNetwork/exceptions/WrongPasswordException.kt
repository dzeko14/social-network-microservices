package com.github.dzeko14.socialNetwork.exceptions

import com.github.dzeko14.socialNetwork.entities.User

class WrongPasswordException(
        user: User
) : Exception() {
    val password = user.password

    override val message: String?
        get() = "User have entered wrong password!!!"
}