package com.github.dzeko14.socialNetwork.messagingService.model

import com.github.dzeko14.socialNetwork.entities.Chat
import com.github.dzeko14.socialNetwork.entities.User
import com.github.dzeko14.socialNetwork.entities.UserMessage
import javax.persistence.*

@Entity(name = "UserMessage")
data class UserMessageImpl(
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        override val id: Long = 0,
        override val content: String = "",
        @ManyToOne(targetEntity = UserImpl::class)
        override val author: UserImpl = UserImpl(User.emptyObject()),
        override val date: Long = 0,
        @ManyToOne(targetEntity = ChatImpl::class)
        override val chat: ChatImpl = ChatImpl(Chat.empty())
) : UserMessage(id, content, author, date, chat) {
        constructor(userMessage: UserMessage)
                : this(userMessage.id,
                userMessage.content,
                UserImpl(userMessage.author),
                userMessage.date,
                ChatImpl(userMessage.chat))
}