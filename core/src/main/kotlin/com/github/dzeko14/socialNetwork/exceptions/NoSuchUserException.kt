package com.github.dzeko14.socialNetwork.exceptions

import com.github.dzeko14.socialNetwork.entities.User

class NoSuchUserException(
       private val user: User = User.emptyObject()
) : Exception() {
    override val message: String?
        get() = "There is no user: $user"
}