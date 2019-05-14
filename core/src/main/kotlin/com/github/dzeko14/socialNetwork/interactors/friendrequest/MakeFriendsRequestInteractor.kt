package com.github.dzeko14.socialNetwork.interactors.friendrequest

import com.github.dzeko14.socialNetwork.entities.FriendRequest
import com.github.dzeko14.socialNetwork.entities.interfaces.Identifiable

interface MakeFriendsRequestInteractor {
    fun makeFriend(requesterId: Identifiable, targetId: Identifiable)
}