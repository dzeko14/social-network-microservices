package com.github.dzeko14.socialNetwork.client.model

import com.github.dzeko14.socialNetwork.entities.Chat

data class ChatImpl(
        override val id: Long = 0,
        override val name: String = "",
        override val members: Set<Long> = HashSet()
) : Chat(id, name, members) {
    constructor(chat: Chat): this(chat.id, chat.name, chat.members)


}