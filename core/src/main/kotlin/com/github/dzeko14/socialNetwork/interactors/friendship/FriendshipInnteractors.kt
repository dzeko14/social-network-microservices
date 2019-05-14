package com.github.dzeko14.socialNetwork.interactors.friendship

import com.github.dzeko14.socialNetwork.entities.User
import com.github.dzeko14.socialNetwork.entities.interfaces.Identifiable
import com.github.dzeko14.socialNetwork.exceptions.NoSuchUserException
import com.github.dzeko14.socialNetwork.provider.FriendshipProvider
import com.github.dzeko14.socialNetwork.provider.StorageProvider

class FriendshipInnteractors(
        private val userStorage: StorageProvider<User>,
        private val friendshipProvider: FriendshipProvider
) : GetUserFriendsInteractor, RemoveFriendshipInteractor {
    override fun get(userId: Identifiable): List<User> {
        val user = try {
             userStorage.getById(userId)
        } catch (e: Exception) {
            throw NoSuchUserException(User.emptyObject())
        }

        val set = mutableSetOf<User>()

        friendshipProvider.provideUserFriendships(user).forEach { set.add(it.user2) }
        return set.toList()
    }

    override fun remove(removerId: Identifiable, targetId: Identifiable) {
        val remover = try {
            userStorage.getById(removerId)
        } catch (e: Exception) {
            throw NoSuchUserException(User.emptyObject())
        }

        val target = try {
            userStorage.getById(targetId)
        } catch (e: Exception) {
            throw NoSuchUserException(User.emptyObject())
        }

        friendshipProvider.removeFriendshipBetweenUsers(remover, target)
        friendshipProvider.removeFriendshipBetweenUsers(target, remover)
    }
}