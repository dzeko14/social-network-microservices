package com.github.dzeko14.socialNetwork.interactors.friendrequest

import com.github.dzeko14.socialNetwork.entities.FriendRequest

interface MakeFriendsRequestInteractor {
    fun makeFriend(request: FriendRequest)
}