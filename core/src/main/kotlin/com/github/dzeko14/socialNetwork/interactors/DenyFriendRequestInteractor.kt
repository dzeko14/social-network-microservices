package com.github.dzeko14.socialNetwork.interactors

import com.github.dzeko14.socialNetwork.entities.FriendRequest

interface DenyFriendRequestInteractor {
    fun deny(request: FriendRequest)
}