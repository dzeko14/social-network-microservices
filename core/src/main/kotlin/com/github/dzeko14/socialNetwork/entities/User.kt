package com.github.dzeko14.socialNetwork.entities

import com.github.dzeko14.socialNetwork.entities.interfaces.Identifiable
import java.util.*

abstract class User  (
        override val id: Long = 0,
        open val login: String = "",
        open val password: String = "",
        open val email: String = "",
        open val name: String = ""
) : Identifiable {
    companion object {
        fun emptyObject(): User {
            return object : User() {
                override val id: Long
                    get() = super.id
                override val login: String
                    get() = super.login
                override val password: String
                    get() = super.password
                override val email: String
                    get() = super.email
                override val name: String
                    get() = super.name
            }
        }
    }
}