package com.github.dzeko14.socialNetwork.messagingService.model

import com.github.dzeko14.socialNetwork.entities.User
import javax.persistence.*

@Entity(name = "Users")
data class UserImpl(
        @Id @GeneratedValue(strategy = GenerationType.AUTO) override val id: Long = 0,
        @Column(unique = true, columnDefinition = "varchar(20)") override val login: String,
        override val password: String = "",
        override val email: String = "",
        override val name: String = ""
) : User(id, login, password, email) {
    constructor(user: User): this(user.id, user.login, user.password, user.email, user.name)
}