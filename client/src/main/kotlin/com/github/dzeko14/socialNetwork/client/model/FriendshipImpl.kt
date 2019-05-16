package com.github.dzeko14.socialNetwork.client.model

import com.github.dzeko14.socialNetwork.entities.Friendship

class FriendshipImpl(
        override val id: Long = 0,
        override val user1: UserImpl,

        override val user2: UserImpl
) : Friendship(id, user1, user2) {
        constructor(fr: Friendship): this(
                fr.id,
                UserImpl(fr.user1),
                UserImpl(fr.user2)
        )
}