package com.github.dzeko14.socialNetwork.messagingService.model

import com.github.dzeko14.socialNetwork.entities.Chat
import javax.persistence.*

@Entity(name = "Chats")
data class ChatImpl(
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        override val id: Long = 0,
        override val name: String = "",
        @ElementCollection(fetch = FetchType.EAGER)
        override val members: List<Long> = ArrayList()
) : Chat(id, name, members) {
    constructor(chat: Chat): this(chat.id, chat.name, chat.members)


}