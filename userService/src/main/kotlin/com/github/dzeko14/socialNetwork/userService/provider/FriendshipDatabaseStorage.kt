package com.github.dzeko14.socialNetwork.userService.provider

import com.github.dzeko14.socialNetwork.entities.Friendship
import com.github.dzeko14.socialNetwork.entities.User
import com.github.dzeko14.socialNetwork.provider.FriendshipProvider
import com.github.dzeko14.socialNetwork.userService.model.FriendshipImpl
import com.github.dzeko14.socialNetwork.userService.model.UserImpl
import com.github.dzeko14.socialNetwork.userService.repository.FriendshipRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class FriendshipDatabaseStorage @Autowired constructor(
        private val friendshipRepository: FriendshipRepository
) : DefaultDatabaseStorageProvider<Friendship, FriendshipImpl>(friendshipRepository),
FriendshipProvider {
    override fun getObject(obj: Friendship): FriendshipImpl {
        return FriendshipImpl(obj)
    }

    override fun updateObject(old: Friendship, new: Friendship): FriendshipImpl {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun validate(obj: Friendship): Boolean {
        return friendshipRepository.countById(obj.id) > 0
    }

    override fun provideUserFriendships(user: User): List<Friendship> {
        return friendshipRepository.findAllByUser1(user)
    }

    override fun removeFriendshipBetweenUsers(user1: User, user2: User) {
        friendshipRepository.deleteFriendshipImplByUser1AndUser2(UserImpl(user1), UserImpl(user2))
    }
}