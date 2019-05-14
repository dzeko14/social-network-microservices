package com.github.dzeko14.socialNetwork.interactors.friendrequest

import com.github.dzeko14.socialNetwork.entities.FriendRequest
import com.github.dzeko14.socialNetwork.entities.interfaces.Identifiable

interface ApproveFriendRequestInteractor {
    fun approve(friendRequestId: Identifiable)
}