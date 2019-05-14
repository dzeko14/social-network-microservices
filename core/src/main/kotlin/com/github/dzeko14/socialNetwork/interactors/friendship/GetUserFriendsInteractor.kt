package com.github.dzeko14.socialNetwork.interactors.friendship

import com.github.dzeko14.socialNetwork.entities.User
import com.github.dzeko14.socialNetwork.entities.interfaces.Identifiable

interface GetUserFriendsInteractor {
    fun get(userId: Identifiable): List<User>
}