package com.github.dzeko14.socialNetwork.interactors.user

import com.github.dzeko14.socialNetwork.entities.User

interface DeleteUserInteractor {
    fun delete(user: User)
}