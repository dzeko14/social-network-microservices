package com.github.dzeko14.socialNetwork.entities

import com.github.dzeko14.socialNetwork.entities.interfaces.Identifiable

abstract class UserMessage(
        override val id: Long = 0,
        open val content: String = "",
        open val author: User = User.emptyObject(),
        open val date: Long = 0,
        open val chatName: String = ""
) : Identifiable