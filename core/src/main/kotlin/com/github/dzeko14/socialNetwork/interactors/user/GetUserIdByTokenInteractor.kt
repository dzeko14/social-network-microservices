package com.github.dzeko14.socialNetwork.interactors.user

import com.github.dzeko14.socialNetwork.entities.Token

interface GetUserIdByTokenInteractor {
    fun getUserIdByToken(token: Token): Long
}