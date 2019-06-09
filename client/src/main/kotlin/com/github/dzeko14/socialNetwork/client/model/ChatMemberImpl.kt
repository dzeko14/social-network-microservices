package com.github.dzeko14.socialNetwork.client.model

import com.github.dzeko14.socialNetwork.entities.ChatMember
import com.github.dzeko14.socialNetwork.entities.User

data class ChatMemberImpl(
        override val id: Long = 0,
        override val name: String = "",
        override val user: UserImpl = UserImpl(User.emptyObject())
) : ChatMember(id, name, user) {
    constructor(chatMember: ChatMember): this(chatMember.id, chatMember.name, UserImpl(chatMember.user))


}