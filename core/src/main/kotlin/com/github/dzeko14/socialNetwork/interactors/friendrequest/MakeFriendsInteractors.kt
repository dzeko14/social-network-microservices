package com.github.dzeko14.socialNetwork.interactors.friendrequest

import com.github.dzeko14.socialNetwork.entities.FriendRequest
import com.github.dzeko14.socialNetwork.entities.Friendship
import com.github.dzeko14.socialNetwork.entities.User
import com.github.dzeko14.socialNetwork.entities.interfaces.Identifiable
import com.github.dzeko14.socialNetwork.exceptions.NoSuchUserException
import com.github.dzeko14.socialNetwork.provider.StorageProvider
import java.rmi.NoSuchObjectException
import java.time.LocalDateTime

class MakeFriendsInteractors(
        private val friendshipStorageProvider: StorageProvider<Friendship>,
        private val friendRequestStorageProvider: StorageProvider<FriendRequest>,
        private val userStorageProvider: StorageProvider<User>
) : ApproveFriendRequestInteractor, DenyFriendRequestInteractor,
        MakeFriendsRequestInteractor {

    override fun approve(friendRequestId: Identifiable) {
        val friendRequest = friendRequestStorageProvider.getById(friendRequestId)

        if (!friendRequestStorageProvider.validate(friendRequest)) {
            throw NoSuchObjectException("There is no such object in database: $friendRequest")
        }

        friendshipStorageProvider.save(
                Friendship.fromFriendRequest(friendRequest)
        )

        friendshipStorageProvider.save(
                Friendship.fromFriendRequestReversed(friendRequest)
        )

        friendRequestStorageProvider.delete(friendRequest)
    }

    override fun deny(requestId: Identifiable) {
        val request = friendRequestStorageProvider.getById(requestId)

        if(friendRequestStorageProvider.validate(request)) {
            friendRequestStorageProvider.delete(request)
        }
    }

    override fun makeFriend(requesterId: Identifiable, targetId: Identifiable) {
        val requester = try {
            userStorageProvider.getById(requesterId)
        } catch (e: Exception) {
            throw NoSuchUserException()
        }

        val target = try {
            userStorageProvider.getById(targetId)
        } catch (e: Exception) {
            throw NoSuchUserException()
        }

        val request = object : FriendRequest(
                sender = requester,
                receiver = target,
                date = LocalDateTime.now()){}

        friendRequestStorageProvider.save(request)
    }
}