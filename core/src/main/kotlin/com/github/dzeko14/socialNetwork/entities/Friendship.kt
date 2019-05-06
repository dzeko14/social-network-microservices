package com.github.dzeko14.socialNetwork.entities

import java.util.*

abstract class Friendship(
        open val id: UUID,
        open val user1: User,
        open val user2: User
) {
    companion object {
        fun fromFriendRequest(fr: FriendRequest): Friendship {
            return FriendshipImpl(fr.sender, fr.receiver)
        }
    }
    private data class FriendshipImpl(
            override val user1: User,
            override val user2: User
    ) : Friendship(UUID.randomUUID(), user1, user2)
}