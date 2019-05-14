package com.github.dzeko14.socialNetwork.interactors.friendrequest

import com.github.dzeko14.socialNetwork.entities.FriendRequest
import com.github.dzeko14.socialNetwork.entities.User
import com.github.dzeko14.socialNetwork.entities.interfaces.Identifiable
import com.github.dzeko14.socialNetwork.exceptions.NoSuchUserException
import com.github.dzeko14.socialNetwork.provider.FriendRequestProvider
import com.github.dzeko14.socialNetwork.provider.StorageProvider

class GetUserFriendRequestInteractorImpl(
    private val friendRequestProvider: FriendRequestProvider,
    private val userStorageProvider: StorageProvider<User>
) : GetUserFriendRequestInteractor {

    override fun getFriendRequests(id: Identifiable): List<FriendRequest> {
        val user = try { userStorageProvider.getById(id) } catch (e: Exception) { throw NoSuchUserException(User.emptyObject())}
        return friendRequestProvider.getUsersFriendRequest(user)
                .map {
                    val receiver = it.receiver
                    val sender = it.sender
                    object : FriendRequest(
                        it.id,
                        User.withoutPassword(sender),
                        User.withoutPassword(receiver),
                        it.date
                ) {}
        }
    }
}