package com.github.dzeko14.socialNetwork.entities

import com.github.dzeko14.socialNetwork.entities.interfaces.Identifiable

abstract class ChatMember(
        override val id: Long = 0,
        open val name: String = "",
        open val user: User = User.emptyObject()
) : Identifiable {
    companion object {
        fun empty(): ChatMember {
            return object : ChatMember() {}
        }
    }
}