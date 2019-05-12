package com.github.dzeko14.socialNetwork.interactors.user

import com.github.dzeko14.socialNetwork.entities.Token
import com.github.dzeko14.socialNetwork.entities.User

interface LoginUserInteractor {
    fun login(user: User, token: Token): Token
}