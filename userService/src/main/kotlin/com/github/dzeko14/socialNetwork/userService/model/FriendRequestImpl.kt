package com.github.dzeko14.socialNetwork.userService.model

import com.github.dzeko14.socialNetwork.entities.FriendRequest
import com.github.dzeko14.socialNetwork.entities.User
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity(name = "Friend_requests")
class FriendRequestImpl(
        @Id @GeneratedValue(strategy = GenerationType.AUTO) override val id: Long = 0,

        @ManyToOne(targetEntity = UserImpl::class, cascade = [CascadeType.REMOVE])
        @JoinColumn
        override val sender: UserImpl,

        @ManyToOne(targetEntity = UserImpl::class)
        @JoinColumn
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