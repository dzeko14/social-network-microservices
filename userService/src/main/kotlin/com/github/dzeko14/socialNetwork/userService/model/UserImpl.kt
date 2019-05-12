package com.github.dzeko14.socialNetwork.userService.model

import com.github.dzeko14.socialNetwork.entities.User
import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity(name = "Users")
class UserImpl(
        @Id @GeneratedValue(strategy = GenerationType.AUTO) override val id: Long = 0,
        override val login: String,
        override val password: String = "",
        override val email: String = "",
        override val name: String = ""
) : User(id, login, password, email) {
    constructor(user: User): this(user.id, user.login, user.password, user.email, user.name)
}