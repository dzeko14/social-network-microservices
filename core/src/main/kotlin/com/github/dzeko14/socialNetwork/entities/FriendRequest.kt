package com.github.dzeko14.socialNetwork.entities

import java.time.LocalDateTime
import java.util.*

abstract class FriendRequest(
        open val id: UUID,
        open val sender: User,
        open val receiver: User,
        open val date: LocalDateTime)