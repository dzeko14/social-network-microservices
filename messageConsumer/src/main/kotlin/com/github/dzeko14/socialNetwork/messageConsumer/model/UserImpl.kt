package com.github.dzeko14.socialNetwork.messageConsumer.model

import com.github.dzeko14.socialNetwork.entities.User
import javax.persistence.Embeddable

@Embeddable
data class UserImpl(
        override var id: Long = 0,
        override var login: String = "",
        override var email: String = "",
        override var name: String = ""
) : User(id, login, "", email) {
    constructor(user: User): this(user.id, user.login, user.email, user.name)
}