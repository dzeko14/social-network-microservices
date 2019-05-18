package com.github.dzeko14.socialNetwork.client.model

import java.time.LocalDateTime
import java.time.ZoneOffset

class RabbitMQMessage<T>(
        val title: String,
        val body: T,
        val date: Long = LocalDateTime.now().unixTimestamp()
)

fun LocalDateTime.unixTimestamp(): Long {
    return this.toEpochSecond(ZoneOffset.UTC)
}