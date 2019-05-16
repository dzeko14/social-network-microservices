package com.github.dzeko14.socialNetwork.client.model

import com.github.dzeko14.socialNetwork.entities.User

data class UserImpl(
        override val id: Long = 0,
        override val login: String,
        override val password: String = "",
        override val email: String = "",
        override val name: String = ""
) : User(id, login, password, email) {
    constructor(user: User): this(user.id, user.login, user.password, user.email, user.name)
}