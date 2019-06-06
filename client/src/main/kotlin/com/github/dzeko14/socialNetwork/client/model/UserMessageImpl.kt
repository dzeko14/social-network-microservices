package com.github.dzeko14.socialNetwork.client.model

import com.github.dzeko14.socialNetwork.entities.Chat
import com.github.dzeko14.socialNetwork.entities.User
import com.github.dzeko14.socialNetwork.entities.UserMessage

data class UserMessageImpl(
        override val id: Long = 0,
        override val content: String = "",
        override val author: UserImpl = UserImpl(User.emptyObject()),
        override val date: Long = 0,
        override val chat: ChatImpl = ChatImpl(Chat.empty())
) : UserMessage(id, content, author, date, chat) {
        constructor(userMessage: UserMessage)
                : this(userMessage.id,
                userMessage.content,
                UserImpl(userMessage.author),
                userMessage.date,
                ChatImpl(userMessage.chat))
}