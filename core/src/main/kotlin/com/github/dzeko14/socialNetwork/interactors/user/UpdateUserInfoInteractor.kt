package com.github.dzeko14.socialNetwork.interactors.user

import com.github.dzeko14.socialNetwork.entities.User

interface UpdateUserInfoInteractor {
    fun update(user: User, password: String)
}