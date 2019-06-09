package com.github.dzeko14.socialNetwork.messagingService.model

import com.github.dzeko14.socialNetwork.entities.ChatMember
import com.github.dzeko14.socialNetwork.entities.User
import javax.persistence.*

@Entity(name = "ChatMembers")
data class ChatMemberImpl(
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        override val id: Long = 0,
        override val name: String = "",
        @ManyToOne(targetEntity = UserImpl::class)
        override val user: UserImpl = UserImpl(User.emptyObject())
) : ChatMember(id, name, user) {
    constructor(chat: ChatMember): this(chat.id, chat.name, UserImpl(chat.user))
}