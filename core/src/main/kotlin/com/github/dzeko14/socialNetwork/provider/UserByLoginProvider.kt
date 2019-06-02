package com.github.dzeko14.socialNetwork.provider

import com.github.dzeko14.socialNetwork.entities.User

interface UserByLoginProvider {
    fun getUserByLogin(login: String): User
}