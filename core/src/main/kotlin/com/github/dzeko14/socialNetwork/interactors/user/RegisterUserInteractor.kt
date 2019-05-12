package com.github.dzeko14.socialNetwork.interactors.user

import com.github.dzeko14.socialNetwork.entities.User

interface RegisterUserInteractor {
    fun register(user: User)
}