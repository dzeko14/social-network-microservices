package com.github.dzeko14.socialNetwork.userService.model

import com.github.dzeko14.socialNetwork.entities.FriendRequest
import com.github.dzeko14.socialNetwork.entities.User
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity(name = "Friend_requests")
class FriendRequestImpl(
        @Id @GeneratedValue(strategy = GenerationType.AUTO) override val id: Long,

        @ManyToOne(targetEntity = UserImpl::class, cascade = [CascadeType.REMOVE])
        @JoinColumn
        override val sender: User,

        @ManyToOne(targetEntity = UserImpl::class, cascade = [CascadeType.REMOVE])
        @JoinColumn
        override val receiver: User,

        override val date: LocalDateTime
) : FriendRequest(id, sender, receiver, date)