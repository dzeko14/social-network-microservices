package com.github.dzeko14.socialNetwork.interactors.implementations

import com.github.dzeko14.socialNetwork.entities.FriendRequest
import com.github.dzeko14.socialNetwork.entities.Friendship
import com.github.dzeko14.socialNetwork.entities.User
import com.github.dzeko14.socialNetwork.exceptions.NoSuchUserException
import com.github.dzeko14.socialNetwork.interactors.ApproveFriendRequestInteractor
import com.github.dzeko14.socialNetwork.interactors.DenyFriendRequestInteractor
import com.github.dzeko14.socialNetwork.interactors.MakeFriendsRequestInteractor
import com.github.dzeko14.socialNetwork.provider.StorageProvider
import java.rmi.NoSuchObjectException

class MakeFriendsInteractors(
        private val requestStorageProvider: StorageProvider<FriendRequest>,
        private val friendshipStorageProvider: StorageProvider<Friendship>,
        private val friendRequestStorageProvider: StorageProvider<FriendRequest>,
        private val userStorageProvider: StorageProvider<User>
) : ApproveFriendRequestInteractor, DenyFriendRequestInteractor,
        MakeFriendsRequestInteractor {

    override fun approve(friendRequest: FriendRequest) {
        if (requestStorageProvider.validate(friendRequest)) {
            throw NoSuchObjectException("There is no such object in database: $friendRequest")
        }

        friendshipStorageProvider.save(
                Friendship.fromFriendRequest(friendRequest)
        )
    }

    override fun deny(request: FriendRequest) {
        if(friendRequestStorageProvider.validate(request)) {
            friendRequestStorageProvider.delete(request)
        }
    }

    override fun makeFriend(request: FriendRequest) {
        if (!(userStorageProvider.validate(request.sender))) {
            throw NoSuchUserException(request.sender)
        }

        if (!userStorageProvider.validate(request.receiver)) {
            throw NoSuchUserException(request.receiver)
        }

        if (requestStorageProvider.validate(request)) {
            return
        }

        requestStorageProvider.save(request)
    }
}