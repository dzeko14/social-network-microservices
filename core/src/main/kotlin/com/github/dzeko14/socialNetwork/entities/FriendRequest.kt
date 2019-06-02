package com.github.dzeko14.socialNetwork.entities

import com.github.dzeko14.socialNetwork.entities.interfaces.Identifiable
import java.time.LocalDateTime
import java.util.*

abstract class FriendRequest(
        override val id: Long = 0,
        open val sender: User = User.emptyObject(),
        open val receiver: User = User.emptyObject(),
        open val date: Long = Date().time
) : Identifiable