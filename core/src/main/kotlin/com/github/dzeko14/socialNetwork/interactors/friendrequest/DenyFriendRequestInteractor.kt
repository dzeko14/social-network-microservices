package com.github.dzeko14.socialNetwork.interactors.friendrequest

import com.github.dzeko14.socialNetwork.entities.FriendRequest

interface DenyFriendRequestInteractor {
    fun deny(request: FriendRequest)
}