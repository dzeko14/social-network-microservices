package com.github.dzeko14.socialNetwork.messageConsumer.model

class RabbitMQMessage<T>(
        var title: String = "",
        var body: T? = null,
        var date: Long = 0
)