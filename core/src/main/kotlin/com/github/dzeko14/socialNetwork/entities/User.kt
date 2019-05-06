package com.github.dzeko14.socialNetwork.entities

abstract class User (
        open val id: Long,
        open val login: String,
        open val password: String,
        open val email: String
)