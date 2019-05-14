package com.github.dzeko14.socialNetwork.provider

import com.github.dzeko14.socialNetwork.entities.FriendRequest
import com.github.dzeko14.socialNetwork.entities.User

interface FriendRequestProvider {
    fun getUsersFriendRequest(user: User): List<FriendRequest>
}