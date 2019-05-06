package com.github.dzeko14.socialNetwork.userService.model

import com.github.dzeko14.socialNetwork.entities.Friendship
import com.github.dzeko14.socialNetwork.entities.User
import java.util.*
import javax.persistence.*

@Entity(name = "Friendships")
class FriendshipImpl(
        @Id @GeneratedValue(strategy = GenerationType.AUTO) override val id: Long = 0,

        @ManyToOne(targetEntity = UserImpl::class, cascade = [CascadeType.REMOVE])
        @JoinColumn
        override val user1: User = User.emptyObject(),

        @ManyToOne(targetEntity = UserImpl::class, cascade = [CascadeType.REMOVE])
        @JoinColumn
        override val user2: User = User.emptyObject()
) : Friendship(id, user1, user2)