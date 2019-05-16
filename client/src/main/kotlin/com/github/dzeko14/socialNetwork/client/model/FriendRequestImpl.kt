package com.github.dzeko14.socialNetwork.client.model

import com.github.dzeko14.socialNetwork.entities.FriendRequest
import java.time.LocalDateTime

class FriendRequestImpl(
        override val id: Long = 0,

        override val sender: UserImpl,

        override val receiver: UserImpl,

        override val date: LocalDateTime = LocalDateTime.now()
) : FriendRequest(id, sender, receiver, date) {
        constructor(fr: FriendRequest): this(
                fr.id,
                UserImpl(fr.sender),
                UserImpl(fr.receiver),
                fr.date
        )
}