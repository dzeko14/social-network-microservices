package com.github.dzeko14.socialNetwork.entities

import com.github.dzeko14.socialNetwork.entities.interfaces.Identifiable

abstract class Friendship(
        override val id: Long = 0,
        open val user1: User = User.emptyObject(),
        open val user2: User = User.emptyObject()
) : Identifiable {
    companion object {
        fun fromFriendRequest(fr: FriendRequest): Friendship {
            return FriendshipImpl(fr.sender, fr.receiver)
        }
    }
    private data class FriendshipImpl(
            override val user1: User,
            override val user2: User
    ) : Friendship(0, user1, user2)
}